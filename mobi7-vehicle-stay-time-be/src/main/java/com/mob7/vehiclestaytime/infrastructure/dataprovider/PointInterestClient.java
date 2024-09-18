package com.mob7.vehiclestaytime.infrastructure.dataprovider;

import com.mob7.vehiclestaytime.infrastructure.dataprovider.dto.PointInterestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@Component
@FeignClient(name = "${mob7feign.name}", url = "${mob7feign.url}")
public interface PointInterestClient {
    @RequestMapping(method = RequestMethod.GET, path = "/pois")
    List<PointInterestResponse> getPoints();
}
