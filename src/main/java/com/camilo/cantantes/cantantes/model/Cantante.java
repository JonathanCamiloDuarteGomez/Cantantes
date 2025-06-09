package com.camilo.cantantes.cantantes.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Hibernate
@Entity
@Table(name="cantantes")

public class Cantante {
    @Id
    @Column(name = "idCantante", unique = true, nullable = false)
    private int idCantante;
    private String nombre;
    private String pais_origen;
    private String genero_musical;
    private Boolean activo;

    // Relación uno a muchos con Cancion
    @OneToMany(mappedBy = "cantante", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Cancion> canciones = new ArrayList<>();

    public Cantante(){}

    public Cantante(int idCantante, String nombre, String pais_origen, String genero_musical, Boolean activo) {
        this.idCantante = idCantante;
        this.nombre = nombre;
        this.pais_origen = pais_origen;
        this.genero_musical = genero_musical;
        this.activo = activo;
    }

    public int getidCantante() {
        return idCantante;
    }

    public void setidCantante(int idCantante) {
        this.idCantante = idCantante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getPais_origen() {
        return pais_origen;
    }

    public void setPais_origen(String pais_origen) {
        this.pais_origen = pais_origen;
    }

    public String getGenero_musical() {
        return genero_musical;
    }

    public void setGenero_musical(String genero_musical) {
        this.genero_musical = genero_musical;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    @Override
    public String toString() {
        return "Cantante: " +
                "idCantante=" + idCantante +
                ", nombre='" + nombre + '\'' +
                ", pais_origen='" + pais_origen + '\'' +
                ", genero_musical='" + genero_musical + '\'' +
                ", activo=" + activo;
    }
}
