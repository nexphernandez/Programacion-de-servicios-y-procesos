package com.docencia.semaforo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class SemaforoCasero implements Runnable {

    private final String color;
    private static final AtomicBoolean running = new AtomicBoolean(false);
    private static final Semaphore semaphore = new Semaphore(1, true);

    /**
     * Constructor vacio
     */
    public SemaforoCasero() {
        this.color = "";
    }

    /**
     * Constructor con el color del semaforo
     *
     * @param color color del semaforo
     */
    public SemaforoCasero(String color) {
        this.color = color;
    }

    /**
     * Funcion para verificar el tiempo del semaforo sabiendo el color
     * @return tiempo en milisegundos
     */
    public int tiempoSemaforo() {
        switch (color) {
            case "Rojo", "Verde": return 3000;
            case "Ambar": return 1000;
            default: return 0;
        }
    }

    @Override
    public void run() {
            while (!running.get()) {
                try {
                    semaphore.acquire();
                    try {
                        if (running.get()) {
                            break;
                        }
                        System.out.println("Color actual: " + color);
                        Thread.sleep(tiempoSemaforo());
                    } finally {
                        semaphore.release(); 
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
            }
        
        
    }
    

    public static void main(String[] args) {
        Thread rojo = new Thread(new SemaforoCasero("Rojo"));
        Thread verde = new Thread(new SemaforoCasero("Verde"));
        Thread ambar = new Thread(new SemaforoCasero("Ambar"));

        rojo.start();
        verde.start();
        ambar.start();
        

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        running.set(true);


        try {
            rojo.join();
            verde.join();
            ambar.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
