package com.cenfotec.workshop.domain;

import org.springframework.data.annotation.Id;

public class Categoria {

    @Id
    private String id;
    private String nombreCategoria;

    public Categoria() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
