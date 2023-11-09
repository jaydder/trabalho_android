package com.example.prova.db;

import android.adservices.customaudience.JoinCustomAudienceRequest;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.service.autofill.UserData;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.prova.model.User;
import com.example.prova.model.UserDates;

import java.util.List;

public class UserDatabase extends SQLiteOpenHelper {

    public UserDatabase(@Nullable Context context) {
        super(context, "PIX_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE user(" +
                "ID INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "cpf TEXT," +
                "celular TEXT)";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void add_user(User user) {
        String sql = "INSERT INTO user (name, password)" +
                " VALUES('" + user.getName() + "','" +
                user.getPassword() + "')";
        Log.i("db", "sql insert usuario: " + sql);
        getWritableDatabase().execSQL(sql);
    }

    public User login(String name, String password) {

        String sql = "select * from user " +
                " where name = '" + name + "' and " +
                " password = '" + password + "'";

        Cursor cursor = getReadableDatabase()
                .rawQuery(sql, null);
        User usuario = null;
        Log.d("DBp===", "usuario cirado");
        for(int i=0; i < cursor.getCount(); i++ ){
            usuario = new User();

            Log.d("DB",cursor.getString(1));
            usuario.setId(cursor.getInt(0));
            usuario.setName(cursor.getString(1));
            usuario.setPassword(cursor.getString(2));
            cursor.moveToNext();
        }

        return usuario;
    }

    public void set_keys(int id, String type, String key_value) {
        String sql = "UPDATE user SET '"+
                type + "'='" + key_value +
                "' where ID = "+ id ;
        Log.i("db", "sql insert usuario: " + sql);
        getWritableDatabase().execSQL(sql);
    }


}
