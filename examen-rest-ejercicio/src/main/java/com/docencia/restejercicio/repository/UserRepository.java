package com.docencia.restejercicio.repository;

import com.docencia.restejercicio.model.Task;
import com.docencia.restejercicio.model.User;
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
public class UserRepository {

    List<User> usuarios;

    /**
     * Constructor vacio
     */
    public UserRepository (){
        this.usuarios = new ArrayList<>();
    }

    /**
     * funcion que busca todos los usuarios de la bbdd
     * @return lista de usuarios
     */
    public List<User> findAll() {
        return usuarios;
    }

    /**
     * Funcion que busca una usuario por el id
     * @param id id de la usuario buscado
     * @return usuario buscado/null
     */
    public Optional<User> findById(Long id) {
        User usuario = new User(id);
        int indice = usuarios.indexOf(usuario);
        if (indice <= -1) {
            return null;
        }
        return Optional.of(usuarios.get(indice));
    }

    /**
     * Funcion para aniadir una usuario en la bbdd
     * @param user usuario a aniadir
     * @return el usuario aniadido
     */
    public User save(User user) {
        usuarios.add(user);
        return user;
    }

    /**
     * Funcion que borra un usuario de la bbdd
     * @param id identificador del usuario a borrar
     */
    public void deleteById(Long id) {
        User usuario = new User(id);
        usuarios.remove(usuario);
    }

    /**
     * Funcion que verifica si existe un usuario sabiendo su id
     * @param id id del usuario a verificar
     * @return true/false
     */
    public boolean existsById(Long id) {
        User usuario = new User(id);
        int indice = usuarios.indexOf(usuario);
        if (indice <= -1) {
            return false;
        }
        return true;
    }
}
