package com.example.prova.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.example.prova.R;
import com.example.prova.db.UserDatabase;
import com.example.prova.model.Keys;
import com.example.prova.model.UserDates;

import java.util.List;

public class FavoritesKeysActivity extends AppCompatActivity {

    UserDatabase db;
    UserDates userdt;
    List<Keys> listkey;
    ListView ltView;
    ArrayAdapter<String> adapter;
    String[] keysUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_keys);

        db = new UserDatabase(this);
        userdt = new UserDates();

        listkey = db.list_all_keys(userdt.user.getId());
        keysUser = new String[listkey.size()];
        for (int i = 0; i < listkey.size(); i++) {
            keysUser[i] = "Chave: " + listkey.get(i).key;
        }
        adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, keysUser);

        ltView = findViewById(R.id.list_fav);
        ltView.setAdapter(adapter);

    }
}