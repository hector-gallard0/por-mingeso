package com.usach.msequipos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "uso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Uso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "descripcion")
    String descripcion;

    public Uso(Integer id) {
        this.id = id;
    }
}
