package com.usach.msequipos.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "equipo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "disponible")
    Boolean disponible;

    @OneToOne
    @JoinColumn(name = "id_marca", referencedColumnName = "id")
    Marca marca;

    @OneToOne
    @JoinColumn(name = "id_tipo", referencedColumnName = "id")
    TipoEquipo tipo;

    @ManyToMany
    @JoinTable(
            name = "permiso_uso_equipo",
            joinColumns = @JoinColumn(name = "id_equipo"),
            inverseJoinColumns = @JoinColumn(name = "id_uso")
    )
    List<Uso> usos;
}
