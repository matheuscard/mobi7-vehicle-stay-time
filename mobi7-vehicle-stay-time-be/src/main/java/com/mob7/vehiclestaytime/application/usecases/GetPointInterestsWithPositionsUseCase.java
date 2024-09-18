package com.mob7.vehiclestaytime.application.usecases;

import com.mob7.vehiclestaytime.application.gateways.PointInterestGateway;
import com.mob7.vehiclestaytime.domain.model.PointInterest;
import com.mob7.vehiclestaytime.domain.model.Position;

import java.util.List;

public class GetPointInterestsWithPositionsUseCase {
    private PointInterestGateway pointInterestGateway;

    public GetPointInterestsWithPositionsUseCase(PointInterestGateway pointInterestGateway) {
        this.pointInterestGateway = pointInterestGateway;
    }

    public List<PointInterest> getPointInterestsWithPositions(List<Position> positions){
        return pointInterestGateway.getPointInterestWithPositions(positions);
    }
    
}
