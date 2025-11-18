package org.formacion.procesos.services.impl;


import java.util.List;

import org.formacion.procesos.domain.Job;
import org.formacion.procesos.services.impl.abstractas.ComandoServiceAbstract;
import org.springframework.stereotype.Component;
/**
 *  @author: nexphernandez
 *  @version: 1.0.0
 */
@Component
public class PsHeadService extends ComandoServiceAbstract {

    private static final List<String> EXPRESION_REGULAR = List.of("aux", "|","head");

    /**
     * Consturctor de la clase PsHeadService
     */
    public PsHeadService(){
        super(Job.PS, EXPRESION_REGULAR);
        
    }
        
}