package com.mob7.vehiclestaytime.application.gateways;

import com.mob7.vehiclestaytime.domain.model.Car;
import com.mob7.vehiclestaytime.domain.model.Position;

import java.util.List;

public interface CarStayTimeGateway {
    List<Car> getCarsWithStayTimeOnPoi(List<Position> positions);
}
