package com.mob7.vehiclestaytime.domain.model;



import java.util.List;

public record Car(String plate,List<Position> positions) {
}
