package com.example.projectAssessmentCombined.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import reactor.core.publisher.Mono;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClassesResponse {
    private int classCode;
    private String className;
    private int studentStrength;
    private int instructorCode;


}
