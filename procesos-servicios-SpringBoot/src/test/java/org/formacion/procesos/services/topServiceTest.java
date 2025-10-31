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
    void validarTest(){
        String[] arrayCommand = {"top","-bn1"};
        boolean validar = comandoTopService.validar(arrayCommand);
        Assertions.assertTrue(validar, "error de validacion");
    }

    @Test
    void validarSinVacioTest(){
        String [] arrayComando = {"top"};
        boolean valida =comandoTopService.validar(arrayComando);
        Assertions.assertTrue(valida,"Se ha producido un error en la validación");
    }


    @Test
    void validarTestMenos(){
        String[] arrayComando = {"top","-"};
        boolean valida = comandoTopService.validar(arrayComando);
        Assertions.assertFalse(valida,"se ha producido un error en la validacion");
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

    @Test
    void procesarLineaTest(){
        String linea = "top -bn1";
        boolean procesado = comandoTopService.procesarLinea(linea);
        Assertions.assertTrue(procesado, "error al procesar linea");
    }

    @Test
    void procesarLineaMalTest(){
        String linea = "tapy -i";
        boolean procesado = comandoTopService.procesarLinea(linea);
        Assertions.assertFalse(procesado, "error al procesar linea");
    }

    @Test
    void procesarLineaSoloTest(){
        String linea = "top ";
        boolean procesado = comandoTopService.procesarLinea(linea);
        Assertions.assertTrue(procesado, "error al procesar linea");
    }

}
