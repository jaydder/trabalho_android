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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btn_register_key = findViewById(R.id.buttonRegisterKey);
        btn_tranfer = findViewById(R.id.buttonTranfer);

        TextView wel = findViewById(R.id.welcome);
        String login = (String) getIntent().getSerializableExtra("login");

        wel.setText("Bem vindo " + login);

        btn_register_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), KeyRegisterActivity.class);
                startActivity(i);
            }
        });

        btn_tranfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TransferActivity.class);
                startActivity(i);
            }
        });
    }
}