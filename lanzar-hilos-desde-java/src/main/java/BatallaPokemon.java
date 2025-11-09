import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BatallaPokemon {

    public AtomicBoolean juegoTerminado = new AtomicBoolean(false);
    public int hpPikachu = 100;
    public int hpCharmander = 100;

    private String turno = "Pikachu";

    private final Lock lock = new ReentrantLock();
    private final Condition turnoCambio = lock.newCondition();

    private void atacar(String atacante, boolean esPikachu) {
        int daño = ThreadLocalRandom.current().nextInt(5, 21);

        if (esPikachu) {
            hpCharmander -= daño;
            System.out.println(atacante + " ataca con " + daño + ". HP rival: " + hpCharmander);
            if (hpCharmander <= 0 && juegoTerminado.compareAndSet(false, true)) {
                System.out.println(atacante + " ha ganado la batalla!");
            }
        } else {
            hpPikachu -= daño;
            System.out.println(atacante + " ataca con " + daño + ". HP rival: " + hpPikachu);
            if (hpPikachu <= 0 && juegoTerminado.compareAndSet(false, true)) {
                System.out.println(atacante + " ha ganado la batalla!");
            }
        }

        try { Thread.sleep(ThreadLocalRandom.current().nextInt(200, 601)); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    private final Runnable pikachu = () -> {
        while (!juegoTerminado.get()) {
            lock.lock();
            try {
                while (!turno.equals("Pikachu") && !juegoTerminado.get()) turnoCambio.await();
                if (juegoTerminado.get()) break;
                atacar("Pikachu", true);
                turno = "Charmander";
                turnoCambio.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally { lock.unlock(); }
        }
    };

    private final Runnable charmander = () -> {
        while (!juegoTerminado.get()) {
            lock.lock();
            try {
                while (!turno.equals("Charmander") && !juegoTerminado.get()) turnoCambio.await();
                if (juegoTerminado.get()) break;
                atacar("Charmander", false);
                turno = "Pikachu";
                turnoCambio.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally { lock.unlock(); }
        }
    };

    public void main() {
        Thread t1 = new Thread(pikachu);
        Thread t2 = new Thread(charmander);
        t1.start();
        t2.start();
        try { t1.join(); t2.join(); } 
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
