package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mob7.vehiclestaytime.domain.model.PointInterest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PositionResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("placa")
    private String plate;
    @JsonProperty("data")
    private String date;
    @JsonProperty("velocidade")
    private Integer velocity;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("ignicao")
    private boolean ignition;
    private PointInterest PointInterest;
}
