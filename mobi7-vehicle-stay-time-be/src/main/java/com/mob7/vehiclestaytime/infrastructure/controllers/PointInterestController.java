package com.mob7.vehiclestaytime.infrastructure.controllers;

import com.mob7.vehiclestaytime.application.usecases.GetCarsWithStayTimeOnPOIUseCase;
import com.mob7.vehiclestaytime.application.usecases.GetPointInterestsWithPositionsUseCase;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.*;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.mapper.CarStayTimeDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.mapper.PositionDTOMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Point Interest Vehicle Stay Time", description = "Point Interest Vehicle Stay Time. Contains all the operations for filter by pate and date stay time of vehicle on specifically point of interest ")
public class PointInterestController {
    private final GetPointInterestsWithPositionsUseCase getPointInterestsWithPositionsUseCase;
    private final GetCarsWithStayTimeOnPOIUseCase getCarsWithStayTimeOnPOIUseCase;
    private final PositionDTOMapper positionDTOMapper;
    private  final CarStayTimeDTOMapper carStayTimeDTOMapper;

    public PointInterestController(GetPointInterestsWithPositionsUseCase getPointInterestsWithPositionsUseCase, GetCarsWithStayTimeOnPOIUseCase getCarsWithStayTimeOnPOIUseCase, PositionDTOMapper positionDTOMapper, CarStayTimeDTOMapper carStayTimeDTOMapper) {
        this.getPointInterestsWithPositionsUseCase = getPointInterestsWithPositionsUseCase;
        this.getCarsWithStayTimeOnPOIUseCase = getCarsWithStayTimeOnPOIUseCase;
        this.positionDTOMapper = positionDTOMapper;
        this.carStayTimeDTOMapper = carStayTimeDTOMapper;
    }

    @GetMapping
    List<PositionResponse> get(PositionRequest positionRequest){
        var res= getPointInterestsWithPositionsUseCase.getPointInterestsWithPositions(positionRequest.getPlate(),positionRequest.getDate());
        return positionDTOMapper.toResponseList(res);
    }
    @GetMapping(path = "/cars")
    List<CarStayTimeReponse> getCarStayTime(PositionRequest positionRequest){
        var res = getPointInterestsWithPositionsUseCase.getPointInterestsWithPositions(positionRequest.getPlate(),positionRequest.getDate());
        return carStayTimeDTOMapper.toResponseList(getCarsWithStayTimeOnPOIUseCase.getCarsWithStayTimeOnPOI(res));
    }
}
