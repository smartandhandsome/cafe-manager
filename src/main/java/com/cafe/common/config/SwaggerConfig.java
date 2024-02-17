package com.cafe.common.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION = "Authorization";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info())
                .components(components());
    }

    private Info info() {
        return new Info()
                .version("v1.0.0")
                .title("카페 운영 어플리케이션")
                .description("백엔드 API")
                .contact(contact());
    }

    private Components components() {
        return new Components()
                .addSecuritySchemes(
                        AUTHORIZATION,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .in(SecurityScheme.In.HEADER)
                                .name(AUTHORIZATION)
                                .scheme("Bearer")
                                .bearerFormat("JWT")
                );
    }

    private Contact contact() {
        return new Contact()
                .name("박상민")
                .email("sangmins930@gmail.com");
    }

}

