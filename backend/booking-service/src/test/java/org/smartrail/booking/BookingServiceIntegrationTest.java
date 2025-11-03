package org.smartrail.booking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.smartrail.booking.repo.TrainRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookingServiceIntegrationTest {
    static PostgreSQLContainer<?> pg = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb").withUsername("test").withPassword("test");

    static {
        pg.start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url", pg::getJdbcUrl);
        r.add("spring.datasource.username", pg::getUsername);
        r.add("spring.datasource.password", pg::getPassword);
    }

    @Autowired
    private TrainRepository trainRepository;

    @Test
    void contextLoadsAndSeedRuns() {
        // Flyway migrations run on startup; ensure trains table exists after context load
        assertThat(trainRepository.findAll()).isNotNull();
    }
}
