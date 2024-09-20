package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarReponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("placa")
    private String plate;
    @JsonProperty("velocidade")
    private Integer velocity;
    @JsonProperty("ignicao")
    private boolean ignition;
    private List<PositionResponse> positions;
}
