package by.academy.domain.dto;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.sql.Timestamp;

public interface TrainingResultDTO {

    String getUserName();
    String getSurName();
    Timestamp getTrainingData();
    String getExerciseName();
    Long getSets();
    Long getReps();

    Long getWeight();

    Long getTotalWeight();



}
