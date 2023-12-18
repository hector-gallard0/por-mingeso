package com.usach.msprofesores.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "retraso")
@Setter
@Getter
@NoArgsConstructor
public class Retraso {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "id_devolucion")
    Integer idDevolucion;

    @Column(name = "horas_retraso")
    Integer horasRetraso;

    @ManyToOne
    @JoinColumn(name = "rut_profesor")
    @JsonBackReference
    Profesor profesor;
}
