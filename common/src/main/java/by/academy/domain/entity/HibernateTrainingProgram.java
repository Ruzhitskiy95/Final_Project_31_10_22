package by.academy.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "training_program")
public class HibernateTrainingProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "training_name")
    private String training_name;

    @Column(name = "exercise_one")
    private String exercise_one;

    @Column(name = "exercise_two")
    private String exercise_two;

    @Column(name = "exercise_three")
    private String exercise_three;

    @Column(name = "exercise_four")
    private String exercise_four;

    @ManyToOne
    @JoinColumn(name = "exercise_one", insertable = false, updatable = false)
    @JsonBackReference
    private HibernateExerciseList trainingProgramExerciseOne;

    @ManyToOne
    @JoinColumn(name = "exercise_two", insertable = false, updatable = false)
    @JsonBackReference
    private HibernateExerciseList trainingProgramExerciseTwo;

    @ManyToOne
    @JoinColumn(name = "exercise_three", insertable = false, updatable = false)
    @JsonBackReference
    private HibernateExerciseList trainingProgramExerciseThree;

    @ManyToOne
    @JoinColumn(name = "exercise_four", insertable = false, updatable = false)
    @JsonBackReference
    private HibernateExerciseList trainingProgramExerciseFour;

}
