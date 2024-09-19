package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class PointInterestResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("raio")
    private Integer radius;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    private List<PositionResponse> positions;
}
