package org.smartrail.booking.web;

import org.smartrail.booking.model.Booking;
import org.smartrail.booking.model.Train;
import org.smartrail.booking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/trains")
    public ResponseEntity<List<Train>> listTrains() {
        return ResponseEntity.ok(bookingService.listTrains());
    }

    @PostMapping("/book")
    public ResponseEntity<Booking> book(@RequestBody BookRequest r) {
        Booking b = bookingService.book(r.userId, r.trainId, r.seats);
        return ResponseEntity.ok(b);
    }

    @GetMapping("/bookings/{userId}")
    public ResponseEntity<List<Booking>> bookings(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsForUser(userId));
    }

    public static class BookRequest {
        public Long userId;
        public Long trainId;
        public int seats;
    }
}
