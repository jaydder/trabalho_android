package com.example.prova.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prova.R;
import com.example.prova.db.UserDatabase;
import com.example.prova.model.UserDates;

public class TransferActivity extends AppCompatActivity {

    Button deposit;
    Button transfer;
    TextView balance;
    EditText value;
    EditText key;
    UserDatabase userDB;
    UserDates userDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        balance = findViewById(R.id.balance);
        deposit = findViewById(R.id.button_deposit);
        transfer = findViewById(R.id.button_transfer);
        value = findViewById(R.id.field_value);
        key = findViewById(R.id.field_key);

        userDT = new UserDates();
        userDB = new UserDatabase(this);

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_value = value.getText().toString();
                userDB.deposit(userDT.user.getId(),"deposit" ,text_value);
            }
        });

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key_value = key.getText().toString();
                String text_value = value.getText().toString();
                if (userDB.validade_key(key_value)){
                    userDB.transfer(text_value,"transfer" ,key_value, userDT.user.getId());
                } else {
                    Toast.makeText(getApplicationContext(), "Chave nao cadastrada", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Double balance_db = userDB.get_balance(userDT.user.getId());
                balance.setText(balance_db.toString());
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
    }
}