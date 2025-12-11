package com.docencia.tareas.soap;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docencia.tareas.model.Alumno;
import com.docencia.tareas.service.IAlumnoSevice;

import jakarta.jws.WebService;

@WebService(
    serviceName = "AlumnoService",
    portName = "AlumnoPort",
    targetNamespace = "http://alumnos.ies.puerto.es/",
    endpointInterface = "com.docencia.tareas.soap.IAlumnoSoapEndPoint"
)
@Service
public class AlumnoSoapService implements IAlumnoSoapEndPoint{

    private final IAlumnoSevice alumnoService;

    public AlumnoSoapService(IAlumnoSevice alumnoService) {
        this.alumnoService = alumnoService;
    }
    

    @Override
    public List<Alumno> listar() {
        return alumnoService.listarTodas();
    }



}
