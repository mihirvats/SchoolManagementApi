package com.example.projectAssessment.service;

import com.example.projectAssessment.models.Classes;
import com.example.projectAssessment.models.School;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class SchoolServiceTest {
    @Autowired
    SchoolService schoolService;
    @Test
    void testSchoolById(){
        Mono<School> results = schoolService.getSchoolById(1);
        results.subscribe(result ->{
            System.out.println(("Result ::"+result));
        });
        StepVerifier.create(results)
                .consumeNextWith(result -> {
                            Assertions.assertEquals("Meerut Public School", result.getSchoolName());
                        }
                ).verifyComplete();
    }

    @Test
    void testClassById(){
        Mono<Classes> results = schoolService.getClassById(1,1);
        results.subscribe(result ->{
            System.out.println(("Result ::"+result));
        });
        StepVerifier.create(results)
                .consumeNextWith(result -> {
                            Assertions.assertEquals(1, result.getClassCode());
                        }
                ).verifyComplete();
    }


    @Test
    void getSchoolList() {
        School school = new School();

        school.setSchoolName("Karan Public School");
        school.setUniqueSchoolId(3);

        Flux<School> listOfSchool = Flux.just(school);
        StepVerifier.create(listOfSchool)
                .consumeNextWith(response -> {
                            Assertions.assertEquals("Karan Public School", response.getSchoolName());
                            Assertions.assertEquals(3, response.getUniqueSchoolId());
                        }
                ).verifyComplete();
    }

    @Test
    void getSchoolByID() {
        School school = new School();
        school.setSchoolName("Karan Public School");
        school.setUniqueSchoolId(3);
        Mono<School> schoolMono = Mono.just(school);
        StepVerifier.create(schoolMono)
                .consumeNextWith(response -> {
                            Assertions.assertEquals("Karan Public School", response.getSchoolName());
                            Assertions.assertEquals(3, response.getUniqueSchoolId());
                        }
                ).verifyComplete();

    }


    @Test
    void getClaassById() {
        Classes class1 = new Classes();
        class1.setClassName("4B");
        class1.setClassCode(3);
        Mono<Classes> schoolMono = Mono.just(class1);
        StepVerifier.create(schoolMono)
                .consumeNextWith(response -> {
                            Assertions.assertEquals("4B", response.getClassName());
                            Assertions.assertEquals(3, response.getClassCode());
                        }
                ).verifyComplete();

    }
}
//    @Autowired
//    ApplicationContext context;
//
//    @MockBean
//    SchoolRepository schoolRepository;
//
//    @MockBean
//    ClassRepository classRepository;
//
//    @Autowired
//    SchoolService schoolService;
//
//    @BeforeAll
//    void setup(
//    })
//
//
//    @Test
//    void getSchoolById() {
//
//        var schools = schoolRepository.findAll();
//
//        StepVerifier.create(schools)
//                .assertNext(school -> {
//                    assertEquals("Meerut Public School", school.getSchoolName());
//                })
//                .verifyComplete();
//
//
//    }

