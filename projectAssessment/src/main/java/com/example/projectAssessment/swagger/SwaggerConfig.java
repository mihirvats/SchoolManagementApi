package com.example.projectAssessment.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi swaggerCall(){
        String[] paths = new String[]{"/schools/**"};
        return GroupedOpenApi.builder().group("School Routes").pathsToMatch(paths).build();
    }
}
