package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarStayTimeReponse {
    @JsonProperty("placa")
    private String plate;
    @JsonProperty(value = "qtd_tempo")
    private String time;
    @JsonProperty("ponto_interesse")
    private PointInterestResponse pointInterestResponse;
}
