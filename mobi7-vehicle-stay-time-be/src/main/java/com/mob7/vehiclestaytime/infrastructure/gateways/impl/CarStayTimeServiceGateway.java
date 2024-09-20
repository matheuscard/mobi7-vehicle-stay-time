package com.mob7.vehiclestaytime.infrastructure.gateways.impl;


import com.mob7.vehiclestaytime.application.gateways.CarStayTimeGateway;
import com.mob7.vehiclestaytime.domain.model.CarStayTime;
import com.mob7.vehiclestaytime.domain.model.PointInterest;
import com.mob7.vehiclestaytime.domain.model.Position;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class CarStayTimeServiceGateway implements CarStayTimeGateway {

    @Override
    public List<CarStayTime> getCarsWithStayTimeOnPoi(final List<Position> positions) {
        Map<PointInterest, Map<String, List<Position>>> pointsPlatesMap =
                positions.stream().collect(Collectors.groupingBy(Position::pointInterest, Collectors.groupingBy(Position::plate)));
        List<CarStayTime> carros= new ArrayList<>();
        pointsPlatesMap.entrySet().stream().forEach(pointInterest -> {
            pointInterest.getValue().entrySet().stream().forEach(plates ->
                {
                    LocalDateTime firstDate = plates.getValue().get(0).date();
                    LocalDateTime lastDate = plates.getValue().get(plates.getValue().size()-1).date();
                    long stayDays = ChronoUnit.DAYS.between(firstDate, lastDate);
                    long stayHours = ChronoUnit.HOURS.between(firstDate, lastDate);
                    long stayMinutes = ChronoUnit.MINUTES.between(firstDate, lastDate);
                    CarStayTime carStayTime = new CarStayTime(plates.getKey(),"Em dias:"+stayDays+". Em horas: "+stayHours+ ". Em minutos: "+stayMinutes,pointInterest.getKey());
                    carros.add(carStayTime);
                }
            );
        });
        return carros;
    }
}
