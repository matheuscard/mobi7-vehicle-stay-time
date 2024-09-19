package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;

import com.mob7.vehiclestaytime.domain.model.Position;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PositionDTOMapper {
    public Position toDomain(final PositionResponse posObj){
        return new Position(posObj.getId(), posObj.getPlate(), LocalDateTime.parse(posObj.getDate()), posObj.getVelocity(), posObj.getLatitude(), posObj.getLongitude(), posObj.isIgnition(),null);
    }
    public List<Position> toDomainList(final List<PositionResponse> positionsObj){
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        List<Position> positions = new ArrayList<>();
        positionsObj.forEach(positionResponse -> {
            String date = positionResponse.getDate().split("\\.")[0];
            positions.add(new Position(positionResponse.getId(), positionResponse.getPlate(),LocalDateTime.parse(date, formatter), positionResponse.getVelocity(),positionResponse.getLatitude(),positionResponse.getLongitude(),positionResponse.isIgnition(),null));
        });
        return positions;
    }
    public List<PositionResponse> toResponseList(final List<Position> positionsDomain){
        List<PositionResponse> positions = new ArrayList<>();
        positionsDomain.forEach(posDomain -> {
            positions.add(new PositionResponse(posDomain.id(),posDomain.plate(),posDomain.date().toString(),posDomain.velocity(),posDomain.latitude(), posDomain.longitude(), posDomain.ignition(),posDomain.pointInterest()));
        });
        return positions;
    }
}
