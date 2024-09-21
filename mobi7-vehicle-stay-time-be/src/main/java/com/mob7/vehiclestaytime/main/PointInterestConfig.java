package com.mob7.vehiclestaytime.main;

import com.mob7.vehiclestaytime.application.gateways.CarStayTimeGateway;
import com.mob7.vehiclestaytime.application.gateways.PointInterestGateway;
import com.mob7.vehiclestaytime.application.gateways.PositionGateway;
import com.mob7.vehiclestaytime.application.usecases.CreatePointInterestUseCase;
import com.mob7.vehiclestaytime.application.usecases.CreatePositionsUseCase;
import com.mob7.vehiclestaytime.application.usecases.GetCarsWithStayTimeOnPOIUseCase;
import com.mob7.vehiclestaytime.application.usecases.GetPointInterestsWithPositionsUseCase;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.CarStayTimeDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PointInterestDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PositionDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.gateways.impl.*;
import com.mob7.vehiclestaytime.infrastructure.gateways.impl.PointInterestEntityMapper;
import com.mob7.vehiclestaytime.infrastructure.gateways.impl.PositionEntityMapper;
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
    CreatePositionsUseCase createPositionUseCase(PositionGateway positionGateway) {
        return new CreatePositionsUseCase(positionGateway);
    }
    @Bean
    GetPointInterestsWithPositionsUseCase getPointInterestsWithPositionsUseCase(PositionGateway positionGateway) {
        return new GetPointInterestsWithPositionsUseCase(positionGateway);
    }
    @Bean
    GetCarsWithStayTimeOnPOIUseCase getCarsWithStayTimeOnPOIUseCase(CarStayTimeGateway carStayTimeGateway) {
        return new GetCarsWithStayTimeOnPOIUseCase(carStayTimeGateway);
    }

    @Bean
    PointInterestGateway pointInterestGateway() {
        return new PointInterestServiceGateway();
    }
    @Bean
    PositionGateway positionGateway(){
        return new PositionServiceGateway();
    }
    @Bean
    CarStayTimeGateway carStayTimeGateway(){
        return new CarStayTimeServiceGateway();
    }
    @Bean
    PointInterestEntityMapper pointInterestEntityMapper() {
        return new PointInterestEntityMapper();
    }
    @Bean
    PointInterestDTOMapper pointInterestDTOMapper() {
        return new PointInterestDTOMapper();
    }
    @Bean
    PositionEntityMapper positionEntityMapper(PointInterestEntityMapper pointInterestEntityMapper) {
        return new PositionEntityMapper(pointInterestEntityMapper);
    }
    @Bean
    PositionDTOMapper positionDTOMapper(PointInterestDTOMapper pointInterestDTOMapper) {
        return new PositionDTOMapper(pointInterestDTOMapper);
    }
    @Bean
    CarStayTimeDTOMapper carStayTimeDTOMapper() {
        return new CarStayTimeDTOMapper();
    }
}
