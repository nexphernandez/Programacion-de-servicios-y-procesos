package org.formacion.procesos.repositories.interfaces;

import java.nio.file.Path;
/**
 *  @author: nexphernandez
 *  @version: 1.0.0
 */
public interface IJobRepository {
    /**
     * Funcion para obtener el path
     * @return path buscado
     */
    public Path obtenerPath();
}
