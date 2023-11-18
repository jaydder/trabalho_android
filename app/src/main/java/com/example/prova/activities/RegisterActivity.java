package com.example.prova.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prova.R;
import com.example.prova.db.UserDatabase;
import com.example.prova.model.User;

public class CadastroActivity extends AppCompatActivity {

    EditText name;
    EditText password;
    Button login;
    UserDatabase lg_db;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        login = findViewById(R.id.cadastrar);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_text = name.getText().toString();
                String password_text = password.getText().toString();

                lg_db = new UserDatabase(getApplicationContext());
                user = new User();
                user.setName(name_text);
                user.setPassword(password_text);
                if (validation()) {
                    lg_db.add_user(user);
                    Toast.makeText(getApplicationContext(), "Usuario: " + name_text + " cadastrado com sucesso", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    public boolean validation(){
        String name_text = name.getText().toString();
        String password_text = password.getText().toString();

        if (name_text.isEmpty() || password_text.isEmpty()){
            Toast.makeText(getApplicationContext(),"Não pode haver compos vazios", Toast.LENGTH_LONG ).show();
            return false;
        }
        return true;
    }
}