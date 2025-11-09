import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Tardis {

    public AtomicBoolean destinoAlcanzado = new AtomicBoolean(false);
    public AtomicReference<String> eraGanadora = new AtomicReference<>(null);

    private Runnable viaje(String era) {
        return () -> {
            try { Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2001)); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            if (destinoAlcanzado.compareAndSet(false, true)) {
                eraGanadora.set(era);
                System.out.println("La TARDIS llegó primero a " + era);
            }
        };
    }

    public void main() {
        String[] eras = {"Roma Antigua", "Futuro Lejano", "Era Victoriana", "Año 3000"};
        Thread[] hilos = new Thread[eras.length];

        for (int i = 0; i < eras.length; i++) {
            hilos[i] = new Thread(viaje(eras[i]));
            hilos[i].start();
        }

        for (Thread t : hilos) {
            try { t.join(); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }
}
