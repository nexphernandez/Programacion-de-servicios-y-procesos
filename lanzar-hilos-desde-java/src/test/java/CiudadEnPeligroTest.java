import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CiudadEnPeligroTest {

    @Test
    public void CiudadEnPeligroSoloNeutralizaElOtroSeDetiene() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        CiudadEnPeligro c = new CiudadEnPeligro();
        c.main();

        String salida = out.toString();

        assertTrue(c.amenazaNeutralizada.get());
        assertTrue(
            c.ganador.get().equals("Superman") ||
            c.ganador.get().equals("Batman")
        );
        assertEquals(1, salida.split("Amenaza neutralizada").length - 1);
    }
}
