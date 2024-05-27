package com.example.proyecto.auth_user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper_auth extends SQLiteOpenHelper {
    public static final String DBName = "contafacil.db";

    public DBHelper_auth(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(userid INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT UNIQUE NOT NULL, " +
                "password TEXT NOT NULL)");
        db.execSQL("CREATE TABLE categoria(id_categoria INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL," +
                "tipo TEXT NOT NULL)");
        db.execSQL("CREATE TABLE ingresos(id_ingresos INTEGER PRIMARY KEY AUTOINCREMENT, userid INTEGER,fecha DATE, " +
                "cantidad_ingresos DECIMAL, id_categoria INTEGER, " +
                "descripcion TEXT NOT NULL,FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria) ," +
                "FOREIGN KEY(userid) REFERENCES users(userid))");
        db.execSQL("CREATE TABLE gastos(id_gastos INTEGER PRIMARY KEY AUTOINCREMENT, userid INTEGER,fecha DATE, " +
                "cantidad_gasto DECIMAL,id_categoria INTEGER, descripcion TEXT NOT NULL," +
                "FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria)," +
                "FOREIGN KEY(userid) REFERENCES users(userid))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }

    public boolean inserData(String email, String password){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password",password);
        long result = db.insert("users", null,contentValues);
        if (result == -1) return false;
        else return true;
    }

    public boolean verificaremail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        if(cursor.getCount() > 0)
            return true;
        else return false;
    }
    public boolean verificarUsuario(String email,String pwd){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ? and password = ?", new String[]{email,pwd});
        if(cursor.getCount() > 0)
            return true;
        else return false;
    }
}
