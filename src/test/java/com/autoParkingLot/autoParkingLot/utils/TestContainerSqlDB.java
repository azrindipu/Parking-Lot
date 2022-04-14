package com.autoParkingLot.autoParkingLot.utils;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

public class TestContainerSqlDB {

    private static MySQLContainer container = (MySQLContainer) new MySQLContainer("mysql:8.0.28").withReuse(true);

    @DynamicPropertySource
    public static void prop(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @BeforeAll
    public static void startContainer(){
        container.start();
    }
}
