package org.formacion.procesos.Services;

import org.formacion.procesos.Services.abstractas.ComandoServiceAbstract;
import org.formacion.procesos.domain.ProcessType;
import org.springframework.stereotype.Component;

@Component
public class ComandoPsService extends ComandoServiceAbstract {

    public ComandoPsService(){
        this.setTipo(ProcessType.PS);
    }

    @Override
    public void imprimeMensaje() {
        System.out.println("Estoy llamamdo a ComandoControllerPs");
    }
  
    @Override
    public boolean validar(String[] arrayComando) {
        if (!super.validarComando()) {
            return false;
        }
        String parametro = arrayComando[1];
        return true;
    }

    
}