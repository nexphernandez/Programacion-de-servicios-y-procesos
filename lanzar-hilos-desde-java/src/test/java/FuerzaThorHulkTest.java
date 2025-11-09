import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FuerzaThorHulkTest {

    @Test
    public void FuerzaThorHulk_terminaPorTiempo_yDeclaraResultado() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        FuerzaThorHulk f = new FuerzaThorHulk();
        f.main();

        String salida = out.toString();

        assertTrue(salida.contains("¡Tiempo!"));
        assertTrue(salida.contains("gana") || salida.contains("Empate"));
    }
}
