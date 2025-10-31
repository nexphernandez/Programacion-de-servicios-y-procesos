package org.formacion.procesos.services.impl;


import org.formacion.procesos.domain.Job;
import org.formacion.procesos.services.impl.abstractas.ComandoServiceAbstract;
import org.springframework.stereotype.Component;

@Component
public class PsHeadService extends ComandoServiceAbstract {

    public PsHeadService(){
        this.setTipo(Job.PS);
        this.setExpresionRegular("^((-?(aux\\s+\\|\\s+head)\s*)|\\s*)$");
    }
        
}