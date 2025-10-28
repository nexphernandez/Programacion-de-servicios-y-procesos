package org.formacion.procesos.services.impl;


import org.formacion.procesos.domain.Job;
import org.formacion.procesos.services.impl.abstractas.ComandoServiceAbstract;
import org.springframework.stereotype.Component;

@Component
public class LsofService extends ComandoServiceAbstract {

    public LsofService() {
        this.setTipo(Job.LSOF);
        this.setExpresionRegular("^((-(la|l|a))|\s*)$");
    }

}