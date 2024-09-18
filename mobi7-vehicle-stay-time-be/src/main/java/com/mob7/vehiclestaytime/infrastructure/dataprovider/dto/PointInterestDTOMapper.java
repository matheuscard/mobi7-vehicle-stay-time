package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;

import com.mob7.vehiclestaytime.domain.model.PointInterest;

import java.util.ArrayList;

public class PointInterestDTOMapper {
    public PointInterest toDomain(PointInterestResponse poiObj){
        return new PointInterest(poiObj.getId(), poiObj.getName(), poiObj.getRadius(), poiObj.getLatitude(), poiObj.getLongitude(),new ArrayList<>());
    }
}
