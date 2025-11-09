import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ExploradoresJediTest {

    @Test
    public void ExploradoresJediUnSoloGanador() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ExploradoresJedi e = new ExploradoresJedi();
        e.main();

        String salida = out.toString();

        assertTrue(e.pistaEncontrada.get());
        assertEquals(1, salida.split("halló una pista").length - 1);
    }
}
