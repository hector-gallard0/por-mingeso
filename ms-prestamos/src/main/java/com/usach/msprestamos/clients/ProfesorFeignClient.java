package com.usach.msprestamos.clients;

import com.usach.msprestamos.configurations.FeignClientConfig;
import com.usach.msprestamos.dtos.ProfesoresResponse;
import com.usach.msprestamos.dtos.Response;
import com.usach.msprestamos.exceptions.CustomErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "API-PROFESORES",
        url = "http://localhost:8080/api/profesores",
        configuration = {FeignClientConfig.class, CustomErrorDecoder.class})
public interface ProfesorFeignClient {
    @GetMapping(value = "/{rutProfesor}/verificar")
    Response verificarProfesor(@PathVariable("rutProfesor") String rutProfesor);

    @GetMapping
    ProfesoresResponse obtenerProfesores(@RequestParam("rutsProfesores") List<String> rutsProfesores);
}

