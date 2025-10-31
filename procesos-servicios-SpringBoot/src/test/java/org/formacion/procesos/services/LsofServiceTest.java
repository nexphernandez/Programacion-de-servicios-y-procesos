package org.formacion.procesos.services;

import org.formacion.procesos.services.impl.LsofService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LsofServiceTest {
    
    LsofService comandoLsofService;

    @BeforeEach
    void beforeEach(){
        comandoLsofService = new LsofService();
        comandoLsofService.setComando("lsof");
    }

    @Test
    void validarITest(){
        String [] arrayComando = {"lsof","-i"};
        boolean valida =comandoLsofService.validar(arrayComando);
        Assertions.assertTrue(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarMenosTest(){
        String [] arrayComando = {"lsof","-"};
        boolean valida =comandoLsofService.validar(arrayComando);
        Assertions.assertFalse(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarVacioTest(){
        String [] arrayComando = {"lsof"," "};
        boolean valida =comandoLsofService.validar(arrayComando);
        Assertions.assertTrue(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarSinVacioTest(){
        String [] arrayComando = {"lsof"};
        boolean valida =comandoLsofService.validar(arrayComando);
        Assertions.assertTrue(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarFalseTest(){
        String [] arrayComando = {"lsof","lalalala"};
        boolean valida =comandoLsofService.validar(arrayComando);
        Assertions.assertFalse(valida,"Se ha producido un error en la validación");
    }

    @Test
    void validarFalse2Test(){
        String [] arrayComando = {"lsof","-lalalala"};
        boolean valida =comandoLsofService.validar(arrayComando);
        Assertions.assertFalse(valida,"Se ha producido un error en la validación");
    }

    @Test
    void procesarLineaTest(){
        String linea = "lsof -i";
        boolean procesado = comandoLsofService.procesarLinea(linea);
        Assertions.assertTrue(procesado, "error al procesar linea");
    }

    @Test
    void procesarLineaMalTest(){
        String linea = "lsoaf -i";
        boolean procesado = comandoLsofService.procesarLinea(linea);
        Assertions.assertFalse(procesado, "error al procesar linea");
    }

        @Test
    void procesarLineaSoloTest(){
        String linea = "lsof ";
        boolean procesado = comandoLsofService.procesarLinea(linea);
        Assertions.assertTrue(procesado, "error al procesar linea");
    }
}
