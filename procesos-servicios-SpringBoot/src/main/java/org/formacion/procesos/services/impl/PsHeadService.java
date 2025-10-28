package org.formacion.procesos.services.impl;


import org.formacion.procesos.domain.Job;
import org.formacion.procesos.services.impl.abstractas.ComandoServiceAbstract;
import org.springframework.stereotype.Component;

@Component
public class PsHeadService extends ComandoServiceAbstract {

    public PsHeadService(){
        this.setTipo(Job.ps);
        this.setExpresionRegular("^((-?(xa|a|x|aux))|\s*)$");
    }
        
}