package com.example.proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
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

    Button btnRegistrarGasto, btnGestionIngresos, btnCategorizacion, btnInformes, btnAlertas, btnSalir;

    @Override
    protected void onStart() {
        super.onStart();

        // Verificar si el usuario está autenticado
        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            // Si el usuario no está autenticado, redirigir a la pantalla de inicio de sesión
            Intent intent = new Intent(Menu.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        btnRegistrarGasto = (Button)findViewById(R.id.btnRegistrarGasto);
        btnRegistrarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(Menu.this, RegistroGasto.class);
                Menu.this.startActivity(intentReg);
            }
        });

        btnGestionIngresos = (Button)findViewById(R.id.btnGestionIngresos);
        btnGestionIngresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(Menu.this, GestionIngresos.class);
                Menu.this.startActivity(intentReg);
            }
        });

        btnCategorizacion = (Button)findViewById(R.id.btnCategorizacion);
        btnCategorizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(Menu.this, CategorizacionGastos.class);
                Menu.this.startActivity(intentReg);
            }
        });

        btnInformes = (Button)findViewById(R.id.btnInformes);
        btnInformes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(Menu.this, InformesGastos.class);
                Menu.this.startActivity(intentReg);
            }
        });

        btnAlertas = (Button)findViewById(R.id.btnAlertas);
        btnAlertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(Menu.this, AlertasRecordatorios.class);
                Menu.this.startActivity(intentReg);
            }
        });

        btnSalir = (Button)findViewById(R.id.Exit);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eliminar datos del usuario almacenados en SharedPreferences
                SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                // Redirigir a la pantalla de inicio de sesión
                Intent intentReg = new Intent(Menu.this, MainActivity.class);
                intentReg.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentReg);
                finish(); // Finaliza Menu activity para evitar que el usuario regrese a ella con el botón de retroceso
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
