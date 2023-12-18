package com.usach.msprofesores.repositories;

import com.usach.msprofesores.dtos.ProfesorDTO;
import com.usach.msprofesores.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, String> {
    @Query("SELECT p.rut AS rut, p.nombre AS nombre, p.apellido AS apellido " +
            "FROM Profesor p " +
            "WHERE p.rut IN :ruts ")
    List<ProfesorDTO> findAllByRuts(@Param("ruts") List<String> ruts);
}
