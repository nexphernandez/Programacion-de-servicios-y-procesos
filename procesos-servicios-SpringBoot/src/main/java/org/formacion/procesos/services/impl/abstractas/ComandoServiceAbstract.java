package org.formacion.procesos.services.impl.abstractas;

import java.util.Arrays;
import java.util.List;

import org.formacion.procesos.domain.Job;
import org.formacion.procesos.repositories.interfaces.IJobRepository;
import org.formacion.procesos.services.interfaces.CommandService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author nexphernandez
 * @version 1.0.0
 */
public abstract class ComandoServiceAbstract implements CommandService {

    private String comando;
    private Job tipo;
    private List<String> expresionRegular;

    IJobRepository iJobRepoitory;

    public ComandoServiceAbstract(Job tipo, List<String> expresionRegular) {
        this.tipo = tipo;
        this.expresionRegular = expresionRegular;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getComando() {
        return comando;
    }

    @Override
    public boolean procesarLinea(String linea) {
        String[] arrayComando = linea.trim().split("\\s+");

        this.setComando(arrayComando[0]);

        if (!validar(arrayComando)) {
            return false;
        }

        try {
            Process proceso = new ProcessBuilder("sh", "-c", linea + " > " + iJobRepoitory.obtenerPath())
                    .start();
            ejecutarProceso(proceso);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean ejecutarProceso(Process proceso) {
        try {
            proceso.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean validar(String[] arrayComando) {

        if (!validarComando()) {
            return false;
        }

        if (arrayComando.length == 1) {
            return true;
        }

        String parametros = String.join(" ", Arrays.copyOfRange(arrayComando, 1, arrayComando.length)).trim();

        if (parametros.isEmpty()) {
            return true;
        }

        String[] tokens = parametros.split("\\s+");

        for (String token : tokens) {
        
            if (expresionRegular.contains(token)) {
                continue;
            }

            if (token.startsWith("-")) {
                String sinGuion = token.substring(1);
                if (expresionRegular.contains(sinGuion)) {
                    continue;
                }
            }



            return false;
        }

        return true;
    }

    public boolean validarComando() {
        return this.getComando().equalsIgnoreCase(getTipoToString());
    }

    public IJobRepository getIJobRepoitory() {
        return iJobRepoitory;
    }

    @Autowired
    public void setIJobRepoitory(IJobRepository iJobRepoitory) {
        this.iJobRepoitory = iJobRepoitory;
    }

    public Job getTipo() {
        return tipo;
    }

    public String getTipoToString() {
        return tipo == null ? null : tipo.toString();
    }
}
