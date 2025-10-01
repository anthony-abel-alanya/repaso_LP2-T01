package com.practica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    private int id_categoria;

    @Column(length = 50)
    private String nombre;

    @Column(length = 100)
    private String descripcion;

    @Override
    public String toString() {
        return nombre;  // Para mostrar en combo
    }
}
