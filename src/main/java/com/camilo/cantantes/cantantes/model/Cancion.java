package com.camilo.cantantes.cantantes.model;

import jakarta.persistence.*;

@Entity
@Table(name="canciones")
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cancion;
    private String titulo;
    private String duracion;
    private String genero;
    // Relación muchos a uno con Cantante (esta es la clave foránea)
    @ManyToOne
    @JoinColumn(name = "id_cantante")
    private Cantante cantante;
    public Cancion(){}

    public Cancion( String titulo, String duracion, String genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
    }

    public Long getId_cancion() {
        return id_cancion;
    }

    public void setId_cancion(Long id_cancion) {
        this.id_cancion = id_cancion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        genero = genero;
    }

    public Cantante getCantante() {
        return cantante;
    }

    public void setCantante(Cantante cantante) {
        this.cantante = cantante;
    }

    @Override
    public String toString() {
        return "Cancion " +
                "id_cancion = " + id_cancion +
                ", titulo ='" + titulo + '\'' +
                ", Duracion ='" + duracion + '\'' +
                ", Genero ='" + genero + '\'';
    }
}
