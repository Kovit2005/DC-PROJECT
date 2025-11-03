package org.smartrail.clock.web;

import org.smartrail.clock.service.ClockSyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/clock")
public class ClockController {
    private final ClockSyncService clockSyncService;

    public ClockController(ClockSyncService clockSyncService) {
        this.clockSyncService = clockSyncService;
    }

    @GetMapping("/state")
    public ResponseEntity<?> state() {
        return ResponseEntity.ok(Map.of(
                "lamport", clockSyncService.getLamport(),
                "vector", clockSyncService.getVectorClock()
        ));
    }
}
