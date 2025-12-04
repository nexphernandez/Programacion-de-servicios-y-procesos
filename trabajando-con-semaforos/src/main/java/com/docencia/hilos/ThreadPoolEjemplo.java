package main.java.com.docencia.hilos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolEjemplo {
    public static void main(String[] args) {
        // Creamos un ThreadPool con 3 hilos fijos
        ExecutorService threadPool = Executors.newFixedThreadPool(3);


            Runnable tarea = new Tarea("Tarea ");
            threadPool.submit(tarea); // Enviamos las tareas al ThreadPool

    }

    static class Tarea implements Runnable {
        private String nombre;

        public Tarea(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {
            System.out.println(nombre + " está siendo ejecutada por el hilo " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000); // Simulamos que la tarea toma 2 segundos en ejecutarse
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(nombre + " ha terminado.");
        }
    }
}
