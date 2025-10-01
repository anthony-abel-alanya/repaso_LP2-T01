package com.practica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
@Table(name = "plataforma")
public class Plataforma {
    @Id
    private int id_plataforma;

    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String fabricante;

    @Override
    public String toString() {
        return nombre;
    }
}
