package com.docencia.tareas.service;

import java.util.List;

import com.docencia.tareas.model.Tarea;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public interface ITareaService {
    /**
     * Funcion que busca todas las tareas existentes
     * @return lista de tareas
     */
    List<Tarea> listarTodas();

    /**
     * Funcion que busca una tarea
     * @param tarea tarea a buscar 
     * @return tarea buscada
     */
    Tarea buscarPorId(Long id);

    /**
     * Funcion que agrega una tarea
     * @param titulo titulo de tarea a agregar
     * @param descripcion descripcion de tarea a agregar
     * @return tarea agragada
     */
    Tarea crearTarea(String titulo, String descripcion);

    /**
     * Funcion que actualiza una tarea
     * @param id id a tarea a actualizar
     * @param titulo titulo de tarea a actualizar
     * @param descripcion descripcion de tarea a actualizar
     * @param completada si la tarea a actualizar esta completada o no
     * @return tarea actualizada
     */
    Tarea actualizarTarea(Long id, String titulo, String descripcion, Boolean completada);

    /**
     * Funcion que borra una tarea por su id
     * @param id identificador de la tarea a borrar
     * @return true/false
     */
    boolean eliminarTarea(Long id);
}
