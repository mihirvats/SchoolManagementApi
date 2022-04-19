package com.example.projectAssessmentCombined.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usersWithSchool")
public class UsersWithSchool {
    private List<UserResponse> userResponse;
    private List<SchoolResponse> schoolResponse;

}
