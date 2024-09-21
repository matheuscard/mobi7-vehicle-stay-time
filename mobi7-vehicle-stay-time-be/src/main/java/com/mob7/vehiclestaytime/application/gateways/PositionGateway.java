package com.mob7.vehiclestaytime.application.gateways;

import com.mob7.vehiclestaytime.domain.model.Position;
import java.util.List;

public interface PositionGateway {
    Position insertPosition(final Position positionDomainObj);
    List<Position> getPointInterestWithPositions(final String plate, final String date);
}
