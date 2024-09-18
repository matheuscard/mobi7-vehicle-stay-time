package com.mob7.vehiclestaytime.main;

import com.mob7.vehiclestaytime.application.gateways.PointInterestGateway;
import com.mob7.vehiclestaytime.application.usecases.CreatePointInterestInteractor;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PointInterestDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.gateways.PointInterestEntityMapper;
import com.mob7.vehiclestaytime.infrastructure.gateways.PointInterestRepositoryGateway;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PoinInterestConfig {
    @Bean
    CreatePointInterestInteractor createPointInterestInteractor(PointInterestGateway pointInterestGateway) {
        return new CreatePointInterestInteractor(pointInterestGateway);
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
    PointInterestDTOMapper pointInterestDTOMapper() {
        return new PointInterestDTOMapper();
    }
//
//    @Bean
//    UserDTOMapper userDTOMapper() {
//        return new UserDTOMapper();
//    }
}
