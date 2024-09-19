package com.mob7.vehiclestaytime.application.usecases;

import com.mob7.vehiclestaytime.application.gateways.PositionGateway;
import com.mob7.vehiclestaytime.domain.model.Position;

public class CreatePositionsUseCase {
    private PositionGateway positionGateway;

    public CreatePositionsUseCase(final PositionGateway positionGateway) {
        this.positionGateway = positionGateway;
    }
    public Position insertPointInterest(final Position position){
        return positionGateway.insertPosition(position);
    }

}
