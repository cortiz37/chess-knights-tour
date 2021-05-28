package com.server.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = new OpenAPI()
            .info(apiInfo());
        return openAPI;
    }

    private Info apiInfo() {
        return new Info()
            .title("Chess - Knight's tour")
            .version("1.0");
    }
}
