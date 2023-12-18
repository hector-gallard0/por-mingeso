package com.usach.msprestamos.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public CustomErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.body() != null) {
                // Deserializar el cuerpo de la respuesta a tu clase Response
                com.usach.msprestamos.dtos.Response errorResponse = objectMapper.readValue(response.body().asInputStream(), com.usach.msprestamos.dtos.Response.class);

                // Devuelve la excepción específica basada en la lógica de tu aplicación
                if (response.status() == HttpStatus.NOT_FOUND.value()) {
                    return new ApiErrorException(errorResponse.getMessage());
                }

                if(response.status() == HttpStatus.FORBIDDEN.value()) {
                    return new ApiErrorException(errorResponse.getMessage());
                }

                return new Exception("Error en la petición.");
            }
        } catch (IOException e) {
            // Manejo de errores de deserialización
            e.printStackTrace();
        }

        // Devuelve una excepción predeterminada si no se pudo deserializar la respuesta
        return new Exception("Error al procesar la respuesta.");
    }
}

