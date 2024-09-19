package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PositionRequest {
    private String plate;
    private String date;
}
