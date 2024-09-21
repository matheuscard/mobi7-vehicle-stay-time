package com.mob7.vehiclestaytime.domain.model;


public record CarStayTime(String plate,
                          String stayTime,
                          PointInterest pointInterest,
                          Position lastPosition) {
}
