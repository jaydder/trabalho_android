package com.example.prova.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.prova.R;
import com.example.prova.db.UserDatabase;
import com.example.prova.model.User;
import com.example.prova.model.UserDates;

import java.util.List;

public class CadastrarChaveActivity extends AppCompatActivity {

    EditText chave;
    Button add;

    UserDatabase db;

    String[] tipos = {"CPF", "Celular"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_chave);
        Log.d("Dash", "Cadastrar");
        chave = findViewById(R.id.chave);
        add = findViewById(R.id.add);
        db = new UserDatabase(this);

        String chave_text = chave.getText().toString();


        Spinner spinner = findViewById(R.id.spinnerTipo);

        ArrayAdapter aa = new ArrayAdapter(this,
                        android.R.layout.simple_spinner_item,
                        tipos);

        aa.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        User user = new User();

        UserDates userdt = new UserDates();
        String types_keys = null;
        String value_key = null;

        if (spinner.getSelectedItem().equals("CPF")){
            types_keys = "cpf";
            value_key = chave_text;
        } else {
            types_keys = "celular";
            value_key = chave_text;
        }

        db.set_keys(userdt.user.getId(), types_keys, value_key);







    }
}