package by.academy.controller.requests;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TrainingJournalCreateRequest {

    private Timestamp trainingData;
    private Long userId;
    private Long exerciseId;
    private Long sets;
    private Long reps;
    private Long weight;

}
