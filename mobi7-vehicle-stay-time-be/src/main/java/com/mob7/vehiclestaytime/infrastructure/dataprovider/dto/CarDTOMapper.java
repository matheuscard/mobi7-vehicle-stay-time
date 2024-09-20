package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;

import com.mob7.vehiclestaytime.domain.model.Car;
import java.util.ArrayList;
import java.util.List;

public class CarDTOMapper {
    public List<CarReponse> toResponseList(final List<Car> carsDomain){
        List<CarReponse> cars = new ArrayList<>();
        carsDomain.forEach(carDomain -> {
            cars.add(new CarReponse(carDomain.id(),carDomain.plate(),carDomain.velocity(),carDomain.ignition(),carDomain.positions()));
        });
        return cars;
    }
}
