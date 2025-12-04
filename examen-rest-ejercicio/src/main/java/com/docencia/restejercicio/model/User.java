package com.docencia.restejercicio.model;
import java.util.Objects;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class User {

    private Long id;
    private String username;
    private String email;
    /**
     * Constructor vacio
     */
    public User() {
    }

    /**
     * Constructor con el identificador de la clase
     * @param id identificador de la tarea
     */
    public User(Long id) {
        this.id = id;
    }

    /**
     * Constructor con los atributos de la clase
     * @param id identificador del usuario
     * @param username nombre de usuario
     * @param email email del usuario
     */
    public User(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }

}
