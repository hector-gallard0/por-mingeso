package com.usach.msprestamos.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.usach.msprestamos.dtos.*;
import com.usach.msprestamos.entities.EstadoDevolucion;
import com.usach.msprestamos.exceptions.ApiErrorException;
import com.usach.msprestamos.services.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PrestamoController {
    PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping("/prestamos")
    public ResponseEntity<PrestamoResponse> ingresarPrestamo(@RequestBody IngresarPrestamoRequest ingresarPrestamoRequest) throws JsonProcessingException {
        return new ResponseEntity<>(
                new PrestamoResponse(
                    prestamoService.ingresarPrestamo(ingresarPrestamoRequest),
                    HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @PostMapping("/devoluciones")
    public ResponseEntity<DevolucionResponse> ingresarDevolucion(@RequestBody IngresarDevolucionRequest ingresarDevolucionRequest) throws JsonProcessingException {
        return ResponseEntity.ok().body(
                new DevolucionResponse(
                        prestamoService.ingresarDevolucion(ingresarDevolucionRequest),
                        HttpStatus.OK.value()
                )
        );
    }

    @GetMapping("/devoluciones/estados")
    public ResponseEntity<EstadoDevolucionResponse> obtenerEstados() {
        return ResponseEntity.ok().body(
                new EstadoDevolucionResponse(
                        prestamoService.obtenerEstados(),
                        HttpStatus.OK.value()
                )
        );
    }

    @GetMapping("/reportes/prestamos")
    public ResponseEntity<ReporteResponse> obtenerReporte(@RequestParam("idEquipo") Integer idEquipo) {
        return ResponseEntity.ok().body(
                new ReporteResponse(
                        prestamoService.obtenerReporte(idEquipo),
                        HttpStatus.OK.value()
                )
        );
    }
}
