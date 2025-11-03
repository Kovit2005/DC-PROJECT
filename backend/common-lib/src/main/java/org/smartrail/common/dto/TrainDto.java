package org.smartrail.common.dto;

import java.math.BigDecimal;

public class TrainDto {
    public Long id;
    public String name;
    public String route;
    public int seatsAvailable;
    public BigDecimal price;

    // simple DTO used across services
}
