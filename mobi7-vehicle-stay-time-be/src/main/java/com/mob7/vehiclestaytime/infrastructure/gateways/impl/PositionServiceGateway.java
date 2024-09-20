package com.mob7.vehiclestaytime.infrastructure.gateways.impl;

import com.mob7.vehiclestaytime.application.gateways.PositionGateway;
import com.mob7.vehiclestaytime.domain.model.Position;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.PositionClient;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PositionDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PositionResponse;
import com.mob7.vehiclestaytime.infrastructure.gateways.impl.utils.DateUtils;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestRepository;
import com.mob7.vehiclestaytime.infrastructure.persistence.entities.PositionEntity;
import com.mob7.vehiclestaytime.infrastructure.persistence.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class PositionServiceGateway implements PositionGateway {
    @Autowired
    private  PointInterestRepository pointInterestRepository;
    private  PositionRepository positionRepository;
    @Autowired
    private  PositionEntityMapper positionEntityMapper;
    @Autowired
    private  PositionClient positionClient;
    @Autowired
    private  PositionDTOMapper positionDTOMapper;

    @Override
    public Position insertPosition(Position positionDomainObj) {
        PositionEntity position = positionEntityMapper.toEntity(positionDomainObj);
        PositionEntity savedObj = positionRepository.save(position);
        return positionEntityMapper.toDomain(savedObj);
    }

    @Override
    public List<Position> getPointInterestWithPositions(String plate, String date) {
        List<PositionResponse> res = positionClient.getPositions(plate, date);
        var positions = positionDTOMapper.toDomainList(res);
        positions.forEach(position -> {
            if (positionRepository.findById(position.id()).isEmpty()) {
                findPointInterestAndMergeWithPosition(position);
            }
        });
        List<PositionEntity> positionEntities = positionRepository.findFilteredPositions(plate, DateUtils.getLocalDateTime(date, true),DateUtils.getLocalDateTime(date, false));
        return positionEntityMapper.toDomainList(positionEntities);
    }

    private void findPointInterestAndMergeWithPosition(Position position) {
        var poi = pointInterestRepository.findPointInterest(position.latitude(), position.longitude());
        poi.ifPresent(pointInterest -> poi.get().forEach(point -> {
            positionRepository.save(positionEntityMapper.toEntity(position));
            Optional<PositionEntity> positionSaved = positionRepository.findById(position.id());
            positionSaved.ifPresent(positionEntity -> {
                positionEntity.setPointInterest(point);
                positionRepository.save(positionEntity);
            });
        }));
    }


}
