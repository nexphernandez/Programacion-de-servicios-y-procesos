package org.formacion.procesos.component;

import org.formacion.procesos.component.interfaces.IFicheroComponent;
import org.formacion.procesos.repository.interfaces.IFicheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FicheroComponent implements IFicheroComponent {

    @Autowired
    @Qualifier("baseDatosRepository")
    IFicheroRepository baseDatosRepository;

        @Autowired
    @Qualifier("ficheroRepository")
    IFicheroRepository ficheroRepository;

    @Override
    public String mensaje() {
        return ficheroRepository.saludar() + " " + baseDatosRepository.saludar();
    }

}
