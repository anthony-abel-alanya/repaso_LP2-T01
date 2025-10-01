package com.practica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
@Table(name = "videojuego")
public class Videojuego {

    @Id
    @Column(length = 10)
    private String id_juego;

    @Column(length = 100)
    private String titulo;

    private double precio;

    private int stock;

    // Foreign keys
    private int id_categoria;
    private int id_plataforma;
    private int id_proveedor;

    // Relaciones ManyToOne (con insertable = false, updatable = false para evitar conflicto)
    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_plataforma", insertable = false, updatable = false)
    private Plataforma plataforma;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", insertable = false, updatable = false)
    private Proveedor proveedor;

    @Override
    public String toString() {
        return titulo;
    }
}
