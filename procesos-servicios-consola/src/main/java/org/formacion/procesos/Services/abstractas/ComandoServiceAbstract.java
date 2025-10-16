package org.formacion.procesos.Services.abstractas;

import java.util.List;

import org.formacion.procesos.domain.ProcessType;
import org.formacion.procesos.repositories.interfaces.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ComandoServiceAbstract {
    String comando;
    List<String> parametros;
    ProcessType tipo;

    @Autowired
    CrudInterface fileRepository;

    public void setComando(String comando) {
        this.comando = comando;
    }

    public void setParametros(List<String> parametros) {
        this.parametros = parametros;
    }

    public String getComando() {
        return comando;
    }

    public List<String> getParametros() {
        return parametros;
    }

    public void procesarLinea(String linea){
        String[] arrayComando = linea.split(" ");
        this.setComando(arrayComando[0]);
        if (!validar(arrayComando)) {
            System.out.println("El comando es invalido");
            return;
        }


        Process proceso;
        //linea = ps aux | grep java
        try {
            proceso = new ProcessBuilder("sh", "-c", linea + " > mis_procesos.txt")
                        .start();
            ejecutarProceso(proceso);
        } catch (Exception e) {
            e.printStackTrace();
        }
        imprimeMensaje();
    }

    public boolean ejecutarProceso(Process proceso){
        try {

            proceso.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public ProcessType getTipo() {
        return tipo;
    }

    public String getTipoToString() {
        if (tipo == null) {
            return null;
        }
        return tipo.toString();
    }

    public void setTipo(ProcessType tipo) {
        this.tipo = tipo;
    }

    public abstract void imprimeMensaje();
    public abstract boolean validar(String[] arrayComando);

    public boolean validarComando() {
        if (!this.getComando().toUpperCase().equals(getTipoToString())) {
            System.out.println("El comando es invalido");
            return false;
        }
        return true;
    }
}
