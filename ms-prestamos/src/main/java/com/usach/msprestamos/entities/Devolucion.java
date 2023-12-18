package com.usach.msprestamos.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "devolucion")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Devolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "fecha")
    LocalDate fecha;

    @Column(name = "hora")
    LocalTime hora;

    @Column(name = "descripcion")
    String descripcion;

    @OneToOne
    @JoinColumn(name = "id_estado_devolucion", referencedColumnName = "id")
    EstadoDevolucion estado;
}
