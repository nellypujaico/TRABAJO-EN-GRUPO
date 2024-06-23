package com.example.proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.example.proyecto.Metodos.Gasto;
import com.example.proyecto.auth_user.DBHelper_auth;

import java.util.ArrayList;
import java.util.List;

public class InformesGastos extends AppCompatActivity {
    private AnyChartView anyChartView;
    private Button btnRegresar, btnTexto;
    private DBHelper_auth dbHelper;
    private int userId;
    private String[] tipoCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informes_gastos);

        // Initialize AnyChartView
        anyChartView = findViewById(R.id.any_chart_view);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnTexto = findViewById(R.id.btnTexto);
        // Initialize DBHelper_auth
        dbHelper = new DBHelper_auth(this);

        // Obtain the user ID dynamically (replace with your actual logic)
        userId = obtenerUserIdActual();

        // Load category names from resources
        tipoCategoria = getResources().getStringArray(R.array.tipoCategoria);

        // Update chart automatically when activity is created
        actualizarGrafico();

        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(InformesGastos.this, Menu.class);
            startActivity(intent);
        });
        btnTexto.setOnClickListener(v -> {
            Intent intent = new Intent(InformesGastos.this, MostrarTotalGastado.class);
            startActivity(intent);
        });
    }

    // Método para actualizar el gráfico con los gastos del usuario actual
    private void actualizarGrafico() {
        // Obtain expenses data for the current user from the database
        List<Gasto> listaGastos = dbHelper.obtenerGastos(userId);

        // Check if there are any expenses recorded
        if (listaGastos.isEmpty()) {
            Toast.makeText(InformesGastos.this, "No hay gastos registrados", Toast.LENGTH_SHORT).show();
            return;
        }

        // Prepare data for AnyChart
        Cartesian barChart = AnyChart.column();

        List<DataEntry> dataEntries = new ArrayList<>();
        for (Gasto gasto : listaGastos) {
            // Convert category ID to category name
            String categoriaNombre = tipoCategoria[gasto.getIdCategoria()];
            dataEntries.add(new ValueDataEntry(categoriaNombre, gasto.getCantidadGasto()));
        }

        // Configure the chart
        barChart.data(dataEntries);
        barChart.title("Gastos por categoría");
        barChart.xAxis(0).title("Categorías");
        barChart.yAxis(0).title("Cantidad Gasto");

        // Set the chart to AnyChartView
        anyChartView.setChart(barChart);
    }

    // Method to obtain the current user ID (replace with your actual logic)
    private int obtenerUserIdActual() {
        // Implement this function according to your authentication logic
        // For demonstration purposes, assuming user ID is stored in SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
        return preferences.getInt("userId", -1); // Replace -1 with default value if not found
    }
}



