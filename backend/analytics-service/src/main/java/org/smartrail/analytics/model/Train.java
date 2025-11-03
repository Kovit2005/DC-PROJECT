package org.smartrail.analytics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "trains")
public class Train {
    @Id
    private Long id;
    private String name;
    private String route;
    private int seats_available;
    private BigDecimal price;

    public Long getId() { return id; }
    public String getRoute() { return route; }
}
