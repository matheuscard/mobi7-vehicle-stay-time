package com.mob7.vehiclestaytime.domain.model;


public record Position(Long id, String plate, String date,Integer velocity, double latitude, double longitude, boolean ignition) {
}
