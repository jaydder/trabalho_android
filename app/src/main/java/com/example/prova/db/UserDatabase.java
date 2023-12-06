package com.example.prova.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.prova.model.Historic;
import com.example.prova.model.Keys;
import com.example.prova.model.User;

import java.util.ArrayList;

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

        sql = "CREATE TABLE history(" +
                "userID INTEGER NOT NULL," +
                "type TEXT  NOT NULL," +
                "value TEXT NOT NULL," +
                "account TEXT)";

        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE pix(" +
                "userID INTEGER NOT NULL," +
                "key_pix TEXT  NOT NULL," +
                "favorite TEXT NOT NULL)";

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
        sql = "UPDATE user SET '" +
                column + "'='" + key_value +
                "' where id ='" + id + "'";
        getWritableDatabase().execSQL(sql);

    }

    public boolean validade_key(String key){
        sql = "SELECT * FROM pix " +
                "WHERE key_pix  = '" + key + "';";

        Cursor cursor = getReadableDatabase()
                .rawQuery(sql,null);
        return cursor.getCount() > 0 ? true : false;
    }

    public void deposit(int id, String type, String value){
        sql = "INSERT INTO history(userID, type, value)" +
                "VALUES(" +
                "'"+ id + "'," +
                "'"+ type  +"'," +
                "'"+ value +"')";

        getWritableDatabase().execSQL(sql);

        sql = "INSERT INTO balance(userID, balance)" +
        "VALUES("+ id +",'"+ value +"');";

        getWritableDatabase().execSQL(sql);
    }

    public void transfer(String value, String type, String key, int id) {
        sql = "INSERT INTO history(userID, type, value, account)" +
                "VALUES(" +
                "'"+ id + "'," +
                "'"+ type  +"'," +
                "'"+ value +"'," +
                "'"+ key + "'" +
                ")";

        sql = "INSERT INTO balance(userID, balance)" +
                "VALUES("+ id +",'-"+ value +"');";

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

    public void add_key(int id, String key, String fav) {
        sql = "INSERT INTO pix(userID, key_pix, favorite)" +
                "VALUES(" +
                "'"+ id + "',"+
                "'"+ key + "',"+
                "'"+ fav + "');";

        getWritableDatabase().execSQL(sql);
    }


    public ArrayList<User> my_keys(int id) {

        sql = "SELECT cpf,celular FROM user WHERE id = '"+ id +"'";

        ArrayList<User> lista = new ArrayList<>();

        Cursor cursor = getReadableDatabase()
                .rawQuery(sql,null);
        cursor.moveToFirst();

        for(int i=0; i < cursor.getCount(); i++ ){
            User user = new User();
            user.setCpf(cursor.getString(cursor.getColumnIndexOrThrow("cpf")));
            user.setCelular(cursor.getInt(cursor.getColumnIndexOrThrow("celular")));
            cursor.moveToNext();
            lista.add(user);
        }
        cursor.close();

        return lista;
    }


    public ArrayList<Keys> list_all_keys(int id) {

        sql = "SELECT * FROM pix WHERE userID = '"+ id +"'";

        ArrayList<Keys> lista = new ArrayList<>();

        Cursor cursor = getReadableDatabase()
                .rawQuery(sql,null);
        cursor.moveToFirst();

        for(int i=0; i < cursor.getCount(); i++ ){
            Keys key = new Keys();
            key.key = cursor.getString(cursor.getColumnIndexOrThrow("key_pix"));
            cursor.moveToNext();
            lista.add(key);
        }
        cursor.close();

        return lista;
    }
    public ArrayList<Keys> list_fav_keys(int id) {

        sql = "SELECT * FROM pix WHERE favorite='sim';";

        ArrayList<Keys> lista = new ArrayList<>();

        Cursor cursor = getReadableDatabase()
                .rawQuery(sql,null);
        cursor.moveToFirst();

        for(int i=0; i < cursor.getCount(); i++ ){
            Keys key = new Keys();
            key.key = cursor.getString(cursor.getColumnIndexOrThrow("key_pix"));
            cursor.moveToNext();
            lista.add(key);
        }
        cursor.close();

        return lista;
    }

    public void removeKey(int id, String key){
        sql = "DELETE FROM pix WHERE " +
                "userID = '"+ id +"' and " +
                "key_pix = '"+ key+"'";

        getWritableDatabase().execSQL(sql);

    }

    public ArrayList<Historic> list_historic(int id) {

        sql = "SELECT * FROM history WHERE userID='" + id + "';";

        ArrayList<Historic> lista = new ArrayList<>();

        Cursor cursor = getReadableDatabase()
                .rawQuery(sql, null);
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            Historic hs = new Historic();
            hs.setType(cursor.getString(cursor.getColumnIndexOrThrow("type")));
            hs.setValeu(cursor.getString(cursor.getColumnIndexOrThrow("value")));
            hs.setAccount(cursor.getString(cursor.getColumnIndexOrThrow("account")));
            cursor.moveToNext();
            lista.add(hs);
        }
        cursor.close();

        return lista;
        }
    }

