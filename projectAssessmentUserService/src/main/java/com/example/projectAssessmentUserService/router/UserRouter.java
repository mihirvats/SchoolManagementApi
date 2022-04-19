package com.example.projectAssessmentUserService.router;

import com.example.projectAssessmentUserService.handler.UserHandler;
import com.example.projectAssessmentUserService.service.UserService;
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
public class UserRouter implements WebFluxConfigurer {
    
    @Bean
    public RouterFunction<ServerResponse> routerFunction1(UserHandler userHandler){
        return route()
                .GET("/users",
                accept(MediaType.APPLICATION_JSON),
                        userHandler::handleAllUsers,
                        ops->  ops.beanClass(UserService.class)
                                .beanMethod("getAllUsers")
                        ).build()
                .and(route().POST("/users",
                        accept(MediaType.APPLICATION_JSON),
                        userHandler::saveUser,
                        ops-> ops.beanClass(UserService.class).beanMethod("saveUser")).build()
                        .and(route().GET("/users/{userId}",
                                accept(MediaType.APPLICATION_JSON),
                                userHandler::getUserById,
                                ops->  ops.beanClass(UserService.class)
                                        .beanMethod("getUserById")
                        ).build()
                                .and(route().PUT("/users/update/{id}"
                                                ,accept(MediaType.APPLICATION_JSON)
                                                , userHandler::updateUser,
                                                ops-> ops.beanClass(UserService.class)
                                                .beanMethod("updateStudent"))
                                        .build())));

    }
}
