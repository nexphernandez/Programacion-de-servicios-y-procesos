package org.formacion.procesos.services;

import org.formacion.procesos.services.impl.PsHeadService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PsHeadServiceTest {
    
    PsHeadService comandoPsHeadService;

    @BeforeEach
    void beforeEach(){
        comandoPsHeadService = new PsHeadService();
        comandoPsHeadService.setComando("ps");
    }

    @Test
    void validarXaTest(){
        String [] arrayComando = {"ps","xa"};
        boolean valida =comandoPsHeadService.validar(arrayComando);
        Assertions.assertTrue(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarXaMenosTest(){
        String [] arrayComando = {"ps","-xa"};
        boolean valida =comandoPsHeadService.validar(arrayComando);
        Assertions.assertTrue(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarVacioTest(){
        String [] arrayComando = {"ps"," "};
        boolean valida =comandoPsHeadService.validar(arrayComando);
        Assertions.assertTrue(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarSinVacioTest(){
        String [] arrayComando = {"ps",""};
        boolean valida =comandoPsHeadService.validar(arrayComando);
        Assertions.assertTrue(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarFalseTest(){
        String [] arrayComando = {"ps","lalalala"};
        boolean valida =comandoPsHeadService.validar(arrayComando);
        Assertions.assertFalse(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarFalse2Test(){
        String [] arrayComando = {"ps","-lalalala"};
        boolean valida =comandoPsHeadService.validar(arrayComando);
        Assertions.assertFalse(valida,"Se ha producido un error en la validación");
    }

}
