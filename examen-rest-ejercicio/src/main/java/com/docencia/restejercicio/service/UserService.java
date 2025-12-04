package com.docencia.restejercicio.service;

import com.docencia.restejercicio.model.User;
import com.docencia.restejercicio.repository.UserRepository;
import java.util.List;

import org.springframework.stereotype.Service;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
@Service
public class UserService {

    private final UserRepository repository;

    /**
     * Constructor que inicializa la clase
     * @param repository respository a inicializar
     */
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * funcion que busca todos los usuarios de la bbdd
     * @return lista de usuarios
     */
    public List<User> getAll() {
        return repository.findAll();
    }

    /**
     * Funcion que busca una usuario por el id
     * @param id id de la usuario buscado
     * @return usuario buscado/null
     */
    public User getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Funcion para aniadir una usuario en la bbdd
     * @param user usuario a aniadir
     * @return el usuario aniadido
     */
    public User create(User user) {
        if (user.getId() == null) {
            user.setId((long) repository.findAll().size());
        }
        return repository.save(user);
    }

    /**
     * Funcion que actuliza un usuario sabiendo su id
     * @param id id del usuario a actualizar 
     * @param update usuario con los datos actualizados
     * @return usuario ya actualizado
     */
    public User update(Long id, User update) {
        if (repository.findById(id) != null) {
            repository.deleteById(id);
        }
        repository.save(update);
        return update;
    }

    /**
     * Funcion que borra un usuario de la bbdd
     * @param id identificador del usuario a borrar
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
