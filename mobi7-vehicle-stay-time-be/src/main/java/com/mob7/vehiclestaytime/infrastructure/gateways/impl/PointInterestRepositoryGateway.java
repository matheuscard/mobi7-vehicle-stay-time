package com.mob7.vehiclestaytime.infrastructure.gateways.impl;

import com.mob7.vehiclestaytime.application.gateways.PointInterestGateway;
import com.mob7.vehiclestaytime.domain.model.PointInterest;
import com.mob7.vehiclestaytime.domain.model.Position;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestEntity;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestRepository;

import java.util.List;

public class PointInterestRepositoryGateway implements PointInterestGateway {
    private final PointInterestRepository pointInterestRepository;
    private final PointInterestEntityMapper pointInterestEntityMapper;
    public PointInterestRepositoryGateway(PointInterestRepository pointInterestRepository, PointInterestEntityMapper pointInterestEntityMapper) {
        this.pointInterestRepository = pointInterestRepository;
        this.pointInterestEntityMapper = pointInterestEntityMapper;
    }

    @Override
    public PointInterest insertPointInterest(PointInterest poiDomainObj) {
        PointInterestEntity pointInterest = pointInterestEntityMapper.toEntity(poiDomainObj);
        PointInterestEntity savedObj = pointInterestRepository.save(pointInterest);
        return pointInterestEntityMapper.toDomain(savedObj);
    }

    @Override
    public List<PointInterest> getPointInterestWithPositions(List<Position> positionDomainObj) {
        return null;
    }
}
