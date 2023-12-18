package com.usach.msprofesores.repositories;

import com.usach.msprofesores.entities.Inhabilitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InhabilitacionRepository extends JpaRepository<Inhabilitacion, Integer> {
    @Query("SELECT i " +
            "FROM Inhabilitacion i " +
            "WHERE i.profesor.rut = :rutProfesor " +
            "AND CURRENT_DATE <= i.fechaFin ")
    Optional<Inhabilitacion> buscarInhabilitacionProfesor(@Param("rutProfesor") String rutProfesor);
}
