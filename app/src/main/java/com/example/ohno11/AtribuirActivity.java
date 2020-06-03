package com.example.ohno11;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ohno11.DAO.ServicoDAO;
import com.example.ohno11.backend.Cliente;
import com.example.ohno11.backend.Servico;
import com.example.ohno11.backend.Veiculo;
import com.example.ohno11.dataBase.BDCore;
import com.example.ohno11.dataBase.DataBase;

import java.util.ArrayList;

public class AtribuirActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView btnvoltar;
    String servicoSelecionado=" ", placaCliente;
    Button btnAdicionar, btnBuscar, btnEmitirOrdemDeServico;
    ListView ServicosAdicionados;
    EditText placaASerconsultada;
    ArrayList<Servico> getAllServicos = new ArrayList<>();
    ArrayList<Servico> servicosCadastrados = new ArrayList<>();
    String splacaASerconsultada;
    TextView outputPlaca, outputMarca, outputModelo, outputAno;
    boolean validadorVeiculo=false;
    BDCore auxBd = new BDCore(this);
    Spinner servicosAAdicionar;
    String placaBD = "";

    Servico enviarServico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atribuir);

        btnvoltar = (ImageView) findViewById(R.id.btnVoltar);
        btnBuscar= (Button) findViewById(R.id.btnBuscarPlaca);
        btnEmitirOrdemDeServico= (Button) findViewById(R.id.btnEmitirOS);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        ServicosAdicionados = (ListView) findViewById(R.id.listViewServicosAdicionados);
        placaASerconsultada = (EditText) findViewById(R.id.pesquisaPlaca);
        outputAno = (TextView) findViewById(R.id.outputAnoPesquisa);
        outputMarca = (TextView) findViewById(R.id.outputMarcaPesquisa);
        outputModelo= (TextView) findViewById(R.id.outputModeloPesquisa);
        outputPlaca = (TextView) findViewById(R.id.outputPlacaPesquisa);
        servicosAAdicionar= (Spinner) findViewById(R.id.dropBoxServicos);
        servicosAAdicionar.setEnabled(false);
        btnAdicionar.setEnabled(false);
        atribuirServicosSpinner();

        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AtribuirActivity.this, HomeActivity.class));
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splacaASerconsultada = placaASerconsultada.getText().toString().toUpperCase();
                closeKeyboard();
                buscarPlaca(splacaASerconsultada);
            }
        });
        btnEmitirOrdemDeServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Servico> listaServico = new ArrayList<Servico>();
                if(!(servicosCadastrados.size() != 0)){
                    Toast.makeText(AtribuirActivity.this, "Selecione um serviço", Toast.LENGTH_SHORT).show();
                }
                if(!validadorVeiculo){
                    Toast.makeText(AtribuirActivity.this, "Selecione um veículo", Toast.LENGTH_SHORT).show();
                }
                if(validadorVeiculo && servicosCadastrados.size() != 0){
                    for(int i=0; servicosCadastrados.size() > i; i++){
                        ServicoDAO servicoDAO = new ServicoDAO();
                        Servico servico = new Servico();
                        servico.setDescricao(servicosCadastrados.get(i).getDescricao());
                        servico.setTempoDuracao(servicosCadastrados.get(i).getTempoDuracao());
                        servico.setValor(servicosCadastrados.get(i).getValor());
                        servico.setPlaca(placaCliente);
                        listaServico.add(servico);
                    }
                    OrdemDeServicoActivity.getServico(listaServico);
                    OrdemDeServicoActivity.getplaca(placaBD);
                    startActivity(new Intent(AtribuirActivity.this, OrdemDeServicoActivity.class));
                }

            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(servicoSelecionado.equalsIgnoreCase("Selecione um serviço") || servicoSelecionado.equalsIgnoreCase(" ") ){
                    Toast.makeText(AtribuirActivity.this, "Selecione um serviço", Toast.LENGTH_SHORT).show();
                }else{
                    if(getAllServicos.size() != 0){
                        for (int i=0; i<getAllServicos.size(); i++) {
                            if(getAllServicos.get(i).getDescricao().equalsIgnoreCase(servicoSelecionado)){
                                servicosCadastrados.add((getAllServicos.get(i)));
                                ArrayAdapter adapter = new ServicoAdapter(AtribuirActivity.this, servicosCadastrados);
                                ServicosAdicionados.setAdapter(adapter);
                            }else{
                        } }
                }
            }
            }
        });
    }

    private void buscarPlaca(String placa) {
        this.placaBD = placa;

        ArrayList<Cliente> listClientes = new ArrayList<Cliente>();

        ArrayList<Veiculo> listVeiculo = new ArrayList<Veiculo>();

        String[] colunas = {"nome", "cpf", "telefone", "ano", "placa", "marca", "modelo"};

        try {
            Cursor cursor = auxBd.getWritableDatabase().query("veiculo, cliente", colunas, "veiculo.placa = '"+placa+"' and veiculo.foreignKeyCliente=cliente.cpf", null, null, null, null);
            //enquanto houver registros, adiciona a pessoa a listagem
            while (cursor.moveToNext()) {
                Cliente cliente = new Cliente();
                cliente.setNome(cursor.getString(0));
                cliente.setCpf(cursor.getString(1));
                cliente.setTelefone(cursor.getString(2));

                listClientes.add(cliente);

                Veiculo veiculo = new Veiculo();
                veiculo.setAno(cursor.getInt(3));
                veiculo.setPlaca(cursor.getString(4));
                veiculo.setMarca(cursor.getString(5));
                veiculo.setModelo(cursor.getString(6));
                placaCliente= veiculo.getPlaca();

                listVeiculo.add(veiculo);
            }
            if(!(placaCliente == null)){
                servicosAAdicionar.setEnabled(true);
                btnAdicionar.setEnabled(true);
            }


        if(listVeiculo.size() != 0){
            for (int i=0; i<listVeiculo.size(); i++) {
                if(listVeiculo.get(i).getPlaca().equalsIgnoreCase(placa)){
                    outputPlaca.setText("Placa: "+listVeiculo.get(i).getPlaca());
                    outputMarca.setText("Marca: "+listVeiculo.get(i).getMarca());
                    outputModelo.setText("Modelo: "+listVeiculo.get(i).getModelo());
                    outputAno.setText("Ano: "+listVeiculo.get(i).getAno());
                    validadorVeiculo=true;

                }else if(placa== null ||placa.equalsIgnoreCase("")
                        ||placa.equalsIgnoreCase(" ")
                        ||placa.length()<=0
                        ||placa.length()==1
                        ||placa.length()==2
                        ||placa.length()==3
                        ||placa.length()==4
                        ||placa.length()==5
                        ||placa.length()==6
                        ||placa.length()>=8
                        ){
                    Toast.makeText(this, "Digite uma placa válida!", Toast.LENGTH_SHORT).show();
                    validadorVeiculo=false;
                }
            }
        }else if(listVeiculo.size() != 0){
            Toast.makeText(this, "Não há nenhum veículo cadastrado para efetuar a pesquisa!", Toast.LENGTH_SHORT).show();
            validadorVeiculo=false;
        }
    } catch (Exception e){
            Toast.makeText(this, "Erro ao buscar dados no Banco de dados!", Toast.LENGTH_SHORT).show();
        }
    }

    public void atribuirServicosSpinner(){
        try{

            ArrayList<String> servicosSpinner = new ArrayList<String>();
            servicosSpinner.add("Selecione um serviço");

            getAllServicos = popularServicos();
            if(getAllServicos.size() != 0){
                for (int i=0; i<getAllServicos.size(); i++) {
                    servicosSpinner.add(getAllServicos.get(i).getDescricao());
                }
            }else{
                throw new Exception("Não há serviços cadastrados no banco de dados");
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, servicosSpinner);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            servicosAAdicionar.setAdapter(arrayAdapter);
            servicosAAdicionar.setOnItemSelectedListener(this);

        }catch (Exception e){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Erro");
            alerta.setMessage("" + e.getMessage());
            alerta.setNeutralButton("OK", null);
            alerta.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if(text != "Selecione um serviço"){
            servicoSelecionado = text;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void closeKeyboard(){
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }

        public ArrayList<Servico> popularServicos() {
            ArrayList<Servico> listServicos = new ArrayList<Servico>();

            listServicos.add(new Servico("Trocar Óleo", (0.5 * 60), 100));
            listServicos.add(new Servico("Reparo no pneu", (1 * 60), 50));
            listServicos.add(new Servico("Regulagem do motor", (3 * 60), 500));
            listServicos.add(new Servico("Alinhamento", (0.2 * 60), 50));
            listServicos.add(new Servico("Cambagem", (0.8 * 60), 60));

            return listServicos;
        }

        private void limpar(){
            outputPlaca.setText("Placa: ");
            outputMarca.setText("Marca: ");
            outputModelo.setText("Modelo:");
            outputAno.setText("Ano: ");

            ArrayList<Servico> listServicosVazio= new ArrayList<Servico>();
            ArrayAdapter adapterServico = new ServicoAdapter(this, listServicosVazio);
            ServicosAdicionados.setAdapter(adapterServico);
        }


}

