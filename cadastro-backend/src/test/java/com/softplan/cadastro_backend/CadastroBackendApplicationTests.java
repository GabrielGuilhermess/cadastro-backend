package com.softplan.cadastro_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(properties = "spring.flyway.enabled=false")
@ActiveProfiles("dev")
public class CadastroBackendApplicationTests {

    @Test
    public void contextLoads() {
    }

    /**
     * Testa se a aplicação inicializa corretamente sem lançar exceções.
     */
    @Test
    void testMainMethod() {
        assertDoesNotThrow(() -> CadastroBackendApplication.main(new String[]{}));
    }

}
