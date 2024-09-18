package com.mob7.vehiclestaytime.domain.model;

import java.util.List;

public record PointInterest(Long id, String name, Integer radius, double latitude, double longitude, List<Position> positions) {
}
