package com.docencia.tareas.model;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author nexphernandez
 * @version 1.0.0
 */
@XmlRootElement(name = "tarea")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tarea {

    private Long id;
    private String titulo;
    private String descripcion;
    private boolean completada;

    /**
     * Constructor vacio
     */
    public Tarea() {
    }

    /**
     * Constructor con el identificadro de la clase
     * @param id identificador de la tarea
     */
    public Tarea(Long id) {
        this.id = id;
    }

    /**
     * Constructor con los atributos de la clase
     * @param id identificador de la tarea
     * @param titulo titulo de la tarea
     * @param descripcion descripcion de la tarea
     * @param completada si la tarea esta completado o no
     */
    public Tarea(Long id, String titulo, String descripcion, boolean completada) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.completada = completada;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return this.completada;
    }

    public boolean getCompletada() {
        return this.completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Tarea)) {
            return false;
        }
        Tarea tarea = (Tarea) o;
        return Objects.equals(id, tarea.id) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", completada='" + isCompletada() + "'" +
            "}";
    }
    
}
