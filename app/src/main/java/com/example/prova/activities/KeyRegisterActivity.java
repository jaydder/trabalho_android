package com.example.prova.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.prova.R;
import com.example.prova.db.UserDatabase;
import com.example.prova.model.UserDates;

public class KeyRegisterActivity extends AppCompatActivity {

    EditText key;
    Button add;
    UserDatabase db;

    String[] tipos = {"CPF", "Celular"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_register);
        Log.d("Dash", "Cadastrar");
        key = findViewById(R.id.chave);
        add = findViewById(R.id.add);
        db = new UserDatabase(this);

        Spinner spinner = findViewById(R.id.spinnerTipo);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                        tipos);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDates userdt = new UserDates();
                String types_keys = null;
                String value_key = key.getText().toString();

                if (spinner.getSelectedItem().equals("CPF")){
                    types_keys = "cpf";
                } else {
                    types_keys = "celular";
                }

                db.set_keys(userdt.user.getId(), types_keys, value_key);
            }
        });
    }
}