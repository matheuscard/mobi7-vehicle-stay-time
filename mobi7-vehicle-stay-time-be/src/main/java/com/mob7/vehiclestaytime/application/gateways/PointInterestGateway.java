package com.mob7.vehiclestaytime.application.gateways;

import com.mob7.vehiclestaytime.domain.model.PointInterest;
import com.mob7.vehiclestaytime.domain.model.Position;
import java.util.List;

public interface PointInterestGateway {
    PointInterest insertPointInterest(final PointInterest poiDomainObj);
    List<Position> getPointInterestWithPositions(final String plate, String date);
}
