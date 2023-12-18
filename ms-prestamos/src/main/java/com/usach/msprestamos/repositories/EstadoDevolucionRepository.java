package com.usach.msprestamos.repositories;

import com.usach.msprestamos.entities.EstadoDevolucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoDevolucionRepository extends JpaRepository<EstadoDevolucion, Integer> {
}
