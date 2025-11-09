import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MilleniumFalcon {

    public AtomicBoolean fin = new AtomicBoolean(false);
    public AtomicBoolean destruida = new AtomicBoolean(false);
    public int tiempoMisionMS = 4000;
    public long inicio;

    public AtomicInteger velocidad = new AtomicInteger(0);
    public AtomicInteger escudos = new AtomicInteger(100);

    private final Runnable hanSolo = () -> {
        while (!fin.get()) {
            velocidad.addAndGet(ThreadLocalRandom.current().nextInt(5, 16));

            if (ThreadLocalRandom.current().nextInt(1, 101) <= 5) {
                destruida.set(true); fin.set(true);
                System.out.println("Fallo de hiperimpulsor. ¡La nave se destruye!");
            }

            try { Thread.sleep(150); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            if (System.currentTimeMillis() - inicio >= tiempoMisionMS) fin.set(true);
        }
    };

    private final Runnable chewbacca = () -> {
        while (!fin.get()) {
            escudos.addAndGet(ThreadLocalRandom.current().nextInt(-10, 6));

            if (escudos.get() <= 0) {
                destruida.set(true); fin.set(true);
                System.out.println("¡Escudos agotados! La nave se destruye!");
            }

            try { Thread.sleep(150); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            if (System.currentTimeMillis() - inicio >= tiempoMisionMS) fin.set(true);
        }
    };

    public void main() {
        inicio = System.currentTimeMillis();
        Thread t1 = new Thread(hanSolo);
        Thread t2 = new Thread(chewbacca);
        t1.start(); t2.start();
        try { t1.join(); t2.join(); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        if (!destruida.get()) {
            System.out.println("¡Han y Chewie escapan! Vel=" + velocidad.get() + ", Escudos=" + escudos.get());
        }
    }
}
