package com.example.projectAssessmentCombined.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolResponse {
        private int uniqueSchoolId;
        private String schoolName;
        private List<ClassesResponse> classDetails;
    }

