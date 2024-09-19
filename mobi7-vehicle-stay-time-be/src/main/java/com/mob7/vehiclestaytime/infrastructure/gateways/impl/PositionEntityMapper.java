package com.mob7.vehiclestaytime.infrastructure.gateways.impl;

import com.mob7.vehiclestaytime.domain.model.Position;
import com.mob7.vehiclestaytime.infrastructure.persistence.PositionEntity;

import java.util.ArrayList;
import java.util.List;

public class PositionEntityMapper {
    private final PointInterestEntityMapper pointInterestEntityMapper;

    public PositionEntityMapper(PointInterestEntityMapper pointInterestEntityMapper) {
        this.pointInterestEntityMapper = pointInterestEntityMapper;
    }

    PositionEntity toEntity(final Position posObj){
        return PositionEntity.builder()
                .id(posObj.id())
                .plate(posObj.plate())
                .date(posObj.date())
                .velocity(posObj.velocity())
                .latitude(posObj.latitude())
                .longitude(posObj.longitude())
                .ignition(posObj.ignition()).build();
    }
    Position toDomain(final PositionEntity positionEntity){
        return new Position(positionEntity.getId(), positionEntity.getPlate(), positionEntity.getDate(), positionEntity.getVelocity(), positionEntity.getLatitude(), positionEntity.getLongitude(), positionEntity.isIgnition(),pointInterestEntityMapper.toDomain(positionEntity.getPointInterest()));
    }
    List<Position> toDomainList(final List<PositionEntity> positionEntities){
        List<Position> positions = new ArrayList<>();
        positionEntities.forEach(positionEntity -> {
            positions.add(new Position(positionEntity.getId(),positionEntity.getPlate(),positionEntity.getDate(),positionEntity.getVelocity(),positionEntity.getLatitude(),positionEntity.getLongitude(), positionEntity.isIgnition(),positionEntity.getPointInterest()!=null? pointInterestEntityMapper.toDomain(positionEntity.getPointInterest()):null));
        });
        return positions;
    }
}
