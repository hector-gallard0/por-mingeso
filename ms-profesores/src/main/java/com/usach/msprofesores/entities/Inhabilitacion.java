package com.usach.msprofesores.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "inhabilitacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inhabilitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "fecha_inicio")
    LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "rut_profesor")
    Profesor profesor;
}
