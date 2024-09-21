package com.mob7.vehiclestaytime.infrastructure.gateways.impl;

import com.mob7.vehiclestaytime.application.gateways.PointInterestGateway;
import com.mob7.vehiclestaytime.domain.model.PointInterest;
import com.mob7.vehiclestaytime.infrastructure.persistence.entities.PointInterestEntity;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PointInterestServiceGateway implements PointInterestGateway {
    @Autowired
    private  PointInterestRepository pointInterestRepository;
    @Autowired
    private PointInterestEntityMapper pointInterestEntityMapper;

    @Override
    public PointInterest insertPointInterest(final PointInterest poiDomainObj) {
        PointInterestEntity pointInterest = pointInterestEntityMapper.toEntity(poiDomainObj);
        PointInterestEntity savedObj = pointInterestRepository.save(pointInterest);
        return pointInterestEntityMapper.toDomain(savedObj);
    }

}
