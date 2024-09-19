package com.mob7.vehiclestaytime.domain.model;


import java.time.LocalDateTime;

public record Position(Long id, String plate, LocalDateTime date, Integer velocity, double latitude, double longitude, boolean ignition, PointInterest pointInterest) {
}
