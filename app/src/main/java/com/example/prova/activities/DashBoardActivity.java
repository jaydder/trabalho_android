package com.example.prova.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prova.R;

public class DashBoardActivity extends AppCompatActivity {

    Button btn_register_key;
    Button btn_tranfer;
    Button btn_show_keys;
    Button btn_fav_keys;
    Button btn_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btn_register_key = findViewById(R.id.buttonRegisterKey);
        btn_tranfer = findViewById(R.id.buttonTranfer);
        btn_show_keys = findViewById(R.id.show_keys);
        btn_fav_keys = findViewById(R.id.favorite_keys);
        btn_history = findViewById(R.id.button_history);

        TextView wel = findViewById(R.id.welcome);
        String login = (String) getIntent().getSerializableExtra("login");

        wel.setText("Bem vindo " + login);

        btn_register_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_screen(KeyRegisterActivity.class);
            }
        });

        btn_tranfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_screen(TransferActivity.class);
            }
        });

        btn_show_keys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_screen(ShowKeysActivity.class);
            }
        });

        btn_fav_keys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_screen(FavoritesKeysActivity.class);
            }
        });

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_screen(HistoryActivity.class);
            }
        });


    }

    public void change_screen(Class activity) {
        Intent i = new Intent(getApplicationContext(), activity);
        startActivity(i);
    }
}