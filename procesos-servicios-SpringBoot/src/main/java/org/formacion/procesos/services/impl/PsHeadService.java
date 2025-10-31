package org.formacion.procesos.services.impl;


import org.formacion.procesos.domain.Job;
import org.formacion.procesos.services.impl.abstractas.ComandoServiceAbstract;
import org.springframework.stereotype.Component;
/**
 *  @author: nexphernandez
 *  @version: 1.0.0
 */
@Component
public class PsHeadService extends ComandoServiceAbstract {

    /**
     * Consturctor de la clase PsHeadService
     */
    public PsHeadService(){
        this.setTipo(Job.PS);
        this.setExpresionRegular("^((-?(aux\\s+\\|\\s+head)\s*)|\\s*)$");
    }
        
}