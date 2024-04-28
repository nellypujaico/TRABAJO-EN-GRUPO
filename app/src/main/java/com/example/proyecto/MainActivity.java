package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Usuario objUsuario = null;
    ArrayList<String> listaIngreso;
    ArrayList<Usuario> listaIdentidad;
    Button btnIngresar;
    EditText txtUsuario, txtContrasena;

    Button btn_registrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txtUsuario = (EditText)findViewById(R.id.txtUsuario);
        txtContrasena = (EditText)findViewById(R.id.txtContrasena);
        btnIngresar = (Button)findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);
        listaIngreso = cargar();
        listaIdentidad = new ArrayList<Usuario>();
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

    public ArrayList<String> cargar(){
        listaIngreso = new ArrayList<String>();
        String obj1 = new String("INGRESAR");
        listaIngreso.add(obj1);

        return listaIngreso;
    }

    @Override
    public void onClick(View v) {
        String texUsuario = txtUsuario.getText().toString();
        String usuario = "diego";
        if(texUsuario.equals(usuario)){
            if(v == btnIngresar){
                registrar();
                Intent objintent = new Intent(MainActivity.this, Menu.class);
                Bundle objbundle = new Bundle();
                        objbundle.putSerializable("dato", listaIdentidad);
                        objintent.putExtras(objbundle);
                startActivity(objintent);
                finish();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Usuario o Contraseña incorrecto", Toast.LENGTH_LONG).show();
        }
    }

    public void registrar(){
        String texUsuario = txtUsuario.getText().toString();
        objUsuario = new Usuario();
        objUsuario.setUsuario(texUsuario);
        listaIdentidad.add(objUsuario);
    }
}