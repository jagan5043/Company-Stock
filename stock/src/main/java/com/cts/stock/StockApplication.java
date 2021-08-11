package com.cts.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.cts.stock.entities.Stock;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2
//@EnableWebSecurity
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}
	
	@Bean
	public Stock getStock() {
		return new Stock();
	}

}
