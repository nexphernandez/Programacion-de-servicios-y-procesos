package com.docencia.tareas.repository;

import java.util.List;

import com.docencia.tareas.model.Tarea;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public interface ITareaRepository {
    /**
     * Funcion que agrega una tarea
     * @param tarea tarea a agregar
     * @return tarea agragada
     */
    public Tarea add(Tarea tarea);

    /**
     * Funcion que borra una tarea
     * @param tarea tarea a borrar
     * @return true/false
     */
    public boolean delete(Tarea tarea);

    /**
     * Funcion que busca una tarea
     * @param tarea tarea a buscar 
     * @return tarea buscada
     */
    public Tarea findBy(Tarea tarea);

    /**
     * Funcion que busca todas las tareas existentes
     * @return lista de tareas
     */
    public List<Tarea> all();

    /**
     * Funcion que actualiza una tarea
     * @param tarea tarea a actualizar
     * @return tarea actualizada
     */
    public Tarea update(Tarea tarea);
}
