package org.formacion.procesos.services.impl;

import org.formacion.procesos.domain.Job;
import org.formacion.procesos.services.impl.abstractas.ComandoServiceAbstract;
import org.springframework.stereotype.Component;

@Component
public class TopService extends ComandoServiceAbstract{
    
    /**
     * Consturctor de la clase TopService
     */
    public TopService(){
        this.setTipo(Job.TOP);
        this.setExpresionRegular("");
    }
}
