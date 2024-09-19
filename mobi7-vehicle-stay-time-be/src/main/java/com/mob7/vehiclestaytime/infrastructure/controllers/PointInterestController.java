package com.mob7.vehiclestaytime.infrastructure.controllers;

import com.mob7.vehiclestaytime.application.usecases.GetPointInterestsWithPositionsUseCase;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.PositionClient;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.*;
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
    private final PositionClient positionClient;
    private final PositionDTOMapper positionDTOMapper;
    private final PointInterestDTOMapper pointInterestDTOMapper;
    public PointInterestController(GetPointInterestsWithPositionsUseCase getPointInterestsWithPositionsUseCase, PositionClient positionClient, PositionDTOMapper positionDTOMapper, PointInterestDTOMapper pointInterestDTOMapper) {
        this.getPointInterestsWithPositionsUseCase = getPointInterestsWithPositionsUseCase;
        this.positionClient = positionClient;
        this.positionDTOMapper = positionDTOMapper;
        this.pointInterestDTOMapper = pointInterestDTOMapper;
    }

    @GetMapping
    List<PointInterestResponse> get(PositionRequest positionRequest){
        List<PositionResponse> positions = positionClient.getPositions(positionRequest.getPlate(),positionRequest.getDate());
        var res= getPointInterestsWithPositionsUseCase.getPointInterestsWithPositions(positionDTOMapper.toDomainList(positions));
        return pointInterestDTOMapper.toListPointInterestResponse(res);
    }
}
