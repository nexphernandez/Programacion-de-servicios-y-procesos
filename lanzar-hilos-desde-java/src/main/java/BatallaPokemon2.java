import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BatallaPokemon2 {
    public static volatile boolean juegoTerminado = false;
    public int hpPikachu = 100;
    public int hpCharmander = 100;
    public String turno = "Pikachu";
    public final ReentrantLock lock = new ReentrantLock();
    public final Condition turnoCambio = lock.newCondition();

    public void atacar(String atacante, int hpObjetivo) {

        int danio = ThreadLocalRandom.current().nextInt(5, 20);

        hpObjetivo = hpObjetivo - danio;
        System.out.println(atacante + "ataca con " + danio + " de daño. HP rival: " + hpObjetivo);

        if (hpObjetivo <= 0 && !juegoTerminado) {
            juegoTerminado = true;
            System.out.println(atacante + " ha ganado la batalla!");
        }
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(200, 600));

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public class HiloPikachu implements Runnable{

        @Override
        public void run() {
            while (!juegoTerminado) {

            }
        }
        
    }

}
