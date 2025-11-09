import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class FabricaDroids {

    public BlockingQueue<String> ensamblados = new LinkedBlockingQueue<>();
    public int N = 10;
    public AtomicInteger activados = new AtomicInteger(0);

    private final Runnable ensamblador = () -> {
        for (int i = 1; i <= N; i++) {
            try { Thread.sleep(ThreadLocalRandom.current().nextInt(100, 301)); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            String d = "Droid-" + i;
            System.out.println("Ensamblado " + d);
            try { ensamblados.put(d); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    };

    private final Runnable activador = () -> {
        int cuenta = 0;
        while (cuenta < N) {
            try {
                String d = ensamblados.take();
                System.out.println("Activado " + d);
                activados.incrementAndGet();
                cuenta++;
                Thread.sleep(ThreadLocalRandom.current().nextInt(50, 151));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    };

    public void main() {
        Thread tE = new Thread(ensamblador);
        Thread tA = new Thread(activador);
        tE.start(); tA.start();
        try { tE.join(); tA.join(); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
