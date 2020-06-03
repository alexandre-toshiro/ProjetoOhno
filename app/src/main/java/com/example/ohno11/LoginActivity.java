package com.example.ohno11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    boolean showPassword;
    Button btnLogin;
    EditText emailLogin, senhaLogin;
    ImageView imgShowPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        emailLogin = (EditText) findViewById(R.id.emailLogin);
        senhaLogin = (EditText) findViewById(R.id.senhaLogin);
        imgShowPassword = (ImageView) findViewById(R.id.showPassword);

        imgShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPassword();
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){
        try {
            String email = emailLogin.getText().toString();
            String senha = senhaLogin.getText().toString();

            if(email.equals("admin@ohno.com") && senha.equals("admin")){
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }else{
                Toast.makeText(LoginActivity.this, "Email ou senha inválido",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(LoginActivity.this, "Erro: " + e, Toast.LENGTH_LONG).show();
        }
    }


    private void showPassword(){
        if(showPassword == false){
            imgShowPassword.setImageResource(R.drawable.open_eye);
            senhaLogin.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        }else{
            imgShowPassword.setImageResource(R.drawable.closed_eye);
            senhaLogin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }

        if(showPassword == true){
            showPassword = false;
        }else{
            showPassword = true;
        }
    }
}
