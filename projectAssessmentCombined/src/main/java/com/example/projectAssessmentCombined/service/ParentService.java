package com.example.projectAssessmentCombined.service;

import com.example.projectAssessmentCombined.models.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class ParentService {


    private final WebClient webClient;

    public ParentService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("").build();
    }

    public Flux<SchoolResponse> getSchools() {
        Flux<SchoolResponse> fluxSchool = this.webClient.get().uri("http://localhost:8081/schools").retrieve().bodyToFlux(SchoolResponse.class);
        return fluxSchool;
    }


    public Flux<UserResponse> getUsers() {
        Flux<UserResponse> fluxUser = this.webClient.get().uri("http://localhost:8090/users").retrieve().bodyToFlux(UserResponse.class);
        return fluxUser;
    }

    public Mono<SchoolResponse> getSchoolById(int pathVariable) {
        final String link = "http://localhost:8081/schools/" + pathVariable;
        return this.webClient.get().uri(link).retrieve().bodyToMono(SchoolResponse.class);
    }

    public Mono<UserResponse> getUserById(int pathVariable) {
        final String link = "http://localhost:8090/users/" + pathVariable;
        return this.webClient.get().uri(link).retrieve().bodyToMono(UserResponse.class);
    }

    public Flux<ClassesResponse> getClassById(int pathVariableSchool, int pathVariableClass) {
        final String link = "http://localhost:8081/schools/" + pathVariableSchool + "/class/" + pathVariableClass;
        Flux<ClassesResponse> monoClass = this.webClient.get().uri(link).retrieve().bodyToFlux(ClassesResponse.class);
        return monoClass;
    }

    public Mono<SchoolWithUsers> getSchoolWithUsers() {
        Mono<List<SchoolResponse>> schools = getSchools().collectList();
        Mono<List<UserResponse>> users = getUsers().collectList();

        return Mono.zip(schools, users)
                .map(tuple2 -> {
                    List<SchoolResponse> schoolResponse = tuple2.getT1();
                    List<UserResponse> userResponse = tuple2.getT2();
                    SchoolWithUsers schoolWithUsers = new SchoolWithUsers();
                    schoolWithUsers.setSchoolResponse(schoolResponse);
                    schoolWithUsers.setUserResponse(userResponse);
                    return schoolWithUsers;
                });
    }

    public Flux<ClassesResponse> classById(int pathVariableSchool, int pathVariableClass) {
        return getClassById(pathVariableSchool, pathVariableClass);
    }


    public Mono<SchoolWithUsers> getSchoolByIdInCombined(int pathVariable) {
        var school = getSchoolById(pathVariable);
        return school
                .flatMap(schoolInfo -> {
                    Mono<List<UserResponse>> user1 =
                            getUsers().filter(q -> q.getSchoolId() == pathVariable).collectList();
                    return user1.map(
                            user2 -> {
                                return new SchoolWithUsers(Collections.singletonList(schoolInfo), user2);
                            });

                });
    }

    public Mono<SchoolWithUsers> getUserWithSchool(int pathVariable) {
        var firstUser = getUserById(pathVariable);
        return firstUser
                .flatMap(userInfo -> {
                    Mono<List<SchoolResponse>> user1 =
                            getSchools().filter(q -> q.getUniqueSchoolId() == userInfo.getSchoolId()).collectList();
                    return user1.map(
                            user2 -> {
                                return new SchoolWithUsers(user2, Collections.singletonList(userInfo));
                            });

                });
    }

    public Mono<UsersWithSchool> getSchoolTeachers(int pathVariable) {
        var allSchools = getSchoolById(pathVariable);
        return allSchools
                .flatMap(schoolInfo -> {
                    Mono<List<UserResponse>> user1 =
                            getUsers().filter(q -> q.getSchoolId() == pathVariable && q.getRole().equals("INSTRUCTOR")).collectList();
                    return user1.map(
                            user2 -> {
                                return new UsersWithSchool(user2,Collections.singletonList(schoolInfo));
                            });
                });
    }

    public Mono<UsersWithSchool> getSchoolStudents(int pathVariable) {

        var allSchools = getSchoolById(pathVariable);

        return allSchools
                .flatMap(schoolInfo -> {
                    Mono<List<UserResponse>> user1 =
                            getUsers().filter(q -> q.getSchoolId() == pathVariable && q.getRole().equals("STUDENT")).collectList();
                    return user1.map(
                            user2 -> {
                                return new UsersWithSchool(user2,Collections.singletonList(schoolInfo));
                            });

                });
    }

    public Mono<UsersWithSchool> getUsersWithSchool() {
        Mono<List<SchoolResponse>> schools = getSchools().collectList();
        Mono<List<UserResponse>> users = getUsers().collectList();

        return Mono.zip(users,schools)
                .map(tuple2 -> {
                    List<SchoolResponse> schoolResponse = tuple2.getT2();
                    List<UserResponse> userResponse = tuple2.getT1();
                    UsersWithSchool schoolWithUsers = new UsersWithSchool();
                    schoolWithUsers.setUserResponse(userResponse);
                    schoolWithUsers.setSchoolResponse(schoolResponse);
                    return schoolWithUsers;});
    }

    }

