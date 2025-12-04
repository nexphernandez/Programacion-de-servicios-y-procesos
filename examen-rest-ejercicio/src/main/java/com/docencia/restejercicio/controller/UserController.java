package com.docencia.restejercicio.controller;

import com.docencia.restejercicio.model.Task;
import com.docencia.restejercicio.model.User;
import com.docencia.restejercicio.repository.UserRepository;
import com.docencia.restejercicio.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@Tag(name = "Users", description = "Operaciones sobre usuarios")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Listar todas los usuarios")
    @GetMapping("/users")
    public List<User> getAll() {
        return userService.getAll();
    }

    @Operation(summary = " Obtener un usuario por id")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable(value = "id") Long id) {
        User usuario = userService.getById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(usuario);
    }

    @Operation(summary = "Crear una nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/users")
    public User create(User user) {
       return userService.create(user);
    }

    @Operation(summary = "Actualizar un usuario existente")
    @PutMapping("/users/{id}")
    public User update(Long id, User update) {
        return userService.update(id, update);
    }

     @Operation(summary = "Eliminar un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user deleted successfully"),
            @ApiResponse(responseCode = "404", description = "user not found")
    })
    @DeleteMapping("/users/{id}")
    public void delete(Long id) {
        userService.delete(id);
    }
    
}
