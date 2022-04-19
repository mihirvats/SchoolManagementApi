package com.example.projectAssessmentUserService.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi swaggerCall(){
        String[] paths = new String[]{"/users/**"};
        return GroupedOpenApi.builder().group("User Routes").pathsToMatch(paths).build();
    }
}
