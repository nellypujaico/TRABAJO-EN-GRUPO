package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    ArrayList<Usuario> lista = null;
    EditText txtdestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        txtdestino = (EditText)findViewById(R.id.txtdestino);
        Intent objIntent = getIntent();
        Bundle objbundle = objIntent.getExtras();
        lista = (ArrayList<Usuario>) objbundle.getSerializable("dato");
        this.lista = lista;

        String acum = "";
        for(Usuario obj:lista){
            acum += obj.getUsuario() + "   ";
        }
        txtdestino.setText(acum);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}