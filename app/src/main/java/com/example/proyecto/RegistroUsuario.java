package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class RegistroUsuario extends AppCompatActivity implements View.OnClickListener{

    ArrayList<Usuario> listaRegistro;
    Usuario objusuario = null;
    EditText txtusuarioR, txtcontrasenaR, txtcontrasenaRC;
    Button btnRegistrar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_usuario);

        txtusuarioR = (EditText)findViewById(R.id.txtusuarioR);
        txtcontrasenaR = (EditText)findViewById(R.id.txtcontrasenaR);
        txtcontrasenaRC = (EditText)findViewById(R.id.txtcontrasenaRC);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);
        btnRegresar = (Button)findViewById(R.id.btnRegresar);
        listaRegistro = new ArrayList<Usuario>();

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresar();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void registro(){
        String texUsuario = txtusuarioR.getText().toString();
        String textcontrasena = txtcontrasenaR.getText().toString();

        objusuario= new Usuario();
        objusuario.setUsuario(texUsuario);
        objusuario.setContrasena(textcontrasena);
        listaRegistro.add(objusuario);

        txtusuarioR.setText("");
        txtcontrasenaR.setText("");
        txtcontrasenaRC.setText("");
    }
    public void regresar(){
        Intent objIntent = new Intent(RegistroUsuario.this,MainActivity.class);
        Bundle objbundle = new Bundle();
        objbundle.putSerializable("datos", listaRegistro);
        objIntent.putExtras(objbundle);
        startActivity(objIntent);
        finish();
    }

    @Override
    public void onClick(View v) {
        String texcontrasenaC = txtcontrasenaRC.getText().toString();
        String texcontrasena = txtcontrasenaR.getText().toString();
        if(texcontrasena.equals(texcontrasenaC)){
            if(v == btnRegistrar){
                registro();
            }
        }else{
            Toast.makeText(getApplicationContext(),"La contraseña ingresa no coincide", Toast.LENGTH_LONG).show();
        }
    }
}