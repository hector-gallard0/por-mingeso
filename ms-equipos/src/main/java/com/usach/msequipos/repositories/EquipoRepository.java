package com.usach.msequipos.repositories;

import com.usach.msequipos.entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    @Query("SELECT e " +
            "FROM Equipo e " +
            "WHERE (:disponible IS NULL OR e.disponible = :disponible) ")
    List<Equipo> findAllByDisponible(@Param("disponible") Boolean disponible);
}
