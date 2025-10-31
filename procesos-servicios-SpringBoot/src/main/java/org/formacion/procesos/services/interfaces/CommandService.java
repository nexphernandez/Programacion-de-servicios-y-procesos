package org.formacion.procesos.services.interfaces;

public interface CommandService {
    boolean procesarLinea(String linea);

    boolean ejecutarProceso(Process proceso);

    boolean validar(String[] arrayComando);
    
}
