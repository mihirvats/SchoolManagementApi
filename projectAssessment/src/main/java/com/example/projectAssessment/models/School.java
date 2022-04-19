package com.example.projectAssessment.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "schools")
public class School {

    @Id
    private int uniqueSchoolId;
    private String schoolName;
    private List<Classes> classDetails;
}
