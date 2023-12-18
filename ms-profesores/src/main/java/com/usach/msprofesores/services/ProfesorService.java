package com.usach.msprofesores.services;

import com.usach.msprofesores.dtos.IngresarProfesor;
import com.usach.msprofesores.dtos.ProfesorDTO;
import com.usach.msprofesores.entities.Inhabilitacion;
import com.usach.msprofesores.entities.Profesor;
import com.usach.msprofesores.exceptions.ApiErrorException;
import com.usach.msprofesores.exceptions.ProfesorInhabilitadoException;
import com.usach.msprofesores.exceptions.RegistroNoExisteException;
import com.usach.msprofesores.repositories.InhabilitacionRepository;
import com.usach.msprofesores.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    ProfesorRepository profesorRepository;
    InhabilitacionRepository inhabilitacionRepository;

    @Autowired
    public ProfesorService(ProfesorRepository profesorRepository,
                           InhabilitacionRepository inhabilitacionRepository) {
        this.profesorRepository = profesorRepository;
        this.inhabilitacionRepository = inhabilitacionRepository;
    }

    public Profesor ingresarProfesor(IngresarProfesor ingresarProfesor) {
        Profesor nuevoProfesor = new Profesor();
        nuevoProfesor.setRut(ingresarProfesor.getRut());
        nuevoProfesor.setNombre(ingresarProfesor.getNombre());
        nuevoProfesor.setApellido(ingresarProfesor.getApellido());
        nuevoProfesor.setCantidadDanios(0);
        return profesorRepository.save(nuevoProfesor);
    }

    public void verificarProfesor(String rutProfesor) {
        if(!profesorRepository.existsById(rutProfesor)) throw new RegistroNoExisteException("No existe el profesor.");
        Optional<Inhabilitacion> inhabilitacion = inhabilitacionRepository.buscarInhabilitacionProfesor(rutProfesor);
        if(inhabilitacion.isPresent()) {
            throw new ProfesorInhabilitadoException("El profesor " + rutProfesor +
                    " se encuentra inhabilitado desde " + inhabilitacion.get().getFechaInicio() + " hasta " + inhabilitacion.get().getFechaFin());
        }
    }

    public List<ProfesorDTO> obtenerProfesoresPorRuts(List<String> rutsProfesores) {
        List<ProfesorDTO> profesores = profesorRepository.findAllByRuts(rutsProfesores);
        System.out.println(profesores);
        return profesores;
    }
}
