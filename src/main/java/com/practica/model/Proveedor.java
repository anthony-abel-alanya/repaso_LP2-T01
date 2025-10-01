package com.practica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    private int id_proveedor;

    @Column(length = 100)
    private String nombre;

    @Column(length = 15)
    private String telefono;

    @Column(length = 100)
    private String email;

    @Override
    public String toString() {
        return nombre;
    }
}
