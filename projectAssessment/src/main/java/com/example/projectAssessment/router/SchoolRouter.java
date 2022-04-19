package com.example.projectAssessment.router;


import com.example.projectAssessment.handler.SchoolHandler;
import com.example.projectAssessment.models.School;
import com.example.projectAssessment.service.SchoolService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@EnableWebFlux
public class SchoolRouter implements WebFluxConfigurer {
    @Bean
    public RouterFunction<ServerResponse> routerFunction2(SchoolHandler schoolHandler){
        return route()
                .GET("/schools" ,accept(MediaType.APPLICATION_JSON), schoolHandler::getAllSchools,
                        ops-> ops.beanClass(SchoolService.class)
                                .beanMethod("getAllSchools")).build()
                .and(route().POST("/schools" ,accept(MediaType.APPLICATION_JSON), schoolHandler::getSchoolById,
                                ops-> ops.beanClass(SchoolService.class)
                                .beanMethod("addSchool")).build().and
                        (route().GET("/schools/{schoolId}" ,accept(MediaType.APPLICATION_JSON), schoolHandler::getSchoolById, ops-> ops.beanClass(SchoolService.class)
                                        .beanMethod("getSchoolById")).build().and
                                (route().GET("/schools/{schoolId}/class/{classId}" ,accept(MediaType.TEXT_EVENT_STREAM), schoolHandler::getClassById, ops-> ops.beanClass(SchoolService.class)
                                                .beanMethod("getClassById"))
                                        .build().and(
                                                route().PUT("/schools/{schoolId}" ,accept(MediaType.APPLICATION_JSON), schoolHandler::updateSchool, ops-> ops.beanClass(SchoolService.class)
                                                        .beanMethod("updateSchool")).build()
                                        )
                                )));
    }

}
