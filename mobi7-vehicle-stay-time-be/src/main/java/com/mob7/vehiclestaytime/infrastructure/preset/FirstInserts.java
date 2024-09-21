package com.mob7.vehiclestaytime.infrastructure.preset;

import com.mob7.vehiclestaytime.application.usecases.CreatePointInterestUseCase;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.PointInterestClient;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PointInterestDTOMapper;
import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PointInterestResponse;
import com.mob7.vehiclestaytime.infrastructure.persistence.PointInterestRepository;
import feign.FeignException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FirstInserts implements ApplicationRunner {
    private final CreatePointInterestUseCase createPointInterestUseCase;
    private final PointInterestRepository pointInterestRepository;

    private final PointInterestClient pointInterestClient;
    private final PointInterestDTOMapper pointInterestDTOMapper;

    public FirstInserts(CreatePointInterestUseCase createPointInterestUseCase, PointInterestRepository pointInterestRepository, PointInterestClient pointInterestClient, PointInterestDTOMapper pointInterestDTOMapper) {
        this.createPointInterestUseCase = createPointInterestUseCase;
        this.pointInterestRepository = pointInterestRepository;
        this.pointInterestClient = pointInterestClient;
        this.pointInterestDTOMapper = pointInterestDTOMapper;
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
