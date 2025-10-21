package org.formacion.procesos.services.abstractas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.formacion.procesos.domain.ProcessType;
import org.formacion.procesos.repositories.interfaces.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ComandoServiceAbstract {
    private String comando;
    private ProcessType tipo;
    private String expresionRegular;

    @Autowired
    CrudInterface fileRepository;

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getComando() {
        return comando;
    }

    public void procesarLinea(String linea) {
        String[] arrayComando = linea.split("\s*");
        this.setComando(arrayComando[0]);
        if (!validar(arrayComando)) {
            System.out.println("El comando es invalido");
            return;
        }

        Process proceso;

        try {
            proceso = new ProcessBuilder("sh", "-c", linea + " > mis_procesos.txt")
                    .start();
            ejecutarProceso(proceso);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean ejecutarProceso(Process proceso) {
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

    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

    public boolean validar(String[] arrayComando) {
        if (!validarComando()) {
            return false;
        }
        String parametro = arrayComando[1];

        Pattern pattern = Pattern.compile(expresionRegular);
        Matcher matcher = pattern.matcher(parametro);
        if (!matcher.find()) {
            System.out.println("No cumple");
            return false;
        }

        return true;
    }

    public boolean validarComando() {
        if (!this.getComando().toUpperCase().equals(getTipoToString())) {
            System.out.println("El comando es invalido");
            return false;
        }
        return true;
    }
}
