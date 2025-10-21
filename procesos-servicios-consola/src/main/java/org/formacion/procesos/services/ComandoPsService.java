package org.formacion.procesos.services;


import org.formacion.procesos.domain.ProcessType;
import org.formacion.procesos.services.abstractas.ComandoServiceAbstract;
import org.springframework.stereotype.Component;

@Component
public class ComandoPsService extends ComandoServiceAbstract {

    public ComandoPsService(){
        this.setTipo(ProcessType.PS);
        this.setExpresionRegular("^((-?(xa|a|x|aux))|\s*)$");
    }
        
}