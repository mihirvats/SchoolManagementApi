package com.example.projectAssessment.repo;

import com.example.projectAssessment.models.School;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends ReactiveMongoRepository<School, Integer> {
}
