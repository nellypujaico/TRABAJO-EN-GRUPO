package com.example.proyecto;

import java.io.Serializable;

public class Usuario implements Serializable {
    String usuario;
    public String getUsuario() {

        return usuario;
    }

    public void setUsuario(String usuario) {

        this.usuario = usuario;
    }
}
