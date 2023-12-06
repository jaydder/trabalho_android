package com.example.prova.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.prova.R;
import com.example.prova.db.UserDatabase;
import com.example.prova.model.Keys;
import com.example.prova.model.User;
import com.example.prova.model.UserDates;

import java.util.ArrayList;
import java.util.List;

public class KeyRegisterActivity extends AppCompatActivity {
    private static final int LENGTH = 11;
    EditText key;
    Button add;
    UserDatabase db;
    UserDates userdt = new UserDates();
    String types_keys = null;
    RadioGroup rg;
    String value_key;

    List<User> listkey;
    ListView ltView;
    ArrayAdapter<String> adapter;
    String[] keysUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_register);
        key = findViewById(R.id.chave);
        add = findViewById(R.id.add);
        rg = findViewById(R.id.radio_gp);
        db = new UserDatabase(this);



        listkey = db.my_keys(userdt.user.getId());
        keysUser = new String[listkey.size()];
        for (int i = 0; i < listkey.size(); i++) {
            keysUser[i] = "CPF: " + listkey.get(i).getCpf()
                        + "\nCelular: " + listkey.get(i).getCelular();
        }
        adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, keysUser);

        ltView = findViewById(R.id.my_keys);
        ltView.setAdapter(adapter);


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radio_celular = findViewById(R.id.radio_celular);
                RadioButton radio_cpf = findViewById(R.id.radio_cpf);

                if (radio_celular.isChecked()) {
                    types_keys = "celular";
                } else if (radio_cpf.isChecked()) {
                    types_keys = "cpf";
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    db.set_keys(userdt.user.getId(), types_keys, value_key);
                    Toast.makeText(getApplicationContext(),"Chave Adicionada com sucesso",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public boolean validate(){
        value_key = key.getText().toString();
        if (value_key.length() != LENGTH) {
            Toast.makeText(getApplicationContext(), "Chave incorreta", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}