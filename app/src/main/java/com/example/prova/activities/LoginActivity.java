package com.example.prova.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prova.R;
import com.example.prova.db.LoginDatabase;
import com.example.prova.model.User;

public class LoginActivity extends AppCompatActivity {

    LoginDatabase login_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        login_db = new LoginDatabase(this);
    }

    public void Logar(View view) {
        EditText Login = findViewById(R.id.Logintext);
        EditText Password = findViewById(R.id.PasswordText);
        boolean eLoginSucesso = login_db
                .logar(Login.getText().toString(),
                       Password.getText().toString());

        if(eLoginSucesso){
            Intent intent = new Intent(this,DashBoardActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(this,"Errou o usuário ou senha",
                    Toast.LENGTH_LONG).show();
        }


    }

    public void Cadastrar(View view) {
        Intent cadastrar = new Intent(this, CadastroActivity.class);
        startActivity(cadastrar);
    }
}