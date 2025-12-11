package com.docencia.tareas.soap;

import java.util.List;

import com.docencia.tareas.model.Alumno;
import jakarta.jws.*;
@WebService(targetNamespace = "http://alumnos.ies.puerto.es/", name = "AlumnoPortType")

public interface IAlumnoSoapEndPoint {
    /**
     * Funcion que busca todas las alumnos existentes
     * @return lista de alumnos
     */
    @WebMethod(operationName = "listarAll")
    List<Alumno> listar();
}
