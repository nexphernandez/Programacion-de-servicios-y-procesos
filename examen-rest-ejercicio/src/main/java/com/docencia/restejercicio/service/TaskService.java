package com.docencia.restejercicio.service;

import com.docencia.restejercicio.model.Task;
import com.docencia.restejercicio.repository.TaskRepository;

/**
 * @author nexphernandez
 * @version 1.0.0
 */
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private  final TaskRepository repository;

    /**
     * Constructor que inicializa la clase
     * @param repository respository a inicializar
     */
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * funcion que busca todas las tareas de la bbdd
     * @return lista de tareas
     */
    public List<Task> getAll() {
        return repository.findAll();
    }

    /**
     * Funcion que busca una tarea por el id
     * @param id id de la tarea buscada
     * @return tarea buscada/null
     */
    public Task getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Funcion para aniadir una tarea en la bbdd
     * @param task tarea a aniadir
     * @return la tarea aniadida
     */
    public Task create(Task task) {
        if (task.getId() == null) {
            task.setId((long) repository.findAll().size());
        }
        return repository.save(task);
    }

    /**
     * Funcion que actuliza una tarea sabiendo su id
     * @param id id de la tarea a actualizar 
     * @param update tarea con los datos actualizados
     * @return tarea ya actualizado
     */
    public Task update(Long id, Task update) {
        if (repository.findById(id) != null) {
            repository.deleteById(id);
        }
        repository.save(update);
        return update;
    }

    /**
     * Funcion que borra una tarea de la bbdd
     * @param id identificador de la tarea a borrar
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
