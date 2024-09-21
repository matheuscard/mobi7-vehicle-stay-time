package com.mob7.vehiclestaytime.infrastructure.dataprovider;

import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PositionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "${mob7feign.name-pos}", url = "${mob7feign.url}")
public interface PositionClient {
    @RequestMapping(method = RequestMethod.GET, path = "/posicao")
    List<PositionResponse> getPositions(@RequestParam(value="placa") String plate, @RequestParam(value = "data") String date);
    @RequestMapping(method = RequestMethod.GET, path = "/posicao/placas")
    List<String> getCars();
}
