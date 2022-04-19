package com.example.projectAssessment.repo;

import com.example.projectAssessment.models.Classes;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ClassRepository extends ReactiveMongoRepository<Classes, Integer> {
    Mono<Classes> findById(Mono<Object> class1);
}
