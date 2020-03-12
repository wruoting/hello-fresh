package com.hellofresh.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The SemaphoreServiceConfiguration class encapsulates the values set in the
 * application.properties associated with the Semaphore Service.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "services.routes")
public class RouteConfig {

    private String helloFreshEndpoint;

}
