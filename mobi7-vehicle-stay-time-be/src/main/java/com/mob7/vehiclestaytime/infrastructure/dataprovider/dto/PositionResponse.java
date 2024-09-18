package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PositionResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("placa")
    private String plate;
    @JsonProperty("data")
    private LocalDateTime date;
    @JsonProperty("velocidade")
    private Integer velocity;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
}
