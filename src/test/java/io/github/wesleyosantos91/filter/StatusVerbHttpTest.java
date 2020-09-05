package io.github.wesleyosantos91.filter;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class StatusVerbHttpTest {

    @Test
    void testExpectedException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           StatusVerbHttp.getHttpCode("ERRO");
        });
    }
}