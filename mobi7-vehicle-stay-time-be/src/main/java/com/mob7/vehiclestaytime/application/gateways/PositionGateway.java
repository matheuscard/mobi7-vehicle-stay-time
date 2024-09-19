package com.mob7.vehiclestaytime.application.gateways;

import com.mob7.vehiclestaytime.domain.model.Position;

public interface PositionGateway {
    Position insertPosition(final Position positionDomainObj);
}
