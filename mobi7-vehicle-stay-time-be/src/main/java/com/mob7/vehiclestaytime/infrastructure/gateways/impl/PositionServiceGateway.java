package com.mob7.vehiclestaytime.infrastructure.gateways.impl;

import com.mob7.vehiclestaytime.application.gateways.PositionGateway;
import com.mob7.vehiclestaytime.domain.model.Position;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.PositionClient;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PositionDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PositionResponse;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestRepository;
import com.mob7.vehiclestaytime.infrastructure.persistence.entities.PositionEntity;
import com.mob7.vehiclestaytime.infrastructure.persistence.PositionRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import static com.mob7.vehiclestaytime.infrastructure.gateways.impl.utils.DateUtils.getLocalDateTime;

public class PositionServiceGateway implements PositionGateway {
    @Autowired
    private  PointInterestRepository pointInterestRepository;
    @Autowired
    private  PositionRepository positionRepository;
    @Autowired
    private  PositionEntityMapper positionEntityMapper;
    @Autowired
    private  PositionClient positionClient;
    @Autowired
    private  PositionDTOMapper positionDTOMapper;

    @Override
    public Position insertPosition(final Position positionDomainObj) {
        PositionEntity position = positionEntityMapper.toEntity(positionDomainObj);
        PositionEntity savedObj = positionRepository.save(position);
        return positionEntityMapper.toDomain(savedObj);
    }

    /**
     *
     * Find client positions, if client doesn't work, find on local base
     * @param plate
     * @param date
     * @return
     */
    @Override
    public List<Position> getPointInterestWithPositions(final String plate, final String date) {
        try {
            List<PositionResponse> res = positionClient.getPositions(plate, date);
            var positions = positionDTOMapper.toDomainList(res);
            positions.forEach(position -> {
                if (positionRepository.findById(position.id()).isEmpty()) {
                    findPointInterestAndMergeWithPosition(position);
                }
            });
            return getPositionsFiltered(plate, date);
        }catch (FeignException.FeignClientException.ServiceUnavailable e){
            return getPositionsFiltered(plate, date);
        }

    }

    private List<Position> getPositionsFiltered(final String plate, final String date) {
        List<PositionEntity> positionEntities = positionRepository.findFilteredPositions(plate, getLocalDateTime(date, true), getLocalDateTime(date, false));
        return positionEntityMapper.toDomainList(positionEntities);
    }

    private void findPointInterestAndMergeWithPosition(final Position position) {
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
