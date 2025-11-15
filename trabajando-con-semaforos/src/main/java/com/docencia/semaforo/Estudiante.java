package com.docencia.semaforo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class Estudiante extends Thread {

    private final String nombre;
    private static final Semaphore semaforo = new Semaphore(4, true);

    /**
     * Contructor vacio
     */
    public Estudiante() {
        this.nombre = "";
    }

    /**
     * Constructor con el nombre del estudiante
     *
     * @param nombre nombre del estudiante
     */
    public Estudiante(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            semaforo.acquire();
            try {
                int numeroEquipo = semaforo.availablePermits() + 1;
                System.out.println("El " + nombre + " ha comenzado a utilizar el equipo " + numeroEquipo);
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 5001));
                System.out.println("El " + nombre + " ha finalizado con el equipo " + numeroEquipo);
            } finally {
                semaforo.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public static void main(String[] args) {
        Thread estudiante1 = new Thread(new Estudiante("Estudiante 1"));
        Thread estudiante2 = new Thread(new Estudiante("Estudiante 2"));
        Thread estudiante3 = new Thread(new Estudiante("Estudiante 3"));
        Thread estudiante4 = new Thread(new Estudiante("Estudiante 4"));
        Thread estudiante5 = new Thread(new Estudiante("Estudiante 5"));
        Thread estudiante6 = new Thread(new Estudiante("Estudiante 6"));

        estudiante1.start();
        estudiante2.start();
        estudiante3.start();
        estudiante4.start();
        estudiante5.start();
        estudiante6.start();

        try {
            estudiante1.join();
            estudiante2.join();
            estudiante3.join();
            estudiante4.join();
            estudiante5.join();
            estudiante6.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
