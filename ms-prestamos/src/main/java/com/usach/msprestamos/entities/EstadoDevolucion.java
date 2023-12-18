package com.usach.msprestamos.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estado_devolucion")
@Setter
@Getter
@NoArgsConstructor
public class EstadoDevolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "descripcion")
    String descripcion;

    public EstadoDevolucion(Integer id) {
        this.id = id;
    }
}
