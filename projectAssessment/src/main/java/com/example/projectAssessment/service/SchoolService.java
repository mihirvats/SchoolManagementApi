package com.example.projectAssessment.service;

import com.example.projectAssessment.models.Classes;
import com.example.projectAssessment.models.School;
import com.example.projectAssessment.repo.ClassRepository;
import com.example.projectAssessment.repo.SchoolRepository;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class SchoolService {

    @Autowired
    SchoolRepository schoolRepository;



    public Flux<School> getAllSchools(){
        return schoolRepository.findAll();
    }


    public Mono<School> addSchool(Mono<School> school1) {
        return school1.flatMap(schoolRepository::insert);
    }

    public Mono<School> getSchoolById(int pathVariable) {
        return schoolRepository.findById(pathVariable);
    }

    public Mono<Classes> getClassById(int pathVariableSchool, int pathVariableClass) {
        Mono<School> school1 = getSchoolById(pathVariableSchool);
        return school1.map(school -> school.getClassDetails().get(pathVariableClass - 1));
    }

    public Mono<School> updateSchool(int pathVariable ,Mono<School> schoolMono) {
            Mono<School> product1 = schoolRepository.findById(pathVariable);
               return product1.flatMap(a-> schoolMono.doOnNext(e->e.setUniqueSchoolId(pathVariable))
                            .flatMap(schoolRepository::save));
    }


}
