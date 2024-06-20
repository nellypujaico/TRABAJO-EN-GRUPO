package com.example.proyecto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyecto.auth_user.DBHelper_auth;

public class RegistroGasto extends AppCompatActivity {
    Context context;
    Spinner spnCategoria;
    EditText txtuserid, txtMonto, txtDescripcion, txtFecha;
    Button btnRegistroRegresar, btnRegistrarGasto;
    DBHelper_auth regasto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_gasto);

        spnCategoria = (Spinner)findViewById(R.id.spnCategoria);
        txtuserid = (EditText) findViewById(R.id.txtuserid);
        txtMonto = (EditText)findViewById(R.id.txtMonto);
        txtDescripcion = (EditText)findViewById(R.id.txtDescripcion);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
        btnRegistrarGasto = (Button)findViewById(R.id.btnRegistrarGasto);
        btnRegistroRegresar = (Button)findViewById(R.id.btnRegistroRegresar);
        regasto =new DBHelper_auth(this);
        btnRegistrarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(txtuserid.getText().toString());
                String fecha = txtFecha.getText().toString();
                double costo = Double.parseDouble(txtMonto.getText().toString());
                String descripcion = txtDescripcion.getText().toString();
                int idCategoria = Categoria();

                boolean registroExitoso =regasto.insertarGasto(id, fecha, costo, idCategoria, descripcion);
                if(registroExitoso){
                    Toast.makeText(RegistroGasto.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegistroGasto.this, "ERROR AL REGISTRAR", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnRegistroRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(RegistroGasto.this,Menu.class);
                RegistroGasto.this.startActivity(intentReg);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public int Categoria(){
        int idCate = spnCategoria.getSelectedItemPosition();
        return idCate + 1;
    }
}