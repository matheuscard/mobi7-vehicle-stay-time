package com.mob7.vehiclestaytime.application.usecases;

import com.mob7.vehiclestaytime.application.gateways.CarStayTimeGateway;
import com.mob7.vehiclestaytime.domain.model.CarStayTime;
import com.mob7.vehiclestaytime.domain.model.Position;
import java.util.List;

public class GetCarsWithStayTimeOnPOIUseCase {
    private CarStayTimeGateway carStayTimeGateway;

    public GetCarsWithStayTimeOnPOIUseCase(CarStayTimeGateway carStayTimeGateway) {
        this.carStayTimeGateway = carStayTimeGateway;
    }

    public List<CarStayTime> getCarsWithStayTimeOnPOI(final List<Position> positions){
        return carStayTimeGateway.getCarsWithStayTimeOnPoi(positions);
    }
    
}
