import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FabricaDroidsTest {

    @Test
    public void FabricaDroidsNoSeActivaAntesYCuentaCorrecta() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        FabricaDroids f = new FabricaDroids();
        f.main();

        String salida = out.toString();

        for (int k = 1; k <= f.N; k++) {
            int idxE = salida.indexOf("Ensamblado Droid-" + k);
            int idxA = salida.indexOf("Activado Droid-" + k);

            assertTrue(idxE != -1 && idxA != -1);
            assertTrue(idxE < idxA);
        }

        assertEquals(f.N, f.activados.get());
    }
}
