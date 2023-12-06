package com.example.prova.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.example.prova.R;
import com.example.prova.db.UserDatabase;
import com.example.prova.model.Keys;
import com.example.prova.model.User;
import com.example.prova.model.UserDates;

import java.util.List;

public class ShowKeysActivity extends AppCompatActivity {

    Button new_key;
    EditText key;
    UserDatabase db;
    UserDates userdt;
    List<Keys> listkey;
    CheckBox checkBox;
    String favorite = "nao";
    ListView ltView;
    ArrayAdapter<String> adapter;
    String[] keysUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_keys);

        new_key = findViewById(R.id.new_key);
        key = findViewById(R.id.key);
        checkBox = findViewById(R.id.fav_check);
        db = new UserDatabase(this);
        userdt = new UserDates();


        load_keys();

        new_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    favorite = "sim";
                }
                db.add_key(userdt.user.getId(), key.getText().toString(), favorite);
                load_keys();
            }
        });


        ltView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                dialog(position);
                return true;
            }
        });

    }

    public void dialog(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remover Chave")
                .setMessage("Tem certeza de que deseja remover esta chave?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String chaveRemovida = listkey.get(position).key;
                        db.removeKey(userdt.user.getId(),chaveRemovida);
                        load_keys();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void load_keys() {
        listkey = db.list_all_keys(userdt.user.getId());
        keysUser = new String[listkey.size()];
        for (int i = 0; i < listkey.size(); i++) {
            keysUser[i] = "Chave: " + listkey.get(i).key;
        }
        adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, keysUser);

        ltView = findViewById(R.id.list_view_key);
        ltView.setAdapter(adapter);

    }

}