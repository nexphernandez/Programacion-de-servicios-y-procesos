import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class CiudadEnPeligro {

    public AtomicBoolean amenazaNeutralizada = new AtomicBoolean(false);
    public String[] zonasA = {"Norte", "Centro", "Este"};
    public String[] zonasB = {"Oeste", "Sur"};
    public AtomicReference<String> ganador = new AtomicReference<>(null);

    private final Runnable superman = () -> {
        for (String zona : zonasA) {
            if (amenazaNeutralizada.get()) return;
            try { Thread.sleep(ThreadLocalRandom.current().nextInt(200, 501)); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            System.out.println("Superman salvó " + zona);
        }
        if (amenazaNeutralizada.compareAndSet(false, true)) {
            ganador.set("Superman");
            System.out.println("Amenaza neutralizada por Superman");
        }
    };

    private final Runnable batman = () -> {
        for (String zona : zonasB) {
            if (amenazaNeutralizada.get()) return;
            try { Thread.sleep(ThreadLocalRandom.current().nextInt(300, 601)); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            System.out.println("Batman salvó " + zona);
        }
        if (amenazaNeutralizada.compareAndSet(false, true)) {
            ganador.set("Batman");
            System.out.println("Amenaza neutralizada por Batman");
        }
    };

    public void main() {
        Thread t1 = new Thread(superman);
        Thread t2 = new Thread(batman);
        t1.start(); t2.start();
        try { t1.join(); t2.join(); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
