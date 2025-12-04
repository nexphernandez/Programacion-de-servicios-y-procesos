package com.docencia.restejercicio.repository;

import com.docencia.restejercicio.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
@Repository
public class TaskRepository {

    List<Task> tareas;

    /**
     * Constructor vacio
     */
    public TaskRepository (){
        this.tareas = new ArrayList<>();
    }

    /**
     * funcion que busca todas las tareas de la bbdd
     * @return lista de tareas
     */
    public List<Task> findAll() {
        return tareas;
    }

    /**
     * Funcion que busca una tarea por el id
     * @param id id de la tarea buscada
     * @return tarea buscada/null
     */
    public Optional<Task> findById(Long id) {
        Task task = new Task(id);
        int indice = tareas.indexOf(task);
        if (indice <= -1) {
            return null;
        }
        return Optional.of(tareas.get(indice));
    }

    /**
     * Funcion para aniadir una tarea en la bbdd
     * @param task tarea a aniadir
     * @return la tarea aniadida
     */
    public Task save(Task task) {
        tareas.add(task);
        return task;
    }

    /**
     * Funcion que borra una tarea de la bbdd
     * @param id identificador de la tarea a borrar
     */
    public void deleteById(Long id) {
        Task task = new Task(id);
        tareas.remove(task);
    }

    /**
     * Funcion que verifica si existe una tarea sabiendo su id
     * @param id id de la tarea a verificar
     * @return true/false
     */
    public boolean existsById(Long id) {
        Task task = new Task(id);
        int indice = tareas.indexOf(task);
        if (indice <= -1) {
            return false;
        }
        return true;
    }
}
