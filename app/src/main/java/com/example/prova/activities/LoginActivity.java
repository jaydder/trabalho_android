package com.example.prova.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prova.R;
import com.example.prova.db.UserDatabase;
import com.example.prova.model.User;
import com.example.prova.model.UserDates;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    UserDatabase login_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_db = new UserDatabase(this);
    }

    public void Logar(View view) {
        EditText Login = findViewById(R.id.Logintext);
        EditText Password = findViewById(R.id.PasswordText);

        User user = login_db.login(Login.getText().toString(), Password.getText().toString());

        if(Objects.nonNull(user)){
            Bundle bundle = new Bundle();
            bundle.putString("login",Login.getText().toString());

            Intent intent = new Intent(this,DashBoardActivity.class);
            intent.putExtras(bundle);

          //  login_db.get_user_dates(Login.getText().toString());
            UserDates.user = user;

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