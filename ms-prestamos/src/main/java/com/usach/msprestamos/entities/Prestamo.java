package com.usach.msprestamos.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


@Table(name = "prestamo")
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Prestamo {
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

    @Column(name = "rut_profesor")
    String rutProfesor;

    @Column(name = "id_uso")
    Integer idUso;

    @Column(name = "id_equipo")
    Integer idEquipo;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_devolucion", referencedColumnName = "id")
    Devolucion devolucion;
}
