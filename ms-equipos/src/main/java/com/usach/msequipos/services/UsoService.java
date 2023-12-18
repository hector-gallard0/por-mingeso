package com.usach.msequipos.services;

import com.usach.msequipos.entities.Uso;
import com.usach.msequipos.repositories.UsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsoService {

    UsoRepository usoRepository;

    @Autowired
    public UsoService(UsoRepository usoRepository) {
        this.usoRepository = usoRepository;
    }

    public Map<Integer, String> obtenerNombresPorIdsUso(List<Integer> idsUso) {
        List<Uso> usos = usoRepository.findAllById(idsUso);
        Map<Integer, String> mapUsos = new HashMap<>();
        for(Uso uso : usos) {
            mapUsos.put(uso.getId(), uso.getDescripcion());
        }
        return mapUsos;
    }

    public List<Uso> obtenerUsos() {
        return usoRepository.findAll();
    }
}
