package com.example.prova.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.prova.model.User;

public class UserDatabase extends SQLiteOpenHelper {
    User user = null;
    final static String DATABASE_NAME = "PIX_DB";
    final static int VERSION = 1;

    public UserDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    String sql;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         sql = "CREATE TABLE user(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "cpf TEXT," +
                "celular TEXT)";
        sqLiteDatabase.execSQL(sql);

         sql = "CREATE TABLE balance(" +
                "userID INTEGER," +
                "balance TEXT  NOT NULL)";
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

        String sql = "SELECT * FROM user " +
                "WHERE name = '" + name + "' AND " +
                "password = '" + password + "'";

        Cursor cursor = getReadableDatabase()
                .rawQuery(sql, null);

        while (cursor.moveToNext()){
            user = new User();
            user.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("password")));
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
        }
        return user;
    }

    public void set_keys(int id, String column, String key_value) {
        String sql = "UPDATE user SET '" +
                column + "'='" + key_value +
                "' where id ='" + id + "'";
        getWritableDatabase().execSQL(sql);
    }

    public void deposit(int id, String value){
        sql = "INSERT INTO balance(userID, balance)" +
        "VALUES("+ id +",'"+ value +"');";

        getWritableDatabase().execSQL(sql);
    }

    public double get_balance(int id) {
        sql = "SELECT SUM(balance) as TotalBalance FROM balance " +
              "WHERE userID = '"+ id +"'";

        Cursor cursor = getReadableDatabase()
                .rawQuery(sql, null);

        double total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getDouble(cursor.getColumnIndexOrThrow("TotalBalance"));
        }
        return total;
    }
}

