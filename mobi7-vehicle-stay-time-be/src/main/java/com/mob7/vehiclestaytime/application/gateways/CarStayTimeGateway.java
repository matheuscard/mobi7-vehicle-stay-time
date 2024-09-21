package com.mob7.vehiclestaytime.application.gateways;

import com.mob7.vehiclestaytime.domain.model.CarStayTime;
import com.mob7.vehiclestaytime.domain.model.Position;
import java.util.List;

public interface CarStayTimeGateway {
    List<CarStayTime>  getCarsWithStayTimeOnPoi(final List<Position> positions);
}
