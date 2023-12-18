package com.usach.msprestamos.repositories;

import com.usach.msprestamos.dtos.EntradaReporteQuery;
import com.usach.msprestamos.entities.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    @Query("SELECT p.fecha AS fechaPrestamo, " +
            "p.hora AS horaPrestamo, " +
            "p.devolucion AS devolucion, " +
            "p.rutProfesor AS rutProfesor, " +
            "TIME_TO_SEC(TIMEDIFF(CONCAT(COALESCE(p.devolucion.fecha, CURRENT_DATE ), ' ', COALESCE(p.devolucion.hora, CURRENT_TIME)), CONCAT(p.fecha, ' ', p.hora))) AS duracionPrestamo, " +
            "p.idUso AS idUso " +
            "FROM Prestamo p " +
            "LEFT JOIN p.devolucion d " +  // LEFT JOIN en lugar de INNER JOIN
            "WHERE p.idEquipo = :idEquipo")
    List<EntradaReporteQuery> findReporteByIdEquipo(@Param("idEquipo") Integer idEquipo);

    @Query("SELECT p " +
            "FROM Prestamo p " +
            "WHERE p.idEquipo = :idEquipo " +
            "AND p.devolucion IS NULL ")
    Optional<Prestamo> findPrestamosSinDevolucionByIdEquipo(@Param("idEquipo") Integer idEquipo);

}
