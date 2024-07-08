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

    public String seleccionarCategoria(int categoria){
        String categoriaID = "";
        switch (categoria){
            case 0: categoriaID = "Comestibles"; break;
            case 1: categoriaID = "Restaurantes"; break;
            case 2: categoriaID = "Bebidas"; break;
            case 3: categoriaID = "Matrícula"; break;
            case 4: categoriaID = "Materiales escolares"; break;
            case 5: categoriaID = "Cursos"; break;
            case 6: categoriaID = "Cine"; break;
            case 7: categoriaID = "Conciertos"; break;
            case 8: categoriaID = "Recreativos"; break;
            case 9: categoriaID = "Consultas médicas"; break;
            case 10: categoriaID = "Medicamentos"; break;
            case 11: categoriaID = "Seguros médicos"; break;
            case 12: categoriaID = "Transporte"; break;
            case 13: categoriaID = "Mantenimiento"; break;
            case 14: categoriaID = "Viajes"; break;
            case 15: categoriaID = "Alquiler"; break;
            case 16: categoriaID = "Servicios públicos"; break;
            case 17: categoriaID = "Mantenimiento"; break;
            case 18: categoriaID = "Ropa"; break;
            case 19: categoriaID = "Calzado"; break;
            case 20: categoriaID = "Accesorios"; break;
            case 21: categoriaID = "Alojamiento"; break;
            case 22: categoriaID = "Transporte publico"; break;
            case 23: categoriaID = "Turismo"; break;
            case 24: categoriaID = "Otros"; break;
        }
        return categoriaID;
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
