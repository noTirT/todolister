package com.tom.todolister.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Configuration {

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**").packagesToScan("com.tom.todolister.controller")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenApi(){
        var info = new Info().title("Todo-List API").version("v1");
        return new OpenAPI().addServersItem(new Server().url("/")).info(info);
    }
}
