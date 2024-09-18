package com.mob7.vehiclestaytime.infrastructure.gateways.impl;

import com.mob7.vehiclestaytime.application.gateways.PointInterestGateway;
import com.mob7.vehiclestaytime.domain.model.PointInterest;
import com.mob7.vehiclestaytime.domain.model.Position;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestEntity;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<PointInterest> getPointInterestWithPositions(List<Position> positions) {
        Set<PointInterest> points = new HashSet<>();
        positions.forEach(position -> {
            var poi = pointInterestRepository.findPointInterest(position.latitude(),position.longitude());
            poi.ifPresent(pointInterest -> {
                pointInterest.positions().add(position);
                points.add(poi.get());
            });
        });
        return points.stream().toList();

    }
}
