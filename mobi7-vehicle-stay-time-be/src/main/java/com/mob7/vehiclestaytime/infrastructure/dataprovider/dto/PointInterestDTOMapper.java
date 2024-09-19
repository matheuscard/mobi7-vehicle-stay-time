package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;

import com.mob7.vehiclestaytime.domain.model.PointInterest;

import java.util.ArrayList;
import java.util.List;

public class PointInterestDTOMapper {
    private final PositionDTOMapper positionDTOMapper;

    public PointInterestDTOMapper(PositionDTOMapper positionDTOMapper) {
        this.positionDTOMapper = positionDTOMapper;
    }

    public PointInterest toDomain(PointInterestResponse poiObj){
        return new PointInterest(poiObj.getId(), poiObj.getName(), poiObj.getRadius(), poiObj.getLatitude(), poiObj.getLongitude(),new ArrayList<>());
    }
    public List<PointInterestResponse> toListPointInterestResponse(List<PointInterest> pointInterestsDomain){
        List<PointInterestResponse> pointInterests = new ArrayList<>();
        pointInterestsDomain.forEach(poiDomain -> {
            pointInterests.add(new PointInterestResponse(poiDomain.id(), poiDomain.name(),poiDomain.radius(),poiDomain.latitude(),poiDomain.longitude(),positionDTOMapper.toResponseList(poiDomain.positions())));
        });
        return pointInterests;
    }
}
