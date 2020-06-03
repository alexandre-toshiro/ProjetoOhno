package com.example.ohno11.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ohno11.DAO.CargoDAO;
import com.example.ohno11.DAO.ClienteDAO;
import com.example.ohno11.DAO.EnderecoDAO;
import com.example.ohno11.DAO.FuncionarioDAO;
import com.example.ohno11.DAO.ServicoDAO;
import com.example.ohno11.DAO.VeiculoDAO;
import com.example.ohno11.MainActivity;
import com.example.ohno11.backend.Cargo;
import com.example.ohno11.backend.Cliente;
import com.example.ohno11.backend.Endereco;
import com.example.ohno11.backend.Funcionario;
import com.example.ohno11.backend.Servico;
import com.example.ohno11.backend.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    CargoDAO cargo;
    ClienteDAO cliente;
    EnderecoDAO endereco;
    FuncionarioDAO funcionario;
    ServicoDAO servico;
    VeiculoDAO veiculo;

    SQLiteDatabase db;

    public DataBase(){}

    public DataBase(Context context){
        BDCore auxBd = new BDCore(context);
        db = auxBd.getWritableDatabase();
        //auxBd.onCreate(db);

        //DELETAR AS TABELAS CASO ELAS JÁ EXISTAM
        db.execSQL("DROP TABLE IF EXISTS VEICULO;");
        db.execSQL("DROP TABLE IF EXISTS ENDERECO;");
        db.execSQL("DROP TABLE IF EXISTS SERVICO;");
        db.execSQL("DROP TABLE IF EXISTS CLIENTE;");
        db.execSQL("DROP TABLE IF EXISTS FUNCIONARIO;");
        db.execSQL("DROP TABLE IF EXISTS CARGO;");

        //CRIANDO TABELAS
        db.execSQL("create table veiculo(id integer primary key autoincrement, modelo varchar(30), ano integer, placa varchar(7) unique, marca varchar(30), informacoesAdicionais varchar(250), foreignKeyCliente varchar(11), nota integer unique)");
        db.execSQL("create table endereco(id integer primary key autoincrement, rua varchar(50), numero integer, complemento varchar(50), cep varchar(8), bairro varchar(50), cidade varchar(50), estado varchar(50))");
        db.execSQL("create table servico(descricao varchar(250), tempo double, valor double, placa varchar(7))");
        db.execSQL("create table cliente(id integer primary key autoincrement, nome varchar(50), cpf varchar(11) unique, foreignKeyEndereco integer, telefone varchar(50) unique, email varchar(50) unique, foreignkeyVeiculo integer)");
        db.execSQL("create table funcionario(id integer primary key autoincrement, nome varchar(50), cpf varchar(11) unique, foreignkeyEndereco integer, telefone varchar(50) unique, email varchar(50) unique, foreignkeyCargo integer, salario double)");
        db.execSQL("create table cargo(id integer primary key autoincrement, cargo varchar(50))");

        //INSERIR VEICULOS
        Veiculo veiculoinserir = new Veiculo("Ka", 2008, "ABC1234", "Ford", "Risco na porta esquerda", "43678954781", 1);
        inserirVeiculo(veiculoinserir);
        inserirVeiculo(new Veiculo("Fusion", 2012, "ABC4321", "Ford", "barulho no motor", "01234567890", 2));
        inserirVeiculo(new Veiculo("Chevette", 1974, "CDD1234", "Chevrolet", "barulho no cambio", "09876543210", 3));
        inserirVeiculo(new Veiculo("QQ", 2000, "AQQ1234", "Chery", "freio de mão não funciona", "01234567899", 4));


        //INSERIR CLIENTES
        inserirCliente(new Cliente("José", "43678954781", 1, "4684-4896", "jose@gmail.com", 1, 1));
        inserirCliente(new Cliente("Pedro", "01234567890", 2, "8888-9999", "pedro@gmail.com", 1, 2));
        inserirCliente(new Cliente("João", "09876543210", 3, "9999-8888", "joao@gmail.com", 2, 3));
        inserirCliente(new Cliente("Maria", "01234567899", 4, "1234-5678", "maria@gmail.com", 3, 4));


        //INSERIR SERVICOS
        /*
        inserirServicos(new Servico("Trocar Óleo", (0.5*60), 100, "ABC1234"));
        inserirServicos(new Servico("Reparo no pneu", (1*60), 50, "ABC1234"));
        inserirServicos(new Servico("Regulagem do motor", (3*60), 500, "ABC1234"));
        inserirServicos(new Servico("Alinhamento", (0.2*60) , 50, "CDD1234"));
        inserirServicos(new Servico("Cambagem", (0.8 * 60), 60, "CDD1234"));
        */

        //INSERIR ENDERECO
        inserirEndereco(new Endereco("Rua 1", 1, "A", "01569856", "Liberdade", "São Paulo", "SP"));
        inserirEndereco(new Endereco("Rua 2", 2, "B", "01234567", "Sé", "São Paulo", "SP"));
        inserirEndereco(new Endereco("Rua 3", 3, "Apto 120", "01234568", "Vila Mariana", "São Paulo", "SP"));
        inserirEndereco(new Endereco("Rua 4", 4, "Condominio Ohno", "01234569", "Santa Cecilia", "São Paulo", "SP"));

     }


    public void inserirVeiculo(Veiculo veiculo){
        ContentValues valores = new ContentValues();
        valores.put("modelo", veiculo.getModelo());
        valores.put("ano", veiculo.getAno());
        valores.put("placa", veiculo.getPlaca());
        valores.put("marca", veiculo.getMarca());
        valores.put("informacoesAdicionais", veiculo.getInformacoesAdicionais());
        valores.put("foreignKeyCliente", veiculo.getForeignKeyCliente());
        valores.put("nota", veiculo.getNotaservico());

        db.insert("veiculo", null, valores);
    }


    public void inserirCliente(Cliente cliente){
        ContentValues valores = new ContentValues();
        valores.put("nome", cliente.getNome());
        valores.put("cpf", cliente.getCpf());
        valores.put("foreignKeyEndereco", cliente.getForeignKeyveiculo());
        valores.put("telefone", cliente.getTelefone());
        valores.put("email", cliente.getEmail());
        valores.put("foreignKeyVeiculo", cliente.getForeignKeyveiculo());

        db.insert("cliente", null, valores);
    }

    public void inserirEndereco(Endereco endereco){
        ContentValues valores = new ContentValues();
        valores.put("rua", endereco.getRua());
        valores.put("numero", endereco.getNumero());
        valores.put("complemento", endereco.getComplemento());
        valores.put("cep", endereco.getCep());
        valores.put("bairro", endereco.getBairro());
        valores.put("cidade", endereco.getCidade());
        valores.put("estado", endereco.getEstado());


        db.insert("endereco", null, valores);
    }

    public void inserirServicos(Servico servico){
        ContentValues valores = new ContentValues();
        valores.put("descricao", servico.getDescricao());
        valores.put("tempo", servico.getTempoDuracao());
        valores.put("valor", servico.getValor());
        valores.put("placa", servico.getPlaca());

        db.insert("servico", null, valores);
    }

    public void inserirFuncionario(Funcionario funcionario){
        ContentValues valores = new ContentValues();
        valores.put("nome", funcionario.getNome());
        valores.put("cpf", funcionario.getCpf());
        valores.put("foreignKeyEndereco", funcionario.getForeignKeyEndereco());
        valores.put("telefone", funcionario.getTelefone());
        valores.put("email", funcionario.getEmail());
        valores.put("foreignkeyCargo", funcionario.getForeignKeyCargo());
        valores.put("salario", funcionario.getSalario());

        db.insert("funcionario", null, valores);
    }

    public void inserirCargo(Cargo cargo){
        ContentValues valores = new ContentValues();
        valores.put("cargo", cargo.getCargo());

        db.insert("cargo", null, valores);
    }



    public void atualizarVeiculo(Veiculo veiculo){
        ContentValues valores = new ContentValues();

        valores.put("modelo", veiculo.getModelo());
        valores.put("ano", veiculo.getAno());
        valores.put("placa", veiculo.getPlaca());
        valores.put("marca", veiculo.getMarca());
        valores.put("informacoesAdicionais", veiculo.getInformacoesAdicionais());
        valores.put("foreignKeyCliente", veiculo.getForeignKeyCliente());
        valores.put("nota", veiculo.getNotaservico());

        db.update("veiculo", valores, "id = ?", new String[]{""+veiculo.getId()});
    }


    public void deletarVeiculo(Veiculo veiculo){
        db.delete("veiculo", "id = ?"+veiculo.getId(), null);
    }


    public List<Veiculo> buscarVeiculo(){
        List<Veiculo> list = new ArrayList<Veiculo>();
        String[] colunas = new String[]{"id", "modelo", "ano", "placa", "marca", "informacoesAdicionais","foreignKeyCliente", "notaservico"};

        Cursor cursor = db.query("veiculo", colunas, null, null, null, null, "nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Veiculo veiculo = new Veiculo();

                veiculo.setId(cursor.getInt(0));
                veiculo.setModelo(cursor.getString(1));
                veiculo.setAno(cursor.getInt(2));
                veiculo.setPlaca(cursor.getString(3));
                veiculo.setMarca(cursor.getString(4));
                veiculo.setInformacoesAdicionais(cursor.getString(5));
                veiculo.setForeignKeyCliente(cursor.getString(6));
                veiculo.setNotaservico(cursor.getInt(7));
                list.add(veiculo);
            }while(cursor.moveToNext());
        }
        return(list);
    }


    public List<Servico> buscarServico(){
        List<Servico> list = new ArrayList<Servico>();
        String[] colunas = new String[]{"id", "descricao", "tempo", "valor", "foreignKeyVeiculo"};

        Cursor cursor = db.query("servico", colunas, null, null, null, null, "nome ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Servico servico = new Servico();
                servico.setDescricao(cursor.getString(1));
                servico.setTempoDuracao(cursor.getDouble(2));
                servico.setValor(cursor.getDouble(3));
                servico.setPlaca(cursor.getString(4));

                list.add(servico);
            }while(cursor.moveToNext());
        }
        return(list);
    }


    public void inserirVeiculoBD() {
        Veiculo veiculoinserir = new Veiculo("Ka", 2008, "ABC1234", "Ford", "Risco na porta esquerda");
        veiculo.inserir(veiculoinserir);
    }

    public void inserirClienteBD() {
        Cliente clienteInserir = new Cliente("José", "43678954781", 1, "4684-4896", "jose@gmail.com",1,1);
        cliente.inserir(clienteInserir);
    }

    public void inserirCargo(){
        Cargo cargoInserir = new Cargo("Mecânico");
        cargo.inserir(cargoInserir);
    }
    public void inserirEndereco(){
        Endereco enderecoinserir = new Endereco("Rua exemplo", 123, "Complemento b", "04886560", "Moema", "São Paulo", "SP");
        endereco.inserir(enderecoinserir);
    }
    public void inserirFuncionario(){
        Funcionario funcionarioInserir = new Funcionario("Pedro", "43678954782", 2, "8888-9999", "pedro@gmail.com",1,8000);
        funcionario.inserir(funcionarioInserir);
    }

    public void inserirServicos(){
        Servico servicoInserir = new Servico("Trocar Óleo", (0.5*60), 100);

        Servico servicoInserir3 = new Servico("Reparo no pneu", 1, 50);
        Servico servicoInserir4 = new Servico("Regulagem do motor", 3, 500);

        Servico servicoInserir5 = new Servico("Alinhamento", (0.2*60), 50);
        Servico servicoInserir6 = new Servico("Cambagem", (0.8*60), 60);

        servico.inserir(servicoInserir);
    }


}
