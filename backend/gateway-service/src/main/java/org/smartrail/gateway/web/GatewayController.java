package org.smartrail.gateway.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class GatewayController {
    private final RestTemplate rest = new RestTemplate();

    @Value("${services.booking}")
    private String bookingUrl;

    @Value("${services.analytics}")
    private String analyticsUrl;

    @GetMapping("/api/trains")
    public ResponseEntity<?> getTrains() {
        return rest.getForEntity(bookingUrl + "/api/trains", Object.class);
    }

    @PostMapping("/api/book")
    public ResponseEntity<?> book(@RequestBody Map body) {
        return rest.postForEntity(bookingUrl + "/api/book", body, Object.class);
    }

    @GetMapping("/api/bookings/{userId}")
    public ResponseEntity<?> bookings(@PathVariable Long userId) {
        return rest.getForEntity(bookingUrl + "/api/bookings/" + userId, Object.class);
    }

    @GetMapping("/api/analytics/summary")
    public ResponseEntity<?> analytics() {
        return rest.getForEntity(analyticsUrl + "/api/analytics/summary", Object.class);
    }
}
