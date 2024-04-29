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
import java.util.List;

public class MainActivity extends AppCompatActivity{
    ArrayList<String> listaIngreso = new ArrayList<>();
    ArrayList<Usuario> listaRegistrada = null;
    Button btnIngresar, btn_registrar;
    EditText txtUsuario, txtContrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txtUsuario = (EditText)findViewById(R.id.txtUsuario);
        txtContrasena = (EditText)findViewById(R.id.txtContrasena);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIn = getIntent();
                Bundle objbundle = objIn.getExtras();
                listaRegistrada = (ArrayList<Usuario>) objbundle.getSerializable("datos");
                String usuario = "", contra = "";
                for (Usuario obj:listaRegistrada){
                    usuario = obj.getUsuario();
                    contra = obj.getContrasena();
                }
                String texUsuario = txtUsuario.getText().toString();
                String texContrasena = txtContrasena.getText().toString();
                if(texUsuario.equals(usuario) && texContrasena.equals(contra)){
                    Intent objintent = new Intent(MainActivity.this,Menu.class);
                    MainActivity.this.startActivity(objintent);
                }else{
                    Toast.makeText(getApplicationContext(),"Usuario o Contraseña incorrecto", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_registrar=(Button) findViewById(R.id.btn_registrar);
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(MainActivity.this,RegistroUsuario.class);
                MainActivity.this.startActivity(intentReg);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}