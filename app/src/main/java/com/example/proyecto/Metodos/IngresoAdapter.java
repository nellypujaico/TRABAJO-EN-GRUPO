package com.example.proyecto.Metodos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.DetalleGastoActivity;
import com.example.proyecto.R;

import java.util.List;

public class IngresoAdapter extends RecyclerView.Adapter<IngresoAdapter.IngresoViewHolder> {
    private Context context;
    private List<Ingreso> listaIngresos;

    public IngresoAdapter(Context context) {
        this.context = context;
    }

    // Método para actualizar la lista de gastos
    public void setIngresos(List<Ingreso> listaIngresos) {
        this.listaIngresos = listaIngresos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngresoAdapter.IngresoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout del item de gasto
        View view = LayoutInflater.from(context).inflate(R.layout.item_ingreso, parent, false);
        return new IngresoAdapter.IngresoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngresoAdapter.IngresoViewHolder holder, int position) {
        // Obtener el gasto en la posición actual
        Ingreso ingreso = listaIngresos.get(position);

        // Asignar valores a las vistas del ViewHolder
        holder.txtFecha.setText(ingreso.getFecha());
        holder.txtDescripcion.setText(ingreso.getDescripcion());
        holder.txtCantidad.setText(String.valueOf(ingreso.getCantidadIngreso()));

        // Manejar clic en el elemento del RecyclerView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al hacer clic, enviar a la actividad de detalle del gasto
                Intent intent = new Intent(context, DetalleGastoActivity.class);
                intent.putExtra("gastoId", ingreso.getId()); // Envía el ID del gasto o toda la información necesaria
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaIngresos != null ? listaIngresos.size() : 0;
    }

    // ViewHolder para mantener las referencias de las vistas de cada item de gasto
    public static class IngresoViewHolder extends RecyclerView.ViewHolder {

        TextView txtFecha, txtDescripcion, txtCantidad;

        public IngresoViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializar las vistas
            txtFecha = itemView.findViewById(R.id.txtFecha);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
            // Configura más vistas si es necesario
        }
    }
}
