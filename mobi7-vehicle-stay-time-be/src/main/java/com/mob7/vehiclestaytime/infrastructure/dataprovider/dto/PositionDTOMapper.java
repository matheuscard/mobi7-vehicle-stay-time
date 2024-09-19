package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;

import com.mob7.vehiclestaytime.domain.model.Position;

import java.util.ArrayList;
import java.util.List;

public class PositionDTOMapper {
    public List<Position> toDomainList(List<PositionResponse> positionsObj){
        List<Position> positions = new ArrayList<>();
        positionsObj.forEach(positionResponse -> {
             positions.add(new Position(positionResponse.getId(), positionResponse.getPlate(),positionResponse.getDate(), positionResponse.getVelocity(),positionResponse.getLatitude(),positionResponse.getLongitude(),positionResponse.isIgnition()));
        });
        return positions;
    }
    public List<PositionResponse> toResponseList(List<Position> positionsDomain){
        List<PositionResponse> positions = new ArrayList<>();
        positionsDomain.forEach(posDomain -> {
            positions.add(new PositionResponse(posDomain.id(),posDomain.plate(),posDomain.date(),posDomain.velocity(),posDomain.latitude(), posDomain.longitude(), posDomain.ignition()));
        });
        return positions;
    }
}
