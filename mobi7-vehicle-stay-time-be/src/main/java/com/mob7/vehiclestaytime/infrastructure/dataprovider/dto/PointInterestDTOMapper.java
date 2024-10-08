package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;

import com.mob7.vehiclestaytime.domain.model.PointInterest;
import java.util.ArrayList;

public class PointInterestDTOMapper {

    public PointInterestResponse toResponse(final PointInterest poiDomain){
        return new PointInterestResponse(poiDomain.id(), poiDomain.name(), poiDomain.radius(), poiDomain.latitude(), poiDomain.longitude(),new ArrayList<>());
    }
    public PointInterest toDomain(final PointInterestResponse poiObj){
        return new PointInterest(poiObj.getId(), poiObj.getName(), poiObj.getRadius(), poiObj.getLatitude(), poiObj.getLongitude(),new ArrayList<>());
    }
}
