package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;

import com.mob7.vehiclestaytime.domain.model.CarStayTime;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

public class CarStayTimeDTOMapper {
    @Autowired
    private PointInterestDTOMapper pointInterestDTOMapper;
    @Autowired
    private PositionDTOMapper positionDTOMapper;

    public List<CarStayTimeReponse> toResponseList(final List<CarStayTime> carsDomain) {
        List<CarStayTimeReponse> cars = new ArrayList<>();
        carsDomain.forEach(carDomain -> {
            cars.add(new CarStayTimeReponse(carDomain.plate(), carDomain.stayTime(), pointInterestDTOMapper.toResponse(carDomain.pointInterest()),positionDTOMapper.toResponse(carDomain.lastPosition())));
        });
        return cars;
    }
}
