package com.example.projectAssessmentUserService.service;

import com.example.projectAssessmentUserService.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService service;

    @Test
    void testUserById(){
        Mono<User> employeeServiceResult = service.getUserById(1);
        employeeServiceResult.subscribe(result ->{
            System.out.println(("Result1 ::"+result));
        });
        StepVerifier.create(employeeServiceResult)
                .consumeNextWith(result -> {
                            Assertions.assertEquals("Mihir Vats", result.getUserName());
                        }
                ).verifyComplete();
    }

    @Test
    void getUsers() {
        User user = new User();

        user.setUserName("Mihir Vats");
        user.setUserId(3);

        Flux<User> listOfSchool = Flux.just(user);
        StepVerifier.create(listOfSchool)
                .consumeNextWith(response -> {
                            Assertions.assertEquals("Mihir Vats", response.getUserName());
                            Assertions.assertEquals(3, response.getUserId());
                        }
                ).verifyComplete();
    }

    @Test
    void getUserByID() {
        User user = new User();
        user.setUserName("Mihir Vats");
        user.setUserId(3);
        Mono<User> schoolMono = Mono.just(user);
        StepVerifier.create(schoolMono)
                .consumeNextWith(response -> {
                            Assertions.assertEquals("Mihir Vats", response.getUserName());
                            Assertions.assertEquals(3, response.getUserId());
                        }
                ).verifyComplete();

    }


}

