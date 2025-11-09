import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BatallaPokemonTest {

    @Test
    public void BatallaPokemon_debeHaberGanador() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BatallaPokemon juego = new BatallaPokemon();
        juego.main();

        String salida = out.toString();

        assertTrue(salida.contains("ha ganado la batalla!"));
        assertTrue(juego.juegoTerminado.get());
        assertTrue(juego.hpPikachu <= 0 || juego.hpCharmander <= 0);
    }
}
