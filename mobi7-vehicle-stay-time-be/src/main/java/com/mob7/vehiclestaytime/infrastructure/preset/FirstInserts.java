package com.mob7.vehiclestaytime.infrastructure.preset;

import com.mob7.vehiclestaytime.application.usecases.CreatePointInterestUseCase;
import com.mob7.vehiclestaytime.application.usecases.CreatePositionsUseCase;
import com.mob7.vehiclestaytime.application.usecases.GetPointInterestsWithPositionsUseCase;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.PointInterestClient;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.PositionClient;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PointInterestDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PointInterestResponse;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PositionDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestRepository;
import com.mob7.vehiclestaytime.infrastructure.persistence.PositionRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstInserts implements ApplicationRunner {
    private final CreatePointInterestUseCase createPointInterestUseCase;
    private final CreatePositionsUseCase createPositionsUseCase;
    private final GetPointInterestsWithPositionsUseCase getPointInterestsWithPositionsUseCase;

    private final Logger logger = LoggerFactory.getLogger(FirstInserts.class);
    private final PointInterestRepository pointInterestRepository;
    private final PositionRepository positionRepository;

    private final PointInterestClient pointInterestClient;
    private final PositionClient positionClient;
    private final PointInterestDTOMapper pointInterestDTOMapper;
    private final PositionDTOMapper positionDTOMapper;

    public FirstInserts(CreatePointInterestUseCase createPointInterestUseCase, CreatePositionsUseCase createPositionsUseCase, GetPointInterestsWithPositionsUseCase getPointInterestsWithPositionsUseCase, PointInterestRepository pointInterestRepository, PositionRepository positionRepository, PointInterestClient pointInterestClient, PositionClient positionClient, PointInterestDTOMapper pointInterestDTOMapper, PositionDTOMapper positionDTOMapper) {
        this.createPointInterestUseCase = createPointInterestUseCase;
        this.createPositionsUseCase = createPositionsUseCase;
        this.getPointInterestsWithPositionsUseCase = getPointInterestsWithPositionsUseCase;
        this.pointInterestRepository = pointInterestRepository;
        this.positionRepository = positionRepository;
        this.pointInterestClient = pointInterestClient;
        this.positionClient = positionClient;
        this.pointInterestDTOMapper = pointInterestDTOMapper;
        this.positionDTOMapper = positionDTOMapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            final List<PointInterestResponse> pointInterestResponses = pointInterestClient.getPoints();
            if (pointInterestRepository.count() < pointInterestResponses.size()) {
                pointInterestResponses.forEach(pointInterestResponse -> {
                    var poi = pointInterestDTOMapper.toDomain(pointInterestResponse);
                    createPointInterestUseCase.insertPointInterest(poi);
                });
            }
        } catch (FeignException.FeignClientException.ServiceUnavailable e) {
        }

    }
}
