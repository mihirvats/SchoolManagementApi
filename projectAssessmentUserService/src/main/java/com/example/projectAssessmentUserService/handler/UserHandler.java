package com.example.projectAssessmentUserService.handler;

import com.example.projectAssessmentUserService.models.User;
import com.example.projectAssessmentUserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    @Autowired

    UserService userService;



    public Mono<ServerResponse> handleAllUsers(ServerRequest request) {
        Flux<User> user1 = userService.getAllUsers();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(user1, User.class);
    }

    public Mono<ServerResponse> saveUser(ServerRequest request) {
        Mono<User> user1 = userService.saveUser(request);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(user1, User.class);

    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        int pathVariable = Integer.parseInt(request.pathVariable("userId"));
        Mono<User> user1 = userService.getUserById(pathVariable);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(user1, User.class);
    }

    public Mono<ServerResponse> updateUser(ServerRequest request){
        Mono<User> userToUpdate = request.bodyToMono(User.class);
        int pathVariable = Integer.parseInt(request.pathVariable("schoolId"));
        Mono<User> user1= userService.updateStudent(pathVariable,userToUpdate );
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(user1,User.class);
    }

}
