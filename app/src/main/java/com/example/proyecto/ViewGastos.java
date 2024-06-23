package com.example.proyecto;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyecto.Metodos.GastoAdapter;  // Ajusta el paquete según tu estructura de paquetes

import com.example.proyecto.Metodos.Gasto;
import com.example.proyecto.auth_user.DBHelper_auth;

import java.util.List;

public class ViewGastos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GastoAdapter gastoAdapter;
    private DBHelper_auth dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gastos);

        recyclerView = findViewById(R.id.recycler_view_gastos);
        dbHelper = new DBHelper_auth(this);

        // Obtener userId del Intent
        int userId = getIntent().getIntExtra("userId", -1);

        if (userId == -1) {
            // Manejar el caso donde no se recibe userId correctamente
            finish();
            return;
        }

        // Configurar RecyclerView y Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gastoAdapter = new GastoAdapter(this);
        recyclerView.setAdapter(gastoAdapter);

        // Obtener y mostrar gastos del usuario
        List<Gasto> listaGastos = dbHelper.obtenerGastos(userId);
        gastoAdapter.setGastos(listaGastos);
    }
}
