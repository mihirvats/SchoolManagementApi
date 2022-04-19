package com.example.projectAssessmentCombined.router;


import com.example.projectAssessmentCombined.handler.ParentHandler;
import com.example.projectAssessmentCombined.service.ParentService;
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
public class ParentRouter implements WebFluxConfigurer {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(ParentHandler parentHandler){

        return route().
                GET("/schools/get",
        accept(MediaType.APPLICATION_JSON),
        parentHandler::getSchools,
        ops -> ops.beanClass(ParentService.class)
                .beanMethod("getSchools")).build().and(

                route().GET("/users/get",
                        accept(MediaType.APPLICATION_JSON),
                        parentHandler::getUsers,
                        ops1-> ops1.beanClass(ParentService.class)
                                .beanMethod("getUsers")).build()
                        .and(
                                route().GET("/schools",
                                        accept(MediaType.APPLICATION_JSON),
                                        parentHandler::getSchoolWithUsers,
                                        ops2 -> ops2.beanClass(ParentService.class)
                                                .beanMethod("getSchoolWithUsers"))
                                        .build().and(
                                                route().GET("/user/{schoolId}/teachers",
                                                                accept(MediaType.APPLICATION_JSON),
                                                                parentHandler::getSchoolTeachers,
                                                                ops3 -> ops3.beanClass(ParentService.class)
                                                                        .beanMethod("getSchoolTeachers"))
                                                        .build().and(
                                                                route().GET("/user/{schoolId}/students",
                                                                                accept(MediaType.APPLICATION_JSON),
                                                                                parentHandler::getSchoolStudents,
                                                                                ops4 -> ops4.beanClass(ParentService.class)
                                                                                        .beanMethod("getSchoolStudents"))
                                                                        .build()
                                                                        .and(route().GET("/user/{userId}",
                                                                                        accept(MediaType.APPLICATION_JSON),
                                                                                        parentHandler::getUserWithSchool,
                                                                                        ops5 -> ops5.beanClass(ParentService.class)
                                                                                                .beanMethod("getUserWithSchool"))
                                                                                .build()
                                                                                .and(
                                                                                        route().GET("/school/{schoolId}",
                                                                                                        accept(MediaType.APPLICATION_JSON),
                                                                                                        parentHandler::getSchoolByIdInCombined,
                                                                                                        ops6 -> ops6.beanClass(ParentService.class)
                                                                                                                .beanMethod("getSchoolByIdInCombined"))
                                                                                                .build()
                                                                                                .and(
                                                                                                        route().GET("/schools/{schoolId}/class/{classId}",
                                                                                                                        accept(MediaType.APPLICATION_JSON),
                                                                                                                        parentHandler::classById,
                                                                                                                        ops7 -> ops7.beanClass(ParentService.class)
                                                                                                                                .beanMethod("classById"))
                                                                                                                .build().and(
                                                                                                                        route().GET("/users",
                                                                                                                                        accept(MediaType.APPLICATION_JSON),
                                                                                                                                        parentHandler::getUsersWithSchool,
                                                                                                                                        ops8 -> ops8.beanClass(ParentService.class)
                                                                                                                                                .beanMethod("getUsersWithSchool"))
                                                                                                                                .build()
                                                                                                                )
                                                                                                )
                                                                                ))))));
    }
}
