package org.formacion.procesos.repositories;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.formacion.procesos.repositories.interfaces.IJobRepository;
import org.springframework.stereotype.Repository;


/**
 *  @author: nexphernandez
 *  @version: 1.0.0
 */
@Repository
public class FileJobRepository implements IJobRepository {
    private String fileName;
    static Path path;

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    /**
     * Constuctor que inicializa el path para guardar el resultado del comando
     */
    public FileJobRepository(){
        if (fileName == null) {
            fileName = "mis_procesos.txt";
        }
        URL resource = getClass().getClassLoader().getResource(fileName);
        path = Paths.get(resource.getPath());
    }
    
    @Override
    public Path obtenerPath() {
        return path;
    }

    
}
