package com.example.projectAssessmentUserService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
@Data
public class User {

    @Id
    private int userId;
    private String userName;
    private int classCode;
    private String role;
    private int schoolId;
}
