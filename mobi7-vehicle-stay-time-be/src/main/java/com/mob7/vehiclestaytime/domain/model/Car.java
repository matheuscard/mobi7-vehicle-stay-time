package com.mob7.vehiclestaytime.domain.model;



import java.util.List;

public record Car( String plate, Integer velocity, boolean ignition,List<Position> positions) {
}
