import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class FuerzaThorHulk {

    public int durationMS = 5000;
    public AtomicBoolean tiempoTerminado = new AtomicBoolean(false);
    public AtomicInteger totalThor = new AtomicInteger(0);
    public AtomicInteger totalHulk = new AtomicInteger(0);

    private final Runnable temporizador = () -> {
        try { Thread.sleep(durationMS); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        tiempoTerminado.set(true);
        System.out.println("¡Tiempo!");
    };

    private final Runnable thor = () -> {
        while (!tiempoTerminado.get()) {
            int peso = ThreadLocalRandom.current().nextInt(5, 21);
            totalThor.addAndGet(peso);
            try { Thread.sleep(ThreadLocalRandom.current().nextInt(50, 121)); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    };

    private final Runnable hulk = () -> {
        while (!tiempoTerminado.get()) {
            int peso = ThreadLocalRandom.current().nextInt(5, 21);
            totalHulk.addAndGet(peso);
            try { Thread.sleep(ThreadLocalRandom.current().nextInt(50, 121)); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    };

    public void main() {
        Thread t0 = new Thread(temporizador);
        Thread t1 = new Thread(thor);
        Thread t2 = new Thread(hulk);
        t0.start(); t1.start(); t2.start();
        try { t0.join(); t1.join(); t2.join(); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        int th = totalThor.get();
        int hk = totalHulk.get();

        if (th > hk) System.out.println("Thor gana con " + th + " vs " + hk);
        else if (hk > th) System.out.println("Hulk gana con " + hk + " vs " + th);
        else System.out.println("Empate: " + th);
    }
}
