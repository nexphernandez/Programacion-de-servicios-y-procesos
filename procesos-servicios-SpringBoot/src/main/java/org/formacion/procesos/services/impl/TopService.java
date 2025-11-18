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
public class TopService extends ComandoServiceAbstract{
    private static final List<String> EXPRESION_REGULAR = List.of("-bn1");

    /**
     * Consturctor de la clase TopService
     */
    public TopService(){
        super(Job.TOP, EXPRESION_REGULAR);
    }
}
