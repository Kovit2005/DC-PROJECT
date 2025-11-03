package org.smartrail.nodemanager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ElectionService {
    private static final Logger log = LoggerFactory.getLogger(ElectionService.class);
    private final Random rnd = new Random();
    private volatile String currentLeader = null;

    // Run periodic health checks and sometimes trigger leader elections
    @Scheduled(fixedDelayString = "${node.election.delay:15000}")
    public void healthCheckAndMaybeElect() {
        // Simulated health checks
        boolean nodeFailure = rnd.nextInt(100) < 5; // 5% chance
        if (nodeFailure) {
            log.warn("[NodeManager] Simulated node failure detected. Triggering election...");
            runBullyElection();
        } else {
            log.info("[NodeManager] Health check OK. Current leader: {}", currentLeader);
        }
    }

    private void runBullyElection() {
        // Simple simulated Bully algorithm: pick highest-id node (randomized here)
        String newLeader = "node-" + (rnd.nextInt(5) + 1);
        log.info("[NodeManager][Bully] Election completed. New leader: {}", newLeader);
        currentLeader = newLeader;
    }

    // Also periodically run a ring-style check
    @Scheduled(fixedDelayString = "${node.ring.delay:45000}")
    public void ringCheck() {
        // Simulated ring algorithm
        String newLeader = "node-" + (rnd.nextInt(5) + 1);
        log.info("[NodeManager][Ring] Ring election result: {}", newLeader);
        currentLeader = newLeader;
    }

    public String getCurrentLeader() {
        return currentLeader;
    }
}
