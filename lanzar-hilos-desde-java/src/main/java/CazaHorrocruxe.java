import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class CazaHorrocruxe {

    public AtomicBoolean encontrado = new AtomicBoolean(false);
    public AtomicReference<String> ganador = new AtomicReference<>(null);

    private Runnable buscador(String nombre, String ubicacion) {
        return () -> {
            try { Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2001)); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            if (encontrado.compareAndSet(false, true)) {
                ganador.set(nombre);
                System.out.println(nombre + " encontró un Horrocrux en " + ubicacion + ". ¡Búsqueda terminada!");
            }
        };
    }

    public void main() {
        Thread t1 = new Thread(buscador("Harry", "Bosque Prohibido"));
        Thread t2 = new Thread(buscador("Hermione", "Biblioteca Antigua"));
        Thread t3 = new Thread(buscador("Ron", "Mazmorras del castillo"));
        t1.start(); t2.start(); t3.start();
        try { t1.join(); t2.join(); t3.join(); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
