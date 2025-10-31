package org.formacion.procesos.repositories;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.formacion.procesos.repositories.interfaces.IJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


/**
 *  @author: nexphernandez
 *  @version: 1.0.0
 */
@Repository
public class FileJobRepository implements IJobRepository {
    private static Logger looger = LoggerFactory.getLogger(FileJobRepository.class);
    private String fileName;
    static Path path;

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    /**
     * Constuctor que inicializa el path para guardar el resultado del comando
     */
    public FileJobRepository() {
        if (fileName == null) {
            fileName = "mis_procesos.txt";
        }
    
        try {
            URL resources = getClass().getClassLoader().getResource(fileName);
            if (resources == null) {
                throw new RuntimeException("No se encontró el recurso: " + fileName);
            }
    
            path = Paths.get(resources.toURI());
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo el path del fichero", e);
        }
    }
    
    @Override
    public Path obtenerPath() {
        return path;
    }

    
}
