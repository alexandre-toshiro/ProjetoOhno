package com.example.ohno11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ohno11.DAO.ServicoDAO;
import com.example.ohno11.backend.Cliente;
import com.example.ohno11.backend.Servico;
import com.example.ohno11.backend.Veiculo;
import java.text.DecimalFormat;
import com.example.ohno11.dataBase.BDCore;

import java.util.ArrayList;

public class OrdemDeServicoActivity extends AppCompatActivity {


    private static String placaBd;
    private static ArrayList<Servico> servicosOS = new ArrayList<Servico>();

    SQLiteDatabase db;
    BDCore auxBd = new BDCore(OrdemDeServicoActivity.this);

    ImageView btnvoltarOS;
    Cliente clienteOS;
    Veiculo veiculoOS;
    TextView nome, cpf, telefone, placa, marca, modelo, ano, outputTempoOS, outputValorOS;
    ListView listView;
    Button confirmar;
    String placaBD;
    ArrayList<Servico> servicos = new ArrayList<Servico>();
    double totalValor=0, totalTempo=0;
    AtribuirActivity infos = new AtribuirActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordem_de_servico);
        btnvoltarOS = (ImageView) findViewById(R.id.btnVoltarOS);
        clienteOS = new Cliente();
        veiculoOS = new Veiculo();
        nome = (TextView) findViewById(R.id.outputNomeOS);
        cpf = (TextView) findViewById(R.id.outputCpfOS);
        telefone = (TextView) findViewById(R.id.outputTelefoneOS);
        placa = (TextView) findViewById(R.id.outputPlacaOS);
        marca = (TextView) findViewById(R.id.outputMarcaOS);
        modelo = (TextView) findViewById(R.id.outputModeloOS);
        ano = (TextView) findViewById(R.id.outputAnoOS);
        outputTempoOS = (TextView) findViewById(R.id.outputTempoOS);
        outputValorOS = (TextView) findViewById(R.id.outputValorOS);
        confirmar = (Button) findViewById(R.id.btnConfirmarOS);
        placaBD = placaBd;
        servicos = servicosOS;

        listView = (ListView) findViewById(R.id.listViewServicosOS);

        receberDados();

        btnvoltarOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrdemDeServicoActivity.this, HomeActivity.class));
            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                db = auxBd.getWritableDatabase();

                    for(int i=0; servicosOS.size() >i; i++){
                        db.execSQL("insert into servico values ('" + servicosOS.get(i).getDescricao() + "'," + servicosOS.get(i).getTempoDuracao() + " , " + servicosOS.get(i).getValor() + ", '" + placaBD + "')\n");
                    }
                    Toast.makeText(OrdemDeServicoActivity.this, "Ordem de serviço emitida", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OrdemDeServicoActivity.this, HomeActivity.class));

                }catch (Exception e){
                    Toast.makeText(OrdemDeServicoActivity.this, "Erro ao salvar no banco de dados", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //public void receberDados(Cliente cliente, Veiculo veiculo, ArrayList<Servico> servicos){
    public void receberDados(){

        String[] colunasCliente = {"nome", "cpf", "telefone"};
        String[] colunasVeiculo = {"ano", "marca", "modelo"};

        Cursor cursor = auxBd.getWritableDatabase().query("cliente", colunasCliente, "cpf = (select foreignKeyCliente from veiculo where placa ='"+placaBD+"')" , null, null, null, null);
        //enquanto houver registros, adiciona a pessoa a listagem
        while (cursor.moveToNext()) {

            nome.setText("Nome: "+cursor.getString(0));
            cpf.setText("CPF: "+ cursor.getString(1));
            telefone.setText("Telefone: "+ cursor.getString(2));
            placa.setText("Placa: "+placaBd);

        }
        cursor.close();

        Cursor cursorVeiculo = auxBd.getWritableDatabase().query("veiculo", colunasVeiculo, "placa ='"+placaBD+"'" , null, null, null, null);

        while (cursorVeiculo.moveToNext()) {
            marca.setText("Marca: "+cursorVeiculo.getString(1));
            modelo.setText("Modelo: "+cursorVeiculo.getString(2));
            ano.setText("Ano: "+cursorVeiculo.getString(0));
        }

        for(int i=0; servicosOS.size() >i; i++){
            totalTempo = (totalTempo+servicosOS.get(i).getTempoDuracao());
            totalValor = (totalValor+servicosOS.get(i).getValor());
        }


        outputTempoOS.setText("Tempo: " +  ((int) totalTempo) +" m(s)");
        outputValorOS.setText("Valor: R$" +  ((int) totalValor)+",00");


        ArrayAdapter adapter = new ServicoAdapter(this, servicosOS);
        listView.setAdapter(adapter);
    }

    public static void  getplaca (String placa){
        placaBd = placa;
    }

    public static void  getServico (ArrayList<Servico> servico){
        servicosOS = servico;
    }

}
