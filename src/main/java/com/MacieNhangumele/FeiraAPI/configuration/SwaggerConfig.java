package com.MacieNhangumele.FeiraAPI.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI feiraOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Feira API")
                .description("API para gestão de feiras online e presenciais")
                .version("1.0")
                .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}