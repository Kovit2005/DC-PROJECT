package org.smartrail.analytics.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    private Long id;

    private Long user_id;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    private int seats;
    private BigDecimal totalPrice;
    private OffsetDateTime bookedAt;

    public Train getTrain() { return train; }
    public BigDecimal getTotalPrice() { return totalPrice; }
}
