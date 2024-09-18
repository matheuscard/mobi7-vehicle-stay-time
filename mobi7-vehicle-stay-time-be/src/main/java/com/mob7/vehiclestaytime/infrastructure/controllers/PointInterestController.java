package com.mob7.vehiclestaytime.infrastructure.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Point Interest Vehicle Stay Time", description = "Point Interest Vehicle Stay Time. Contains all the operations for filter by pate and date stay time of vehicle on specifically point of interest ")
public class PointInterestController {
}
