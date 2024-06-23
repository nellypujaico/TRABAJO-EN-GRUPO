package com.example.proyecto.auth_user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.proyecto.Metodos.Gasto;

import java.util.ArrayList;
import java.util.List;

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
        onCreate(db);
    }

    public boolean inserData(String email, String password){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password",password);
        long result = db.insert("users", null,contentValues);
        return result != -1; // Simplified return statement
    }

    public boolean verificaremail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public Integer verificarUsuario(String email, String pwd){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT userid FROM users WHERE email = ? AND password = ?", new String[]{email, pwd});
        Integer userId = null;
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }
        cursor.close();
        return userId;
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
        return result != -1; // Simplified return statement
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

    public List<Gasto> obtenerGastos(int userId) {
        List<Gasto> listaGastos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM gastos WHERE userid = ?", new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id_gastos"));
                String fecha = cursor.getString(cursor.getColumnIndex("fecha"));
                double cantidadGasto = cursor.getDouble(cursor.getColumnIndex("cantidad_gasto"));
                int idCategoria = cursor.getInt(cursor.getColumnIndex("id_categoria"));
                String descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));

                Gasto gasto = new Gasto(id, userId, fecha, cantidadGasto, idCategoria, descripcion);
                listaGastos.add(gasto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listaGastos;
    }
    public Gasto obtenerGastoPorId(int gastoId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Gasto gasto = null;

        Cursor cursor = db.rawQuery("SELECT * FROM gastos WHERE id_gastos = ?", new String[]{String.valueOf(gastoId)});

        if (cursor.moveToFirst()) {
            int userId = cursor.getInt(cursor.getColumnIndex("userid"));
            String fecha = cursor.getString(cursor.getColumnIndex("fecha"));
            double cantidadGasto = cursor.getDouble(cursor.getColumnIndex("cantidad_gasto"));
            int idCategoria = cursor.getInt(cursor.getColumnIndex("id_categoria"));
            String descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));

            gasto = new Gasto(gastoId, userId, fecha, cantidadGasto, idCategoria, descripcion);
        }

        cursor.close();
        return gasto;
    }

    public boolean eliminarGasto(int gastoId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("gastos", "id_gastos = ?", new String[]{String.valueOf(gastoId)});
        return result > 0; // Devuelve true si se eliminó al menos una fila
    }

    public boolean actualizarGasto(int id, String nuevaFecha, String nuevaDescripcion, double nuevaCantidad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fecha", nuevaFecha);
        values.put("descripcion", nuevaDescripcion);
        values.put("cantidad_gasto", nuevaCantidad);

        int filasAfectadas = db.update("gastos", values, "id_gastos = ?", new String[]{String.valueOf(id)});

        return filasAfectadas > 0; // Devuelve true si se actualizó al menos una fila
    }


}

