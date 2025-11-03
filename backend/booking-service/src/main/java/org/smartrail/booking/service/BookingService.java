package org.smartrail.booking.service;

import org.smartrail.booking.model.Booking;
import org.smartrail.booking.model.Train;
import org.smartrail.booking.repo.BookingRepository;
import org.smartrail.booking.repo.TrainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class BookingService {
    private final TrainRepository trainRepo;
    private final BookingRepository bookingRepo;

    public BookingService(TrainRepository trainRepo, BookingRepository bookingRepo) {
        this.trainRepo = trainRepo;
        this.bookingRepo = bookingRepo;
    }

    public List<Train> listTrains() {
        return trainRepo.findAll();
    }

    @Transactional
    public Booking book(Long userId, Long trainId, int seats) {
        // PESSIMISTIC_WRITE lock to avoid overbooking
        Train train = trainRepo.findByIdForUpdate(trainId).orElseThrow(() -> new RuntimeException("Train not found"));
        if (train.getSeatsAvailable() < seats) throw new RuntimeException("Not enough seats");
        train.setSeatsAvailable(train.getSeatsAvailable() - seats);
        trainRepo.saveAndFlush(train);

        Booking b = new Booking();
        b.setUserId(userId);
        b.setTrain(train);
        b.setSeats(seats);
        b.setTotalPrice(train.getPrice().multiply(BigDecimal.valueOf(seats)));
        b.setBookedAt(OffsetDateTime.now());
        Booking saved = bookingRepo.save(b);

        // Optional: emit Kafka event if configured (left as comment placeholder)

        return saved;
    }

    public List<Booking> getBookingsForUser(Long userId) {
        return bookingRepo.findByUserId(userId);
    }
}
