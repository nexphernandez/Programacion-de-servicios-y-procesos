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
    void validarAuxTest(){
        String [] arrayComando = {"ps","-aux | head"};
        boolean valida =comandoPsHeadService.validar(arrayComando);
        Assertions.assertTrue(valida,"Se ha producido un error en la validación");
    }

    
    @Test
    void validarconMenosTest(){
        String[] arrayCommand = {"ps","-aux | head"};
        boolean validar = comandoPsHeadService.validar(arrayCommand);
        Assertions.assertTrue(validar, "error de validacion");
    }

    @Test
    void validarTestVacio(){
        String[] arrayComando = {"ps"};
        boolean valida = comandoPsHeadService.validar(arrayComando);
        Assertions.assertTrue(valida,"se ha producido un error en la validacion");
    }

    @Test
    void validarTestFalse(){
        String[] arrayComando = {"ps","au |"};
        boolean valida = comandoPsHeadService.validar(arrayComando);
        Assertions.assertFalse(valida,"se ha producido un error en la validacion");
    }

    @Test
    void validarTestFalse2(){
        String[] arrayComando = {"ps","-"};
        boolean valida =comandoPsHeadService.validar(arrayComando);
        Assertions.assertFalse(valida,"se ha producido un error en la validacion");
    }

    @Test
    void procesarLineaTest(){
        String linea = "ps aux | head";
        boolean procesado = comandoPsHeadService.procesarLinea(linea);
        Assertions.assertTrue(procesado, "error al procesar linea");
    }

    @Test
    void procesarLineaMalTest(){
        String linea = "paso au / head";
        boolean procesado = comandoPsHeadService.procesarLinea(linea);
        Assertions.assertFalse(procesado, "error al procesar linea");
    }
    
    @Test
    void procesarLineaSoloTest(){
        String linea = "ps ";
        boolean procesado = comandoPsHeadService.procesarLinea(linea);
        Assertions.assertTrue(procesado, "error al procesar linea");
    }

}
