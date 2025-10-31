package org.formacion.procesos.controllers;

import java.util.Scanner;

import org.formacion.procesos.services.impl.LsofService;
import org.formacion.procesos.services.impl.PsHeadService;
import org.formacion.procesos.services.impl.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *  @author: nexphernandez
 *  @version: 1.0.0
 */
@Service
public class CliController {

    @Autowired
    PsHeadService comandoControllerPs;

    @Autowired
    LsofService comandoControllerLsof;

    @Autowired
    TopService comandoControllerTop;

    /**
     * funcion que imprime el menu en pantalla
     */
    public void menuConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Lanzador de Procesos (CLI) Linux/Windows ===\n" +
                "Comandos:\n" +
                "  lsof -i\n" +
                "  top -bn1\n" +
                "  ps aux | head\n");
        String linea = scanner.nextLine();

        if (linea.toUpperCase().startsWith("PS")) {
            comandoControllerPs.procesarLinea(linea);

        }
        if (linea.toUpperCase().startsWith("LSOF")) {

            comandoControllerLsof.procesarLinea(linea);
        }
        if (linea.toUpperCase().startsWith("TOP")) {

            comandoControllerTop.procesarLinea(linea);
        }
        scanner.close();
    }

}
