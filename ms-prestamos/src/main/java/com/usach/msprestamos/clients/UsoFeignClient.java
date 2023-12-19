package com.usach.msprestamos.clients;

import com.usach.msprestamos.configurations.FeignClientConfig;
import com.usach.msprestamos.exceptions.CustomErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "API-USOS",
        url = "http://ms-gateway:8080/api/usos",
        configuration = {FeignClientConfig.class, CustomErrorDecoder.class})
public interface UsoFeignClient {

    @GetMapping(value = "/nombres")
    public Map<Integer, String> obtenerNombresPorIdsUso(@RequestParam("idsUso") List<Integer> idsUso);

}
