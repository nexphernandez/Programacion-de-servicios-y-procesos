package com.docencia.cazadoresymostruos.retos;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SpawnsMundoAbiertoConEpicos {

    static class SpawnTarea implements Runnable {
        private final String[] zonas = {"Bosque", "Ruinas", "Pantano"};
        private final String[] enemigos = {"Slime", "Esqueleto", "Bandido"};

        @Override
        public void run() {
            String zona = zonas[(int)(Math.random() * zonas.length)];
            String enemigo = enemigos[(int)(Math.random() * enemigos.length)];

            // 5% de spawn épico
            if (Math.random() < 0.05) enemigo = "Dragón Ancestral Épico";

            System.out.println("[" + LocalTime.now() + "] Spawn de " + enemigo + " en " + zona);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        scheduler.scheduleAtFixedRate(new SpawnTarea(), 0, 2, TimeUnit.SECONDS);

        Thread.sleep(12000); // deja correr unos segundos
        scheduler.shutdown();
        scheduler.awaitTermination(5, TimeUnit.SECONDS);
    }
}
