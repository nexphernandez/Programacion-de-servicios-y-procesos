package com.docencia.restejercicio.controller;

import com.docencia.restejercicio.common.ApiRestController;
import com.docencia.restejercicio.model.Task;
import com.docencia.restejercicio.repository.TaskRepository;
import com.docencia.restejercicio.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Tasks", description = "Operaciones sobre productos")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Listar todas las tareas")
    @GetMapping("/tasks")
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @Operation(summary = " Obtener una tarea por id")
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getById(@PathVariable(value = "id") Long id) {
        Task task = taskService.getById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(task);
    }

    @Operation(summary = "Crear una nueva tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "task created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/tasks")
    public Task create(@Valid @RequestBody Task task) {
        return taskService.create(task);
    }

    @Operation(summary = "Actualizar una tarea existente")
    @PutMapping("/tasks/{id}")
    public Task update( @PathVariable(value = "id") Long id, Task update) {
        return taskService.update(id, update);
    }

    @Operation(summary = "Eliminar una tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "task not found")
    })
    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        taskService.delete(id);
    }
}
