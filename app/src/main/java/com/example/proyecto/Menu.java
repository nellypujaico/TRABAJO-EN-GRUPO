package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class Menu extends AppCompatActivity {

    Button btnRegistrarGasto, btnGestionIngresos, btnCategorizacion, btnInformes, btnAlertas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        btnRegistrarGasto = (Button)findViewById(R.id.btnRegistrarGasto);
        btnRegistrarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(Menu.this,RegistroGasto.class);
                Menu.this.startActivity(intentReg);
            }
        });

        btnGestionIngresos = (Button)findViewById(R.id.btnGestionIngresos);
        btnGestionIngresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(Menu.this,GestionIngresos.class);
                Menu.this.startActivity(intentReg);
            }
        });

        btnCategorizacion = (Button)findViewById(R.id.btnCategorizacion);
        btnCategorizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(Menu.this,CategorizacionGastos.class);
                Menu.this.startActivity(intentReg);
            }
        });

        btnInformes = (Button)findViewById(R.id.btnInformes);
        btnInformes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(Menu.this,InformesGastos.class);
                Menu.this.startActivity(intentReg);
            }
        });

        btnAlertas = (Button)findViewById(R.id.btnAlertas);
        btnAlertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(Menu.this,AlertasRecordatorios.class);
                Menu.this.startActivity(intentReg);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}