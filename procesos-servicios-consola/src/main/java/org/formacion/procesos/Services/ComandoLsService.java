package org.formacion.procesos.Services;

import org.formacion.procesos.Services.abstractas.ComandoServiceAbstract;
import org.formacion.procesos.domain.ProcessType;
import org.springframework.stereotype.Component;

@Component
public class ComandoLsService extends ComandoServiceAbstract {

    public ComandoLsService(){
        this.setTipo(ProcessType.LS);
    }

    @Override
    public void imprimeMensaje() {
        System.out.println("Estoy llamamdo a ComandoControllerLs");
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