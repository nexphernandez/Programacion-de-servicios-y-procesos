import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TardisTest {

    @Test
    public void TardisExisteUnaEraGanadora() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Tardis t = new Tardis();
        t.main();

        String salida = out.toString();

        assertTrue(t.destinoAlcanzado.get());
        assertNotNull(t.eraGanadora.get());
        assertEquals(1, salida.split("llegó primero").length - 1);
    }
}
