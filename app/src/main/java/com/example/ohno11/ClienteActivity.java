package com.example.ohno11;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ohno11.backend.Cliente;
import com.example.ohno11.backend.Veiculo;
import com.example.ohno11.backend.Servico;
import com.example.ohno11.dataBase.BDCore;

import java.util.ArrayList;


public class ClienteActivity extends AppCompatActivity {

    ImageView btnvoltar;
    ListView listViewVeiculos, listViewServicos;
    EditText cpf;
    String sCpf;
    Veiculo veiculo;
    Servico servico;
    TextView oPnome, oPcpf, oPtelefone;
    Button btnPesquisarCpf;
    ArrayList<Cliente> clientesCadastrados;
    private SQLiteDatabase db;
    BDCore auxBd = new BDCore(ClienteActivity.this);
    String placaCliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        db = auxBd.getWritableDatabase();
        btnvoltar = (ImageView) findViewById(R.id.btnVoltar);
        listViewVeiculos = (ListView) findViewById(R.id.listViewVeiculosConsultar);
        listViewServicos = (ListView) findViewById(R.id.listViewServicosConsultar);
        cpf = (EditText) findViewById(R.id.pesquisaCpf);
        oPnome = (TextView) findViewById(R.id.outputNome);
        oPcpf = (TextView) findViewById(R.id.outputCpf);
        oPtelefone = (TextView) findViewById(R.id.outputTelefone);
        btnPesquisarCpf = (Button) findViewById(R.id.btnBuscarCpf);
        db = auxBd.getWritableDatabase();

        listViewVeiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              try {

                ArrayList<Servico> listServicos = new ArrayList<Servico>();
                String[] colunas = {"descricao", "tempo", "valor"};
                Cursor cursor = auxBd.getWritableDatabase().query("servico", colunas, "placa = '" + placaCliente + "'", null, null, null, null);
                while (cursor.moveToNext()) {
                    Servico servico = new Servico();
                    servico.setDescricao(cursor.getString(0));
                    servico.setTempoDuracao(cursor.getInt(1));
                    servico.setValor(cursor.getDouble(2));
                    listServicos.add(servico);
                }
                if(cursor.moveToNext()){
                    Toast.makeText(ClienteActivity.this, "Não há serviços para este veículo!", Toast.LENGTH_LONG).show();
                }
                  ArrayAdapter adapter = new ServicoAdapter(ClienteActivity.this, listServicos);
                  listViewServicos.setAdapter(adapter);

            }catch(Exception e){
                  Toast.makeText(ClienteActivity.this, "Erro: " + e, Toast.LENGTH_LONG).show();
              }
            }

        });


        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClienteActivity.this, HomeActivity.class));
            }
        });


        btnPesquisarCpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();
                buscar();
            }
        });

    }

        public void buscar () {
        try {
            buscarCliente(cpf.getText().toString());
            closeKeyboard();
        } catch (Exception e) {
            Toast.makeText(ClienteActivity.this, "Erro: " + e, Toast.LENGTH_LONG).show();
        }
    }

    private void buscarCliente(String cpf) {
        Cliente cliente = new Cliente();
        ArrayList<Cliente> listClientes = new ArrayList<Cliente>();
        Veiculo veiculo = new Veiculo();
        ArrayList<Veiculo> listVeiculo= new ArrayList<Veiculo>();
        String[] colunas = {"nome","cpf","telefone", "marca", "modelo", "placa"};

        try {
            Cursor cursor = auxBd.getWritableDatabase().query("cliente, veiculo", colunas, "cliente.cpf=veiculo.foreignKeyCliente and cpf = '"+cpf+"'", null, null, null, null);

            //enquanto houver registros, adiciona a pessoa a listagem
            while (cursor.moveToNext()) {
                cliente.setNome(cursor.getString(0));
                cliente.setCpf(cursor.getString(1));
                cliente.setTelefone(cursor.getString(2));
                listClientes.add(cliente);

                veiculo.setMarca(cursor.getString(3));
                veiculo.setModelo(cursor.getString(4));
                veiculo.setPlaca(cursor.getString(5));
                placaCliente= veiculo.getPlaca();
                listVeiculo.add(veiculo);

            }

            cursor.close();

            if (listClientes.size() != 0) {
                for (int i = 0; i <= listClientes.size(); i++) {
                    if (listClientes.get(i).getCpf().equalsIgnoreCase(cpf)) {
                        oPnome.setText("Nome: " + listClientes.get(i).getNome());
                        oPcpf.setText("CPF: " + listClientes.get(i).getCpf());
                        oPtelefone.setText("Telefone: " + listClientes.get(i).getTelefone());

                        ArrayAdapter adapter = new VeiculoAdapter(this, listVeiculo);
                        listViewVeiculos.setAdapter(adapter);

                    } else if (!(listClientes.get(i).getCpf().equalsIgnoreCase(cpf))) {
                        Toast.makeText(this, "Nenhum cliente cadastrado correponde ao CPF digitado!", Toast.LENGTH_SHORT).show();
                    } else if (cpf == null || cpf.equalsIgnoreCase("")) {
                        Toast.makeText(this, "Digite um CPF válido!", Toast.LENGTH_SHORT).show();
                    }
                }


            } else if (listClientes.size() != 0) {
                Toast.makeText(this, "Não há nenhum cliente cadastrado para efetuar a pesquisa!", Toast.LENGTH_SHORT).show();

            }

        } catch (SQLException e) {
            Toast.makeText(this, "Erro ao buscar no Banco de Dados!", Toast.LENGTH_SHORT).show();}

        catch (Exception e) {
        }
    }


    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private void limpar(){
        oPnome.setText("Nome: ");
        oPcpf.setText("CPF: ");
        oPtelefone.setText("Telefone:");

        ArrayList<Veiculo> listVeiculoVazio= new ArrayList<Veiculo>();
        ArrayAdapter adapterVeiculo = new VeiculoAdapter(this, listVeiculoVazio);
        listViewVeiculos.setAdapter(adapterVeiculo);

        ArrayList<Servico> listServicosVazio= new ArrayList<Servico>();
        ArrayAdapter adapterServico = new ServicoAdapter(this, listServicosVazio);
        listViewServicos.setAdapter(adapterServico);
    }

}
