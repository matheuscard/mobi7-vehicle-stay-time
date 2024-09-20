package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;

import com.mob7.vehiclestaytime.domain.model.Position;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PositionDTOMapper {
    private final PointInterestDTOMapper pointInterestDTOMapper;

    public PositionDTOMapper(PointInterestDTOMapper pointInterestDTOMapper) {
        this.pointInterestDTOMapper = pointInterestDTOMapper;
    }

    public List<Position> toDomainList(final List<PositionResponse> positionsObj){
        List<Position> positions = new ArrayList<>();
        positionsObj.forEach(positionResponse -> {
            positions.add(new Position(positionResponse.getId(), positionResponse.getPlate(),getDateFomatted(positionResponse), positionResponse.getVelocity(),positionResponse.getLatitude(),positionResponse.getLongitude(),positionResponse.isIgnition(),null));
        });
        return positions;
    }

    private LocalDateTime getDateFomatted(PositionResponse positionResponse) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String date = positionResponse.getDate().split("\\.")[0];
        return LocalDateTime.parse(date, formatter);
    }

    public List<PositionResponse> toResponseList(final List<Position> positionsDomain){
        List<PositionResponse> positions = new ArrayList<>();
        positionsDomain.forEach(posDomain -> {
            positions.add(new PositionResponse(posDomain.id(),posDomain.plate(),posDomain.date().toString(),posDomain.velocity(),posDomain.latitude(), posDomain.longitude(), posDomain.ignition(),pointInterestDTOMapper.toResponse(posDomain.pointInterest())));
        });
        return positions;
    }
}
