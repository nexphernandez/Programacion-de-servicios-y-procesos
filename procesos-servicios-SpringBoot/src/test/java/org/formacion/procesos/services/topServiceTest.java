package org.formacion.procesos.services;

import org.formacion.procesos.services.impl.TopService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TopServiceTest {
    
    TopService comandoTopService;

    @BeforeEach
    void beforeEach(){
        comandoTopService = new TopService();
        comandoTopService.setComando("top");
    }

    @Test
    void validarSinVacioTest(){
        String [] arrayComando = {"top",""};
        boolean valida =comandoTopService.validar(arrayComando);
        Assertions.assertTrue(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarMenosTest(){
        String [] arrayComando = {"top","-"};
        boolean valida =comandoTopService.validar(arrayComando);
        Assertions.assertFalse(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarVacioTest(){
        String [] arrayComando = {"top"," "};
        boolean valida =comandoTopService.validar(arrayComando);
        Assertions.assertTrue(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarFalseTest(){
        String [] arrayComando = {"top","lalalala"};
        boolean valida =comandoTopService.validar(arrayComando);
        Assertions.assertFalse(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarFalse2Test(){
        String [] arrayComando = {"top","-lalalala"};
        boolean valida =comandoTopService.validar(arrayComando);
        Assertions.assertFalse(valida,"Se ha producido un error en la validación");
    }

}
