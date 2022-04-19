package com.example.projectAssessmentUserService.repo;

import com.example.projectAssessmentUserService.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User,Integer> {
}
