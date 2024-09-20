package com.mob7.vehiclestaytime.application.usecases;

import com.mob7.vehiclestaytime.application.gateways.PositionGateway;
import com.mob7.vehiclestaytime.domain.model.Position;
import java.util.List;

public class GetPointInterestsWithPositionsUseCase {
    private PositionGateway positionGateway;

    public GetPointInterestsWithPositionsUseCase(PositionGateway positionGateway) {
        this.positionGateway = positionGateway;
    }

    public List<Position> getPointInterestsWithPositions(final String plate, final String date){
        return positionGateway.getPointInterestWithPositions(plate,date);
    }
    
}
