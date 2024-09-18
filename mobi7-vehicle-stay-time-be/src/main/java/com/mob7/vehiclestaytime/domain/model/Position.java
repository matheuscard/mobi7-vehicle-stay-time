package com.mob7.vehiclestaytime.domain.model;

import java.time.LocalDateTime;

public record Position(String pate, LocalDateTime date, double latitude, double longitude, boolean ignition) {
}
