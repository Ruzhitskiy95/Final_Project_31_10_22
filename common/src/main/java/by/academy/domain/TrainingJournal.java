package by.academy.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TrainingJournal  {

    private Long id;
    private Timestamp trainingData;
    private Long userId;
    private Long exerciseId;
    private Long sets;
    private Long reps;
    private Long weight;
    private Boolean isDeleted;
    private Integer totalWeight;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
