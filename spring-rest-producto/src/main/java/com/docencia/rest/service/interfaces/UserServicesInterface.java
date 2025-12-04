package com.docencia.rest.service.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.docencia.rest.exeception.ResourceNotFoundException;
import com.docencia.rest.modelo.User;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public interface UserServicesInterface {
    /**
     * Funcion que busca a todos los usuario de la bbdd
     * @return lista con los ususarios de la bbd
     */
    public List<User> getAllUsers();
    /**
     * Funcion para crear un usuario en la bbdd
     * @param user usuario a niadir en la bbdd
     * @return el usuario aniadido/null
     */
    public User createUser(@Valid @RequestBody User user);
    /**
     * Funcion que busca un usuario en la bbdd por el id
     * @param userId id del usuario a buscar
     * @return el usuario aniadido/null
     * @throws ResourceNotFoundException
     */
    public User getUserById(@PathVariable(value = "id") int userId) throws ResourceNotFoundException;
    /**
     * Funcion para acrualizar un usuario de la bbdd
     * @param userId id del usuario a actualizar
     * @param userDetails usuario con los datos actualizados
     * @return usuario aniadido a la bbdd
     * @throws ResourceNotFoundException
     */
    public User updateUser(@PathVariable(value = "id") int userId, @Valid @RequestBody User userDetails) throws ResourceNotFoundException;
    /**
     * Funcion para borrar una persona de la bbd
     * @param userId id del usuario a buscar
     * @return true/false
     * @throws ResourceNotFoundException
     */
    public boolean deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException;
}
