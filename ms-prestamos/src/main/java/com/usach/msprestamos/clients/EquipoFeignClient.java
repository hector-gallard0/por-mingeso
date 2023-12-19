package com.usach.msprestamos.clients;

import com.usach.msprestamos.configurations.FeignClientConfig;
import com.usach.msprestamos.dtos.Response;
import com.usach.msprestamos.exceptions.CustomErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "API-EQUIPOS",
        url = "http://ms-gateway:8080/api/equipos",
        configuration = {FeignClientConfig.class, CustomErrorDecoder.class})
public interface EquipoFeignClient {
    @GetMapping(value = "/{idEquipo}/uso/{idUso}/verificar")
    Response verificarEquipoYUso(@PathVariable("idEquipo") Integer idEquipo,
                                 @PathVariable("idUso") Integer idUso);


    @GetMapping(value = "/{idEquipo}/verificar")
    Response verificarEquipo(@PathVariable("idEquipo") Integer idEquipo);
}
