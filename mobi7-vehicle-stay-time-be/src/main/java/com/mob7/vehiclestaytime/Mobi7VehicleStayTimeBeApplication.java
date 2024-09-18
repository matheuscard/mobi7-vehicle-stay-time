package com.mob7.vehiclestaytime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Mobi7VehicleStayTimeBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mobi7VehicleStayTimeBeApplication.class, args);
	}

}
