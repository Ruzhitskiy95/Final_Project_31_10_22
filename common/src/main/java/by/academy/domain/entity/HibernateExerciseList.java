package by.academy.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {
        "trainingProgram"
})
@Table(name = "exercise_list")
public class HibernateExerciseList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exercise_name")
    private String exerciseName;

    @OneToMany(mappedBy = "trainingJournalExercise",
            fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<HibernateTrainingJournal> exerciseList;

    @OneToMany(mappedBy = "trainingProgramExerciseOne",
            fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<HibernateTrainingProgram> trainingProgramOne;

    @OneToMany(mappedBy = "trainingProgramExerciseTwo",
            fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<HibernateTrainingProgram> trainingProgramTwo;

    @OneToMany(mappedBy = "trainingProgramExerciseThree",
            fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<HibernateTrainingProgram> trainingProgramThree;

    @OneToMany(mappedBy = "trainingProgramExerciseFour",
            fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<HibernateTrainingProgram> trainingProgramFour;

}
