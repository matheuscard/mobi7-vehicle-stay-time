package com.mob7.vehiclestaytime.infrastructure.gateways.impl;

import com.mob7.vehiclestaytime.domain.model.Position;
import com.mob7.vehiclestaytime.infrastructure.persistence.PositionEntity;

public class PositionEntityMapper {
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
        return new Position(positionEntity.getId(), positionEntity.getPlate(), positionEntity.getDate(), positionEntity.getVelocity(), positionEntity.getLatitude(), positionEntity.getLongitude(), positionEntity.isIgnition());
    }
}
