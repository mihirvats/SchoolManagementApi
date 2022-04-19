package com.example.projectAssessment.handler;

import com.example.projectAssessment.models.Classes;
import com.example.projectAssessment.models.School;
import com.example.projectAssessment.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class SchoolHandler {

    @Autowired
    SchoolService schoolService;


    public Mono<ServerResponse> getAllSchools(ServerRequest request) {
        Flux<School> school1= schoolService.getAllSchools();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(school1, School.class);
    }

    public Mono<ServerResponse> addSchool(ServerRequest request) {
        Mono<School> school1 = request.bodyToMono(School.class);
        Mono<School> school2 = schoolService.addSchool(school1);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(school2, School.class);

    }

    public Mono<ServerResponse> getSchoolById(ServerRequest request) {
        int pathVariable = Integer.parseInt(request.pathVariable("schoolId"));
        Mono<School> school1 = schoolService.getSchoolById(pathVariable);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(school1, School.class);

    }

    public Mono<ServerResponse> getClassById(ServerRequest request) {
        int schoolId = Integer.parseInt(request.pathVariable("schoolId"));
        int classId = Integer.parseInt(request.pathVariable("classId"));
        Mono<Classes>class1= schoolService.getClassById(schoolId,classId);
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(class1, Classes.class);
    }

    public Mono<ServerResponse> updateSchool(ServerRequest request){
        Mono<School> schoolToUpdate = request.bodyToMono(School.class);
        int pathVariable = Integer.parseInt(request.pathVariable("schoolId"));
        Mono<School> user1= schoolService.updateSchool(pathVariable, schoolToUpdate);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(user1,School.class);
    }
}
