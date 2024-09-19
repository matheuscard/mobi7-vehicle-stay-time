package com.mob7.vehiclestaytime.application.usecases;

import com.mob7.vehiclestaytime.application.gateways.PointInterestGateway;
import com.mob7.vehiclestaytime.domain.model.PointInterest;

public class CreatePointInterestUseCase {
    private PointInterestGateway pointInterestGateway;

    public CreatePointInterestUseCase(PointInterestGateway pointInterestGateway) {
        this.pointInterestGateway = pointInterestGateway;
    }

    public PointInterest insertPointInterest(PointInterest pointInterest){
        return pointInterestGateway.insertPointInterest(pointInterest);
    }

}