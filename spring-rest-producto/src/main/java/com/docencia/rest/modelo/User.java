package com.docencia.rest.modelo;

import jakarta.persistence.*;
import java.util.Objects;
/**
 * @author nexphernandez
 */
@Entity
@Table(name = "users")
public class User {

    private int id;
    private String name;
    
    /**
     * Constructor vacio
     */
    public User() {
            
    }

    public User(int id) {
        this.id = id;
    }
        
    public User(String name) {
        this.name = name;
    }
        
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
        
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
        
}