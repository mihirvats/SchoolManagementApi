package com.example.projectAssessmentUserService.service;


import com.example.projectAssessmentUserService.models.User;
import com.example.projectAssessmentUserService.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Mono<User> saveUser(ServerRequest serverRequest) {
        Mono<User> user1 = serverRequest.bodyToMono(User.class);
        return user1.flatMap(userRepository::insert);

    }


    public Mono<User> getUserById(int pathVariable) {

        return userRepository.findById(pathVariable);
    }
    public Mono<User> updateStudent(int pathVariable ,Mono<User> schoolMono) {
        Mono<User> product1 = userRepository.findById(pathVariable);
        return product1.flatMap(a-> schoolMono.doOnNext(e->e.setUserId(pathVariable))
                .flatMap(userRepository::save));
    }



}
