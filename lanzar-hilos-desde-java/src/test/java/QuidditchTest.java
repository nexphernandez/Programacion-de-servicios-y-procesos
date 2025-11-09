import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuidditchTest {

    @Test
    public void Quidditch_terminaCuandoSnitchAtrapada() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Quidditch q = new Quidditch();
        q.main();

        String salida = out.toString();

        assertTrue(salida.contains("¡Snitch dorada atrapada!"));
        assertTrue(salida.contains("Marcador final:"));
    }
}
