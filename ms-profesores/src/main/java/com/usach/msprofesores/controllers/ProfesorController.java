package com.usach.msprofesores.controllers;

import com.usach.msprofesores.dtos.*;
import com.usach.msprofesores.entities.Profesor;
import com.usach.msprofesores.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    ProfesorService profesorService;

    @Autowired
    public ProfesorController(
            ProfesorService profesorService
    ) {
        this.profesorService = profesorService;
    }

    @PostMapping
    public ResponseEntity<ProfesorResponse> ingresarProfesor(@RequestBody IngresarProfesor profesor) {
        return ResponseEntity.ok().body(
                new ProfesorResponse(
                        profesorService.ingresarProfesor(profesor),
                        HttpStatus.OK.value()
                )
        );
    }

    @GetMapping("/{rutProfesor}/verificar")
    public ResponseEntity<Response> verificarProfesor(@PathVariable String rutProfesor) {
        profesorService.verificarProfesor(rutProfesor);
        return ResponseEntity.ok().body(
                new Response(HttpStatus.OK.value())
        );
    }

    @GetMapping
    public ResponseEntity<ProfesoresResponse> obtenerProfesoresPorRuts(@RequestParam("rutsProfesores") List<String> rutsProfesores) {
        return ResponseEntity.ok().body(
                new ProfesoresResponse(
                        profesorService.obtenerProfesoresPorRuts(rutsProfesores),
                        HttpStatus.OK.value())
        );
    }


}
