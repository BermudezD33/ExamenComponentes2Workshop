package com.cenfotec.workshop.domain;

import org.springframework.data.annotation.Id;

public class Actividad {


    private String nombreActividad;
    private String descripcion;
    private String texto;
    private int tiempo;

    public Actividad() {
        super();
    }

    public Actividad(String nombreActividad, String descripcion, String texto, int tiempo) {
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
        this.texto = texto;
        this.tiempo = tiempo;
    }


    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "" +
                "  Nombre: '" + nombreActividad + '\n' + "   " +
                ", Descripcion: '" + descripcion + '\n' + "   " +
                ", Texto: '" + texto + '\n' + "   " +
                ", Tiempo: " + tiempo + '\n' + "   ";
    }
}
