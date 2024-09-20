package com.mob7.vehiclestaytime.infrastructure.gateways.impl;


import com.mob7.vehiclestaytime.application.gateways.CarStayTimeGateway;
import com.mob7.vehiclestaytime.domain.model.Car;
import com.mob7.vehiclestaytime.domain.model.Position;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PositionDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.persistence.PositionRepository;

import java.util.ArrayList;
import java.util.List;

public class CarStayTimeServiceGateway implements CarStayTimeGateway {
    private final PositionDTOMapper positionDTOMapper;
    private final PositionRepository positionRepository;
    public CarStayTimeServiceGateway(PositionDTOMapper positionDTOMapper, PositionRepository positionRepository) {
        this.positionDTOMapper = positionDTOMapper;
        this.positionRepository = positionRepository;
    }

    @Override
    public List<Car> getCarsWithStayTimeOnPoi(List<Position> positions) {
        List<Car> cars = new ArrayList<>();
        positions.forEach(position -> {
            cars.add(new Car(position.plate(),position.velocity(),position.ignition(),positions));

        });
        return null;
    }
}
