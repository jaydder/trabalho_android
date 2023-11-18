package com.example.prova.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prova.R;
import com.example.prova.db.UserDatabase;
import com.example.prova.model.User;
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


        key.setVisibility(View.INVISIBLE);

        userDT = new UserDates();
        userDB = new UserDatabase(this);

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_value = value.getText().toString();
                userDB.deposit(userDT.user.getId(), text_value);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Double balance_db = userDB.get_balance(userDT.user.getId());
        balance.setText(balance_db.toString());
    }
}