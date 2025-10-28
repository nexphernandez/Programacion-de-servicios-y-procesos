package org.formacion.procesos.services.interfaces;

public interface CommandService {
    void procesarLinea(String linea);

    boolean ejecutarProceso(Process proceso);

    boolean validar(String[] arrayComando);
    
}
