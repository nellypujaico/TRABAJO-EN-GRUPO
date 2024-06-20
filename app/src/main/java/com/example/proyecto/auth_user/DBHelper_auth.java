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
    public boolean insertarGasto(int id, String fecha, double costo, int id_categoria, String descripcion){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userid", id);
        values.put("fecha", fecha);
        values.put("cantidad_gasto", costo);
        values.put("id_categoria", id_categoria);
        values.put("descripcion", descripcion);
        long result = db.insert("gastos", null, values);
        if (result == -1) return false;
        return true;
    }
    public int obtenerIdCategoria(String nombreCategoria, String tipoCategoria) {
        SQLiteDatabase db = this.getReadableDatabase();
        int idCategoria = -1;

        String query = "SELECT id_categoria FROM categoria WHERE nombre = ? AND tipo = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nombreCategoria, tipoCategoria});

        if (cursor.moveToFirst()) {
            idCategoria = cursor.getInt(0);
        }

        cursor.close();
        return idCategoria;
    }
}
