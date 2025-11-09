import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MilleniumFalconTest {

    @Test
    public void MilleniumFalcon_finalizaConEscapeODestruccion() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        MilleniumFalcon m = new MilleniumFalcon();
        m.main();

        String salida = out.toString();

        boolean destr = salida.contains("se destruye");
        boolean esc = salida.contains("escapan");

        assertTrue(destr ^ esc);
    }
}
