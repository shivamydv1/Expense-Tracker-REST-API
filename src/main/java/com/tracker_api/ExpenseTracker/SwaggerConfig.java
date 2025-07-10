package com.tracker_api.ExpenseTracker;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Expense Tracker API")
                        .description("This is a REST API for tracking expenses.")
                        .version("v1.0.0"));
    }
}
