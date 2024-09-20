package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.mapper;

import com.mob7.vehiclestaytime.domain.model.CarStayTime;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.CarStayTimeReponse;

import java.util.ArrayList;
import java.util.List;

public class CarStayTimeDTOMapper {
    private final PointInterestDTOMapper pointInterestDTOMapper;

    public CarStayTimeDTOMapper(PointInterestDTOMapper pointInterestDTOMapper) {
        this.pointInterestDTOMapper = pointInterestDTOMapper;
    }

    public List<CarStayTimeReponse> toResponseList(final List<CarStayTime> carsDomain) {
        List<CarStayTimeReponse> cars = new ArrayList<>();
        carsDomain.forEach(carDomain -> {
            cars.add(new CarStayTimeReponse(carDomain.plate(), carDomain.stayTime(), pointInterestDTOMapper.toResponse(carDomain.pointInterest())));
        });
        return cars;
    }
}
