package com.usach.msequipos.repositories;

import com.usach.msequipos.entities.Uso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsoRepository extends JpaRepository<Uso, Integer> {
}
