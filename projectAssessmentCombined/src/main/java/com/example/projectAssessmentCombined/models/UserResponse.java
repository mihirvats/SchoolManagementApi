package com.example.projectAssessmentCombined.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    @Id
    private int userId;
    private String userName;
    private int classCode;
    private String role;
    private int schoolId;
}
