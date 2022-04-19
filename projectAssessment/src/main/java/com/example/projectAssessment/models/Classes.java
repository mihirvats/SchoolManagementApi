package com.example.projectAssessment.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "classes")
public class Classes {

    @Id
    private int classCode;
    private String className;
    private int studentStrength;
    private int instructorCode;

}
