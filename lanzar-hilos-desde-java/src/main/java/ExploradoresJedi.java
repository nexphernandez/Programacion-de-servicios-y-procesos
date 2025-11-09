import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class ExploradoresJedi {

    public AtomicBoolean pistaEncontrada = new AtomicBoolean(false);
    public AtomicReference<String> ganador = new AtomicReference<>(null);

    private Runnable jedi(String nombre, String planeta) {
        return () -> {
            try { Thread.sleep(ThreadLocalRandom.current().nextInt(400, 1501)); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            if (pistaEncontrada.compareAndSet(false, true)) {
                ganador.set(nombre);
                System.out.println(nombre + " halló una pista en " + planeta + ". Fin de búsqueda.");
            }
        };
    }

    public void main() {
        Thread t1 = new Thread(jedi("Kenobi", "Tatooine"));
        Thread t2 = new Thread(jedi("Skywalker", "Dagobah"));
        t1.start(); t2.start();
        try { t1.join(); t2.join(); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
