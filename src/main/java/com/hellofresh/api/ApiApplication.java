package com.hellofresh.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.hellofresh.api.config.RouteConfig;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.hellofresh.api"
})
@EnableConfigurationProperties({
		RouteConfig.class,
})
public class ApiApplication {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		SpringApplication.run(ApiApplication.class, args);
		long timeElapsed = System.currentTimeMillis() - startTime;

		System.out.println("Hello fresh service has started in " + timeElapsed + " ms");
	}

}


// TODO: find a recipe with wines and marketplace items