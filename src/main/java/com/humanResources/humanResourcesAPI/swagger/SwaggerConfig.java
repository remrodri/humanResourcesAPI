package com.humanResources.humanResourcesAPI.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                        .title("Human Resource API")
                        .version("1.0")
                        .description("Human Resource API")
                                .contact(new Contact().name("Human Resource API"))
                                .license(new License().name("License of API").url("https://github.com/HumanResources/HumanResourcesAPI")
                                )
                );
    }
}
