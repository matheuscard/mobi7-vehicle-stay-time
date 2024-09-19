package com.mob7.vehiclestaytime.main;

import com.mob7.vehiclestaytime.application.gateways.PointInterestGateway;
import com.mob7.vehiclestaytime.application.usecases.CreatePointInterestUseCase;
import com.mob7.vehiclestaytime.application.usecases.GetPointInterestsWithPositionsUseCase;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PointInterestDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PositionDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.gateways.impl.PointInterestEntityMapper;
import com.mob7.vehiclestaytime.infrastructure.gateways.impl.PointInterestRepositoryGateway;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PointInterestConfig {
    @Bean
    CreatePointInterestUseCase createPointInterestUseCase(PointInterestGateway pointInterestGateway) {
        return new CreatePointInterestUseCase(pointInterestGateway);
    }
    @Bean
    GetPointInterestsWithPositionsUseCase getPointInterestsWithPositionsUseCase(PointInterestGateway pointInterestGateway) {
        return new GetPointInterestsWithPositionsUseCase(pointInterestGateway);
    }

    @Bean
    PointInterestGateway pointInterestGateway(PointInterestRepository pointInterestRepository, PointInterestEntityMapper pointInterestEntityMapper) {
        return new PointInterestRepositoryGateway(pointInterestRepository, pointInterestEntityMapper);
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
    PositionDTOMapper positionDTOMapper() {
        return new PositionDTOMapper();
    }
//
//    @Bean
//    UserDTOMapper userDTOMapper() {
//        return new UserDTOMapper();
//    }
}
