package com.anish.journalApp.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomUI(){
        return new OpenAPI().info(
                new Info().title("Journal App APIs")
                        .description("by Anish Kumar made with ❤️")
                )
                .servers(Arrays.asList(
                        new Server().url("http://localhost:8080/").description("Local"),
                        new Server().url("https://stormy-plateau-60300-cf3202d715ca.herokuapp.com").description("Live")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList( "bearerAuth"))
                    .components(new Components().addSecuritySchemes(
                        "bearerAuth", new SecurityScheme()
                        .type (SecurityScheme. Type.HTTP)
                        .scheme ("bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization")
                ));
    }
}
