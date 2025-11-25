package com.docencia.rest.service.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.docencia.rest.exeception.ResourceNotFoundException;
import com.docencia.rest.modelo.User;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

public interface UserServicesInterface {
    public List<User> getAllUsers();
    public User createUser(@Valid @RequestBody User user);
    public User getUserById(@PathVariable(value = "id") int userId) throws ResourceNotFoundException;
    public User updateUser(@PathVariable(value = "id") int userId, @Valid @RequestBody User userDetails) throws ResourceNotFoundException;
    public boolean deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException;
}
