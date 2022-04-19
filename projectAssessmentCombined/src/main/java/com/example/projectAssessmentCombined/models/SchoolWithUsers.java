package com.example.projectAssessmentCombined.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "schoolWithUsers")
public class SchoolWithUsers {
    private List<SchoolResponse> schoolResponse;
    private List<UserResponse> userResponse;





}
