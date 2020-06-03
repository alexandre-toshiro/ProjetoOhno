package com.example.ohno11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ohno11.DAO.CargoDAO;
import com.example.ohno11.DAO.ClienteDAO;
import com.example.ohno11.DAO.EnderecoDAO;
import com.example.ohno11.DAO.FuncionarioDAO;
import com.example.ohno11.DAO.ServicoDAO;
import com.example.ohno11.DAO.VeiculoDAO;
import com.example.ohno11.backend.Cargo;
import com.example.ohno11.backend.Cliente;
import com.example.ohno11.backend.Endereco;
import com.example.ohno11.backend.Funcionario;
import com.example.ohno11.backend.Servico;
import com.example.ohno11.backend.Veiculo;
import com.example.ohno11.dataBase.DataBase;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {

            DataBase db = new DataBase(this);

            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    catch (Exception e){
        Toast.makeText(MainActivity.this, "Erro: "+e,Toast.LENGTH_LONG).show();
    }
    }


}
