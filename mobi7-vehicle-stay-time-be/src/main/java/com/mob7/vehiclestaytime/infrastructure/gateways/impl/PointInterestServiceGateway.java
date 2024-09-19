package com.mob7.vehiclestaytime.infrastructure.gateways.impl;

import com.mob7.vehiclestaytime.application.gateways.PointInterestGateway;
import com.mob7.vehiclestaytime.application.gateways.PositionGateway;
import com.mob7.vehiclestaytime.domain.model.PointInterest;
import com.mob7.vehiclestaytime.domain.model.Position;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestEntity;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestRepository;
import com.mob7.vehiclestaytime.infrastructure.persistence.PositionEntity;
import com.mob7.vehiclestaytime.infrastructure.persistence.PositionRepository;

import java.util.*;


public class PointInterestServiceGateway implements PointInterestGateway, PositionGateway {
    private final PointInterestRepository pointInterestRepository;
    private final PointInterestEntityMapper pointInterestEntityMapper;
    private final PositionRepository positionRepository;
    private final PositionEntityMapper positionEntityMapper;
    public PointInterestServiceGateway(final PointInterestRepository pointInterestRepository, final PointInterestEntityMapper pointInterestEntityMapper, PositionRepository positionRepository, PositionEntityMapper positionEntityMapper) {
        this.pointInterestRepository = pointInterestRepository;
        this.pointInterestEntityMapper = pointInterestEntityMapper;
        this.positionRepository = positionRepository;
        this.positionEntityMapper = positionEntityMapper;
    }

    @Override
    public PointInterest insertPointInterest(final PointInterest poiDomainObj) {
        PointInterestEntity pointInterest = pointInterestEntityMapper.toEntity(poiDomainObj);
        PointInterestEntity savedObj = pointInterestRepository.save(pointInterest);
        return pointInterestEntityMapper.toDomain(savedObj);
    }

    @Override
    public List<PointInterest> getPointInterestWithPositions(final List<Position> positions) {
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

    @Override
    public Position insertPosition(final Position positionDomainObj) {
        PositionEntity position = positionEntityMapper.toEntity(positionDomainObj);
        PositionEntity savedObj = positionRepository.save(position);
        return positionEntityMapper.toDomain(savedObj);
    }
}
