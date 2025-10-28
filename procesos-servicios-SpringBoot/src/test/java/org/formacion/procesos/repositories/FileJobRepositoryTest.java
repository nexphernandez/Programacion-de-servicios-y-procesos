package org.formacion.procesos.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileJobRepositoryTest {

    private static final String NO_SE_HA_OBTENIDO_EL_RESULTADO_ESPERADO = "No se ha obtenido el resultado esperado";
    static FileJobRepository fileJobRepository;

    @BeforeAll
    static void beforeAll(){
        fileJobRepository = new FileJobRepository();
        fileJobRepository.setFileName("fichero-test.txt");
    }

    @Test
    void addContenidoTest(){
        boolean resultado = fileJobRepository.add("texto");
        Assertions.assertTrue(resultado, NO_SE_HA_OBTENIDO_EL_RESULTADO_ESPERADO);
    }
}
