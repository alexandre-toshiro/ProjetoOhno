package com.example.ohno11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btnLogout, btnConsultarCliente, btnOrcamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnLogout= findViewById(R.id.btnLogout);
        btnConsultarCliente= findViewById(R.id.btnConsultarCliente);
        btnOrcamento= findViewById(R.id.btnOrcamento);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        btnConsultarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarCliente();
            }
        });

        btnOrcamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarOrcamento();
            }
        });

    }
    @Override
    public void onBackPressed() {
    }

    public void logout(){
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }

    public void consultarCliente(){
        startActivity(new Intent(HomeActivity.this, ClienteActivity.class));
    }

    public void consultarOrcamento(){
        startActivity(new Intent(HomeActivity.this,  AtribuirActivity.class));
    }
}
