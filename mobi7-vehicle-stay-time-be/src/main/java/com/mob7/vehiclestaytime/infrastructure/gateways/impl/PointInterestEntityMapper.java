package com.mob7.vehiclestaytime.infrastructure.gateways.impl;

import com.mob7.vehiclestaytime.domain.model.PointInterest;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestEntity;

import java.util.ArrayList;

public class PointInterestEntityMapper {
    PointInterestEntity toEntity(PointInterest poiObj){
        return PointInterestEntity.builder()
                .id(poiObj.id())
                .name(poiObj.name())
                .radius(poiObj.radius())
                .latitude(poiObj.latitude())
                .longitude(poiObj.longitude()).build();
    }
    PointInterest toDomain(PointInterestEntity poiEntity){
        return new PointInterest(poiEntity.getId(), poiEntity.getName(), poiEntity.getRadius(), poiEntity.getLatitude(), poiEntity.getLongitude(),new ArrayList<>());
    }
}