package org.smartrail.clock.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ClockSyncService {
    private static final Logger log = LoggerFactory.getLogger(ClockSyncService.class);
    private final Random rnd = new Random();
    private Map<String, Long> vectorClock = new HashMap<>();

    // Cristian algorithm simulated
    @Scheduled(fixedDelayString = "${clock.cristian.delay:20000}")
    public void cristian() {
        long masterTime = Instant.now().toEpochMilli() + rnd.nextInt(50);
        log.info("[Clock][Cristian] Adjusted local clock towards master {}", masterTime);
    }

    // Berkeley algorithm simulated
    @Scheduled(fixedDelayString = "${clock.berkeley.delay:45000}")
    public void berkeley() {
        log.info("[Clock][Berkeley] Calculated average offset and adjusted clocks.");
    }

    // Lamport logical clock simulation
    private long lamport = 0;

    @Scheduled(fixedDelayString = "${clock.lamport.delay:15000}")
    public void lamport() {
        lamport++;
        log.info("[Clock][Lamport] Tick: {}", lamport);
    }

    // Vector clock simulation
    @Scheduled(fixedDelayString = "${clock.vector.delay:30000}")
    public void vector() {
        String node = "node-" + (rnd.nextInt(5) + 1);
        vectorClock.put(node, vectorClock.getOrDefault(node, 0L) + 1);
        log.info("[Clock][Vector] Updated vector clock: {}", vectorClock);
    }

    // Expose getters for controllers/inspection
    public long getLamport() {
        return lamport;
    }

    public Map<String, Long> getVectorClock() {
        return new HashMap<>(vectorClock);
    }
}
