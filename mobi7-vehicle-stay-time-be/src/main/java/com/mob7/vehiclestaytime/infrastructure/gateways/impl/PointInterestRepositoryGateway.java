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
            poi.ifPresent(pointInterest -> poi.get().forEach(point -> {
                if(points.stream().anyMatch(poiMatch -> Objects.equals(poiMatch.id(), point.getId()))){
                    var res = points.stream().filter(pointFilter -> Objects.equals(pointFilter.id(), point.getId())).findFirst().get();
                    points.remove(res);
                    res.positions().add(position);
                    points.add(res);
                }else{
                    PointInterest poiSaved = pointInterestEntityMapper.toDomain(point);
                    poiSaved.positions().add(position);
                    points.add(poiSaved);
                }
            }));
        });
        return points.stream().toList();
    }
}
