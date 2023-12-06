package com.example.prova.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.prova.R;
import com.example.prova.db.UserDatabase;
import com.example.prova.model.Historic;
import com.example.prova.model.UserDates;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    UserDatabase db;
    UserDates userdt;
    List<Historic> list_historic;
    ListView ltView;
    ArrayAdapter<String> adapter;
    String[] keysUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        db = new UserDatabase(this);
        userdt = new UserDates();

        list_historic = db.list_historic(userdt.user.getId());
        keysUser = new String[list_historic.size()];
        for (int i = 0; i < list_historic.size(); i++) {
            keysUser[i] =
                      "Tipo: " + list_historic.get(i).getType()
                    + "\n Valor: " + list_historic.get(i).getValeu()
                    + "\n Account: " + list_historic.get(i).getAccount();
        }
        adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, keysUser);

        ltView = findViewById(R.id.list_fav);
        ltView.setAdapter(adapter);
    }
}