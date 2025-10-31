package org.formacion.procesos.services.interfaces;
/**
 *  @author: nexphernandez
 *  @version: 1.0.0
 */
public interface CommandService {

    /**
     * procesa la linea de comando introducida
     * @param linea de comando
     * @return true/false
     */
    boolean procesarLinea(String linea);

    /**
     * ejecuta el proceso introducido
     * @param proceso
     * @return true/false
     */
    boolean ejecutarProceso(Process proceso);

    /**
     * valida los parametros del comando
     * @param arrayComando
     * @return true/false
     */
    boolean validar(String[] arrayComando);
    
}
