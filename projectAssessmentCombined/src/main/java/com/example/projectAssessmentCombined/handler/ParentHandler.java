package com.example.projectAssessmentCombined.handler;

import com.example.projectAssessmentCombined.models.*;
import com.example.projectAssessmentCombined.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ParentHandler {

    @Autowired
    ParentService parentService;

    public Mono<ServerResponse> getSchools(ServerRequest request) {
        Flux<SchoolResponse> schoolResponseFlux = parentService.getSchools();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(schoolResponseFlux, SchoolResponse.class);
    }

    public Mono<ServerResponse> getUsers(ServerRequest request) {
        Flux<UserResponse> userResponseFlux = parentService.getUsers();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userResponseFlux, UserResponse.class);
    }

    public Mono<ServerResponse> getSchoolWithUsers(ServerRequest request) {
        Mono<SchoolWithUsers> schoolWithUsersFlux = parentService.getSchoolWithUsers();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(schoolWithUsersFlux, SchoolWithUsers.class);
    }

    public Mono<ServerResponse> getUsersWithSchool(ServerRequest request) {
        Mono<UsersWithSchool> schoolWithUsersFlux = parentService.getUsersWithSchool();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(schoolWithUsersFlux, SchoolWithUsers.class);
    }

    public Mono<ServerResponse> getSchoolTeachers(ServerRequest request) {
        int pathVariable = Integer.parseInt(request.pathVariable("schoolId"));
        Mono<UsersWithSchool> schoolWithUsersMono = parentService.getSchoolTeachers(pathVariable);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(schoolWithUsersMono, SchoolWithUsers.class);
    }

    public Mono<ServerResponse> getSchoolStudents(ServerRequest request) {
        int pathVariable = Integer.parseInt(request.pathVariable("schoolId"));
        Mono<UsersWithSchool> schoolWithUsersMono = parentService.getSchoolStudents(pathVariable);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(schoolWithUsersMono, SchoolWithUsers.class);
    }

    public Mono<ServerResponse> getUserWithSchool(ServerRequest request) {
        int pathVariable = Integer.parseInt(request.pathVariable("userId"));
        Mono<SchoolWithUsers> schoolWithUsersMono = parentService.getUserWithSchool(pathVariable);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(schoolWithUsersMono, SchoolWithUsers.class);
    }

    public Mono<ServerResponse> getSchoolByIdInCombined(ServerRequest serverRequest) {
        int pathVariable = Integer.parseInt(serverRequest.pathVariable("schoolId"));
        Mono<SchoolWithUsers> school1 = parentService.getSchoolByIdInCombined(pathVariable);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(school1, SchoolResponse.class);
    }

    public Mono<ServerResponse> classById(ServerRequest serverRequest) {
        int pathVariableSchool = Integer.parseInt(serverRequest.pathVariable("schoolId"));
        int pathVariableClass = Integer.parseInt(serverRequest.pathVariable("classId"));
        Flux<ClassesResponse> school1 = parentService.classById(pathVariableSchool, pathVariableClass);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(school1, SchoolResponse.class);
    }


}