package com.example.proyecto.Metodos;

public class Gasto {
    private int id;
    private int userId;
    private String fecha;
    private double cantidadGasto;
    private int idCategoria;
    private String descripcion;

    public Gasto(int id, int userId, String fecha, double cantidadGasto, int idCategoria, String descripcion) {
        this.id = id;
        this.userId = userId;
        this.fecha = fecha;
        this.cantidadGasto = cantidadGasto;
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getFecha() {
        return fecha;
    }

    public double getCantidadGasto() {
        return cantidadGasto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Gasto{" +
                "id=" + id +
                ", userId=" + userId +
                ", fecha='" + fecha + '\'' +
                ", cantidadGasto=" + cantidadGasto +
                ", idCategoria=" + idCategoria +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
