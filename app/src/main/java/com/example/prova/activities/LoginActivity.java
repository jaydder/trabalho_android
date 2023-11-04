package com.example.prova.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.prova.R;
import com.example.prova.db.LoginDatabase;
import com.example.prova.model.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        LoginDatabase login_db = new LoginDatabase(this);
        User user = new User();

        user.setName("admin");
        user.setPassword("admin");
        login_db.add_user(user);


    }

    public void Logar(View view) {
        EditText Login = findViewById(R.id.Logintext);
        EditText Password = findViewById(R.id.PasswordText);


    }

    public void Cadastrar(View view) {
        Intent cadastrar = new Intent(this, CadastroActivity.class);
        startActivity(cadastrar);
    }
}