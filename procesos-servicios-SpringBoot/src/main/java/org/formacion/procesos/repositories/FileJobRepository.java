package org.formacion.procesos.repositories;

import org.formacion.procesos.repositories.interfaces.IJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Repository
public class FileJobRepository implements IJobRepository {
    private static Logger looger = LoggerFactory.getLogger(FileJobRepository.class);
    static String fileName;
    static Path path;

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public FileJobRepository() {
        if (fileName == null) {
            fileName = "mis_procesos.txt";
        }
        URL resources = getClass().getClassLoader().getResource(fileName);
        path = Paths.get(resources.getPath());
    }

    @Override
    public boolean add(String text) {
        try {
            Files.write(path, text.getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (Exception e) {
            looger.error("Se ha producido un error almacenando el fichero", e);
        }
        return false;
    }
}
