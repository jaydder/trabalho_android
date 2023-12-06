package com.example.prova.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prova.R;
import com.example.prova.db.UserDatabase;
import com.example.prova.model.User;
import com.example.prova.model.UserDates;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    UserDatabase login_db;
    EditText login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_db = new UserDatabase(this);
        Button btn_login = findViewById(R.id.LoginButton);

        btn_login.setBackgroundResource(com.google.android.material.R.color.material_dynamic_primary30);
    }

    public void Logar(View view) {
        login = findViewById(R.id.Logintext);
        EditText Password = findViewById(R.id.PasswordText);

        User user = login_db.login(login.getText().toString(), Password.getText().toString());

        if(Objects.nonNull(user)){
            Bundle bundle = new Bundle();
            bundle.putString("login",login.getText().toString());

            Intent intent = new Intent(this,DashBoardActivity.class);
            intent.putExtras(bundle);

            UserDates.user = user;

            startActivity(intent);

        }else{
            Toast.makeText(this,"Errou o usuário ou senha",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void Cadastrar(View view) {
        Intent cadastrar = new Intent(this, RegisterActivity.class);
        startActivity(cadastrar);
    }
}