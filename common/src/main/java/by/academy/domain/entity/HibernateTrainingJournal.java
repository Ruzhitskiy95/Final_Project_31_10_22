package by.academy.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "training_journal")
@EqualsAndHashCode(exclude = {
        "roles", "trainingJournalsSets", "userMedicalInfo", "exerciseList", "userAnthropometrySet",
        "trainingJournalExercise", "userTraining"
})
public class HibernateTrainingJournal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "training_data")
    private Timestamp trainingData;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "exercise_id")
    private  Long exerciseId;

    @Column(name = "sets")
    private Long sets;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "reps")
    private Long reps;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "total_weight")
    private Long totalWeight;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference
    private HibernateUser userTraining;

    @ManyToOne
    @JoinColumn(name = "exercise_id", insertable = false, updatable = false)
    @JsonBackReference
    private HibernateUser trainingJournalExercise;


//    @OneToMany(mappedBy = "exercise_id",
//            fetch = FetchType.EAGER)
//    @JsonManagedReference
//    private Set<HibernateExerciseList> exercise;


}
