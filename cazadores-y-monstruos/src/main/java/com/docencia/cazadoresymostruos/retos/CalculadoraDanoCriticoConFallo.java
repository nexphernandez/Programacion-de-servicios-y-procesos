package com.docencia.cazadoresymostruos.retos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalculadoraDanoCriticoConFallo {

    static class Ataque {
        final String atacante;
        final int danoBase;
        final double probCritico;
        final double multiplicadorCritico;
        final double probFallo;

        Ataque(String atacante, int danoBase, double probCritico,
               double multiplicadorCritico, double probFallo) {
            this.atacante = atacante;
            this.danoBase = danoBase;
            this.probCritico = probCritico;
            this.multiplicadorCritico = multiplicadorCritico;
            this.probFallo = probFallo;
        }
    }

    static class TareaCalcularDano implements Callable<Integer> {
        private final Ataque ataque;
        TareaCalcularDano(Ataque ataque) { this.ataque = ataque; }

        @Override
        public Integer call() {
            boolean fallo = Math.random() < ataque.probFallo;
            if (fallo) return 0;

            boolean critico = Math.random() < ataque.probCritico;
            double mult = critico ? ataque.multiplicadorCritico : 1.0;
            return (int)(ataque.danoBase * mult);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(4);

        Ataque[] ataques = {
                new Ataque("Mago", 120, 0.3, 2.5, 0.1),
                new Ataque("Guerrero", 150, 0.15, 2.0, 0.05),
                new Ataque("Pícaro", 90, 0.5, 3.0, 0.2),
                new Ataque("Arquera", 110, 0.35, 2.2, 0.15)
        };

        List<Future<Integer>> futuros = new ArrayList<>();
        for (Ataque a : ataques) futuros.add(pool.submit(new TareaCalcularDano(a)));

        int total = 0;
        int n = futuros.size();
        for (Future<Integer> f : futuros) total += f.get();

        double media = (double) total / n;
        System.out.println("Daño total: " + total + " | Media: " + media);

        pool.shutdown();
    }
}
