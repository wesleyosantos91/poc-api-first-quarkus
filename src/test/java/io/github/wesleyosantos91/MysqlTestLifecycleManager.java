package io.github.wesleyosantos91;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.MySQLContainer;

import java.util.HashMap;
import java.util.Map;

public class MysqlTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

    public static final MySQLContainer<?> MYSQL = new MySQLContainer<>("mysql:8.0.21");

    @Override
    public Map<String, String> start() {
        MYSQL.start();
        Map<String, String> propriedades = new HashMap<>();

        //Banco de dados
        propriedades.put("quarkus.datasource.url", MYSQL.getJdbcUrl());
        propriedades.put("quarkus.datasource.username", MYSQL.getUsername());
        propriedades.put("quarkus.datasource.password", MYSQL.getPassword());

        return propriedades;
    }

    @Override
    public void stop() {
        if (MYSQL != null && MYSQL.isRunning()) {
            MYSQL.stop();
        }
    }
}