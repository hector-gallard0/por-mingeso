package com.usach.msequipos.controllers;

import com.usach.msequipos.dtos.Catalogo;
import com.usach.msequipos.dtos.CatalogoResponse;
import com.usach.msequipos.dtos.EquiposResponse;
import com.usach.msequipos.dtos.Response;
import com.usach.msequipos.dtos.requests.IngresarEquiposRequest;
import com.usach.msequipos.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EquipoController {

    EquipoService equipoService;

    @Autowired
    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping("/equipos")
    public ResponseEntity<EquiposResponse> obtenerEquipos(@RequestParam(value = "disponible", required = false) Boolean disponible) {
        return ResponseEntity.ok().body(
                new EquiposResponse(
                        equipoService.obtenerEquipos(disponible),
                        HttpStatus.OK.value()
                )
        );
    }

    @PostMapping("/equipos")
    public ResponseEntity<EquiposResponse> ingresarEquipos(@RequestBody IngresarEquiposRequest ingresarEquiposRequest)  {
        return ResponseEntity.ok().body(
                new EquiposResponse(
                    equipoService.ingresarEquipos(ingresarEquiposRequest),
                    HttpStatus.OK.value()
                )
        );
    }

    @GetMapping("/equipos/{idEquipo}/uso/{idUso}/verificar")
    public ResponseEntity<Response> verificarEquipoYUso(@PathVariable Integer idEquipo,
                                                        @PathVariable Integer idUso) {
        equipoService.verificarEquipoYUso(idEquipo, idUso);
        return ResponseEntity.ok().body(
                new Response(
                        HttpStatus.OK.value()
                )
        );
    }

    @GetMapping("/equipos/{idEquipo}/verificar")
    public ResponseEntity<Response> verificarEquipo(@PathVariable Integer idEquipo) {
        equipoService.verificarEquipo(idEquipo);
        return ResponseEntity.ok().body(
                new Response(
                        HttpStatus.OK.value()
                )
        );
    }

    @GetMapping("/catalogo")
    public ResponseEntity<CatalogoResponse> obtenerCatalogo() {
        return ResponseEntity.ok().body(
                new CatalogoResponse(
                        equipoService.obtenerCatalogo(),
                        HttpStatus.OK.value()
                )
        );
    }
}
