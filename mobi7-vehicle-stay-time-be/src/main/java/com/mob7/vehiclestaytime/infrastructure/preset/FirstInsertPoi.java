package com.mob7.vehiclestaytime.infrastructure.preset;

import com.mob7.vehiclestaytime.application.usecases.CreatePointInterestUseCase;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.PointInterestClient;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PointInterestDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInsertPoi implements ApplicationRunner {
    private final CreatePointInterestUseCase createPointInterestUseCase;
    private final Logger logger = LoggerFactory.getLogger(FirstInsertPoi.class);
    private final PointInterestRepository pointInterestRepository;

    @Autowired
    private final PointInterestClient pointInterestClient;
    private final PointInterestDTOMapper pointInterestDTOMapper;

    public FirstInsertPoi(CreatePointInterestUseCase createPointInterestUseCase, PointInterestRepository pointInterestRepository, PointInterestClient pointInterestClient, PointInterestDTOMapper pointInterestDTOMapper) {
        this.createPointInterestUseCase = createPointInterestUseCase;
        this.pointInterestRepository = pointInterestRepository;
        this.pointInterestClient = pointInterestClient;
        this.pointInterestDTOMapper = pointInterestDTOMapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (pointInterestRepository.count() != 0) {
            return;
        }

        logger.info("Nenhum poi cadastrado.");
        pointInterestClient.getPoints().forEach(pointInterestResponse -> {
            var poi = pointInterestDTOMapper.toDomain(pointInterestResponse);
            createPointInterestUseCase.insertPointInterest(poi);
        });

    }
}
