package org.formacion.procesos.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComandoLsServiceTest {
    
    ComandoLsService comandoLsService;

    @BeforeEach
    void beforeEach(){
        comandoLsService = new ComandoLsService();
        comandoLsService.setComando("ls");
    }

    @Test
    void validarLaTest(){
        String [] arrayComando = {"ls","-la"};
        boolean valida =comandoLsService.validar(arrayComando);
        Assertions.assertTrue(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarMenosTest(){
        String [] arrayComando = {"ls","-"};
        boolean valida =comandoLsService.validar(arrayComando);
        Assertions.assertFalse(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarVacioTest(){
        String [] arrayComando = {"ls"," "};
        boolean valida =comandoLsService.validar(arrayComando);
        Assertions.assertTrue(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarFalseTest(){
        String [] arrayComando = {"ls","lalalala"};
        boolean valida =comandoLsService.validar(arrayComando);
        Assertions.assertFalse(valida,"Se ha producido un error en la validación");
    }

}
