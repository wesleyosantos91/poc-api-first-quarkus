package io.github.wesleyosantos91.swagger;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ApplicationOpenApiTest {

    @Test
    @DisplayName("Deve validar uma instancia")
    void validarInstancia() {
        ApplicationOpenApi api = new ApplicationOpenApi();
        assertNotNull(api);
    }
}