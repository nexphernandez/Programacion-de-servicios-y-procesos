package org.formacion.procesos.services.impl;


import org.formacion.procesos.domain.Job;
import org.formacion.procesos.services.impl.abstractas.ComandoServiceAbstract;
import org.springframework.stereotype.Component;
/**
 *  @author: nexphernandez
 *  @version: 1.0.0
 */
@Component
public class LsofService extends ComandoServiceAbstract {

    /**
     * Consturctor de la clase LsofService
     */
    public LsofService() {
        this.setTipo(Job.LSOF);
        this.setExpresionRegular("^(-i|\s*)$");
    }

}