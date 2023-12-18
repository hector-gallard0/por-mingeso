package com.usach.msprofesores.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "profesor")
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Profesor {
    @Id
    @Column(name = "rut")
    String rut;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "apellido")
    String apellido;

    @Column(name = "cantidad_danios")
    Integer cantidadDanios;

    @OneToMany(mappedBy = "profesor")
    @JsonManagedReference
    List<Retraso> retrasos;
}
