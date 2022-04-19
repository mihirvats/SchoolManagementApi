package com.example.projectAssessmentCombined.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi swaggerCall(){
        String[] paths = new String[]{"/school/**","/schools/**","/users/**","/user/**"};
        return GroupedOpenApi.builder().group("Combined Route For Schools And Users").pathsToMatch(paths).build();
    }
}
