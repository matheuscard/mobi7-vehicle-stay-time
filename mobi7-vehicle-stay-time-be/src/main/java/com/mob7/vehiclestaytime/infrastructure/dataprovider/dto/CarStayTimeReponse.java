package com.mob7.vehiclestaytime.infrastructure.dataprovider.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarStayTimeReponse {
    @JsonProperty("plate")
    private String plate;
    @JsonProperty(value = "timeStay")
    private String time;
    @JsonProperty("pointInterest")
    private PointInterestResponse pointInterestResponse;
}
