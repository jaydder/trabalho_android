package com.example.prova.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.prova.model.User;

public class LoginDatabase extends SQLiteOpenHelper {

    public LoginDatabase(@Nullable Context context) {
        super(context, "PIX_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE user(" +
                "ID INTERGER PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "password TEXT NOT NULL)";
        sqLiteDatabase.execSQL(sql);


        sql = "INSERT INTO user(name,password)" +
                " VALUES('jaydder','admin')";

        Log.d("db", sql);
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void add_user(User user){
        String sql = "INSERT INTO user (name, password)" +
                " VALUES('" + user.getName() + "','" +
                user.getPassword() + "')";
        Log.i("db","sql insert usuario: " + sql);
        getWritableDatabase().execSQL(sql);
    }
    public boolean logar(String name,String password){

        String sql ="select * from user " +
                " where name = '" + name + "' and " +
                " password = '" + password +"'";

        Cursor cursor = getReadableDatabase()
                .rawQuery(sql,null);
        return cursor.getCount() > 0 ? true : false;
    }
}

