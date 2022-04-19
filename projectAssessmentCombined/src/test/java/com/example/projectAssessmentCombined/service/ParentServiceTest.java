package com.example.projectAssessmentCombined.service;

import com.example.projectAssessmentCombined.models.ClassesResponse;
import com.example.projectAssessmentCombined.models.SchoolResponse;
import com.example.projectAssessmentCombined.models.SchoolWithUsers;
import com.example.projectAssessmentCombined.models.UserResponse;
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
class ParentServiceTest {
    @Autowired
    ParentService parentService;

    @Test
    void testUserById(){
        Mono<UserResponse> employeeServiceResult = parentService.getUserById(1);
        employeeServiceResult.subscribe(result ->{
            System.out.println(("Result ::"+result));
        });
        StepVerifier.create(employeeServiceResult)
                .consumeNextWith(result -> {
                            Assertions.assertEquals("Mihir Vats", result.getUserName());
                        }
                ).verifyComplete();
    }


    @Test
    void testSchoolById(){
        Mono<SchoolResponse> employeeServiceResult = parentService.getSchoolById(1);
        employeeServiceResult.subscribe(result ->{
            System.out.println(("Result ::"+result));
        });
        StepVerifier.create(employeeServiceResult)
                .consumeNextWith(result -> {
                            Assertions.assertEquals("Meerut Public School", result.getSchoolName());
                        }
                ).verifyComplete();
    }


    @Test
    void testClassById(){
        Flux<ClassesResponse> employeeServiceResult = parentService.getClassById(1,1);
        employeeServiceResult.subscribe(result ->{
            System.out.println(("Result ::"+result));
        });
        StepVerifier.create(employeeServiceResult)
                .consumeNextWith(result -> {
                            Assertions.assertEquals(1, result.getClassCode());
                        }
                ).verifyComplete();
    }
}