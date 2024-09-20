package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.mapper;

import com.mob7.vehiclestaytime.domain.model.PointInterest;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PointInterestResponse;

import java.util.ArrayList;
import java.util.List;

public class PointInterestDTOMapper {

    public PointInterestResponse toResponse(final PointInterest poiDomain){
        return new PointInterestResponse(poiDomain.id(), poiDomain.name(), poiDomain.radius(), poiDomain.latitude(), poiDomain.longitude(),new ArrayList<>());
    }
    public PointInterest toDomain(final PointInterestResponse poiObj){
        return new PointInterest(poiObj.getId(), poiObj.getName(), poiObj.getRadius(), poiObj.getLatitude(), poiObj.getLongitude(),new ArrayList<>());
    }
    public List<PointInterestResponse> toListPointInterestResponse(final List<PointInterest> pointInterestsDomain){
        List<PointInterestResponse> pointInterests = new ArrayList<>();
        pointInterestsDomain.forEach(poiDomain -> {
            pointInterests.add(new PointInterestResponse(poiDomain.id(), poiDomain.name(),poiDomain.radius(),poiDomain.latitude(),poiDomain.longitude(), new ArrayList<>()));
        });
        return pointInterests;
    }
}
