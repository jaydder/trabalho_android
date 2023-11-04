package com.example.prova;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;

import com.example.prova.db.LoginDatabase;
import com.example.prova.model.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        LoginDatabase login_db = new LoginDatabase(this);
        User user = new User();

        user.setName("admin");
        user.setPassword("admin");
        login_db.add_user(user);




    }
}