package com.mob7.vehiclestaytime.main;

import com.mob7.vehiclestaytime.application.gateways.PointInterestGateway;
import com.mob7.vehiclestaytime.application.gateways.PositionGateway;
import com.mob7.vehiclestaytime.application.usecases.CreatePointInterestUseCase;
import com.mob7.vehiclestaytime.application.usecases.CreatePositionsUseCase;
import com.mob7.vehiclestaytime.application.usecases.GetPointInterestsWithPositionsUseCase;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.PositionClient;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PointInterestDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PositionDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.gateways.impl.PointInterestEntityMapper;
import com.mob7.vehiclestaytime.infrastructure.gateways.impl.PointInterestServiceGateway;
import com.mob7.vehiclestaytime.infrastructure.gateways.impl.PositionEntityMapper;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestRepository;
import com.mob7.vehiclestaytime.infrastructure.persistence.PositionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PointInterestConfig {
    @Bean
    CreatePointInterestUseCase createPointInterestUseCase(PointInterestGateway pointInterestGateway) {
        return new CreatePointInterestUseCase(pointInterestGateway);
    }
    @Bean
    CreatePositionsUseCase createPositionUseCase(PositionGateway positionGateway) {
        return new CreatePositionsUseCase(positionGateway);
    }
    @Bean
    GetPointInterestsWithPositionsUseCase getPointInterestsWithPositionsUseCase(PointInterestGateway pointInterestGateway) {
        return new GetPointInterestsWithPositionsUseCase(pointInterestGateway);
    }

    @Bean
    PointInterestGateway pointInterestGateway(PointInterestRepository pointInterestRepository, PointInterestEntityMapper pointInterestEntityMapper, PositionRepository positionRepository, PositionEntityMapper positionEntityMapper, PositionClient positionClient, PositionDTOMapper positionDTOMapper) {
        return new PointInterestServiceGateway(pointInterestRepository, pointInterestEntityMapper, positionRepository, positionEntityMapper, positionClient, positionDTOMapper);
    }


    @Bean
    PointInterestEntityMapper pointInterestEntityMapper() {
        return new PointInterestEntityMapper();
    }
    @Bean
    PointInterestDTOMapper pointInterestDTOMapper(PositionDTOMapper positionDTOMapper) {
        return new PointInterestDTOMapper(positionDTOMapper);
    }
    @Bean
    PositionEntityMapper positionEntityMapper(PointInterestEntityMapper pointInterestEntityMapper) {
        return new PositionEntityMapper(pointInterestEntityMapper);
    }
    @Bean
    PositionDTOMapper positionDTOMapper() {
        return new PositionDTOMapper();
    }
//
//    @Bean
//    UserDTOMapper userDTOMapper() {
//        return new UserDTOMapper();
//    }
}
