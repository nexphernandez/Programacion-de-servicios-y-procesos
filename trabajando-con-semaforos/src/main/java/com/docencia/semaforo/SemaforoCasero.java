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
    private static String turno = "Rojo";

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
     * Metodo que gestiona el tiempo segun el color y actualiza el turno
     * @return tiempo en milisegundos
     */
    private int gestionarColor() {
        int tiempo = 0;

        switch (color) {
            case "Rojo":
                tiempo = 3000;
                turno = "Verde";
                break;

            case "Verde":
                tiempo = 3000;
                turno = "Ambar";
                break;

            case "Ambar":
                tiempo = 1000;
                turno = "Rojo";
                break;
        }

        return tiempo;
    }

    @Override
    public void run() {
        while (!running.get()) {
            try {
                semaphore.acquire();

                if (!turno.equals(color)) {
                    continue;
                }

                System.out.println("Color actual: " + color);

                int tiempo = gestionarColor();
                Thread.sleep(tiempo);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
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
