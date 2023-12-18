package com.usach.msequipos.controllers;

import com.usach.msequipos.entities.Uso;
import com.usach.msequipos.services.UsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usos")
public class UsoController {

    UsoService usoService;

    @Autowired
    public UsoController(UsoService usoService) {
        this.usoService = usoService;
    }

    @GetMapping("/nombres")
    public Map<Integer, String> obtenerNombresPorCodigosUsos(@RequestParam("idsUso") List<Integer> idsUso) {
        return usoService.obtenerNombresPorIdsUso(idsUso);
    }

}
