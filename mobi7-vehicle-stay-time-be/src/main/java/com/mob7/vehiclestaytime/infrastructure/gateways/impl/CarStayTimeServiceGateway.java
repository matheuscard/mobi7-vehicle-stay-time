package com.mob7.vehiclestaytime.infrastructure.gateways.impl;

import com.mob7.vehiclestaytime.application.gateways.CarStayTimeGateway;
import com.mob7.vehiclestaytime.domain.model.CarStayTime;
import com.mob7.vehiclestaytime.domain.model.PointInterest;
import com.mob7.vehiclestaytime.domain.model.Position;
import java.util.*;
import java.util.stream.Collectors;

import static com.mob7.vehiclestaytime.infrastructure.gateways.impl.utils.DateUtils.getStayTime;

public class CarStayTimeServiceGateway implements CarStayTimeGateway {
    @Override
    public List<CarStayTime> getCarsWithStayTimeOnPoi(final List<Position> positions) {
        Map<PointInterest, Map<String, List<Position>>> pointsPlatesMap =
                positions
                        .stream()
                        .collect(Collectors.groupingBy(Position::pointInterest,
                                Collectors.groupingBy(Position::plate)));
        List<CarStayTime> cars = new ArrayList<>();
        pointsPlatesMap.entrySet().stream().forEach(pointInterest -> {
            pointInterest.getValue().entrySet().stream().forEach(plates -> cars
                    .add(new CarStayTime(plates.getKey(), getStayTime(plates), pointInterest.getKey()))
            );
        });
        return cars;
    }


}
