package com.example.proyecto.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DBUsuario extends DBHelper{

    Context context;

    public DBUsuario(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public long insertaUsuario(String correo, String contrasena){
        long id = 0;

        try {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("correo", correo);
            values.put("contraseña", contrasena);

            id = db.insert(TABLE_LOGIN, null, values);
        } catch (Exception ex){
            ex.toString();
        }

        return id;
    }
}
