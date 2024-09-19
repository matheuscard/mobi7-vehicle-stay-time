package com.mob7.vehiclestaytime.infrastructure.gateways.impl;

import com.mob7.vehiclestaytime.application.gateways.PointInterestGateway;
import com.mob7.vehiclestaytime.domain.model.PointInterest;
import com.mob7.vehiclestaytime.domain.model.Position;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestEntity;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestRepository;

import java.util.*;

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
        List<PointInterest> points = new ArrayList<>();
        positions.forEach(position -> {
            var poi = pointInterestRepository.findPointInterest(position.latitude(),position.longitude());

            poi.ifPresent(pointInterest -> {
                PointInterest poiSaved = pointInterestEntityMapper.toDomain(poi.get());
                if(points.stream().anyMatch(pointInterest1 -> Objects.equals(pointInterest1.id(), poiSaved.id()))){
                    PointInterest result = points.stream().filter(pointInterest1 -> Objects.equals(pointInterest1.id(), poiSaved.id())).findFirst().get();
                    points.remove(result);
                    result.positions().add(position);
                    points.add(result);
                }else{
                    poiSaved.positions().add(position);
                    points.add(poiSaved);
                }
            });
        });
        return points.stream().toList();

    }
}
