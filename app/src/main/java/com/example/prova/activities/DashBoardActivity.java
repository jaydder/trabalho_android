package com.example.prova.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prova.R;

public class DashBoardActivity extends AppCompatActivity {

    Button cadPix;
    Button btn_tranfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        cadPix = findViewById(R.id.buttonCadPix);
        btn_tranfer = findViewById(R.id.buttonTranfer);
        TextView wel = findViewById(R.id.welcome);
        String login = (String) getIntent().getSerializableExtra("login");

        wel.setText("Bem vindo " + login);
        cadPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CadastrarChaveActivity.class);
                startActivity(i);
                Log.d("Dash","Intent");
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