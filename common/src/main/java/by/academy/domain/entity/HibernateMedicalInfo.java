package by.academy.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "medical_info")
@EqualsAndHashCode(exclude = {
        "roles", "trainingJournalsSets", "userMedicalInfo", "exerciseList", "userAnthropometrySet",
        "trainingJournalExercise", "userTraining"
})
public class HibernateMedicalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blood_type")
    private Long bloodType;

    @Column(name = "rh")
    private String rh;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;

    @Column(name = "user_id")
    private Long userId;

//    @OneToOne
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    @JsonBackReference
//    private HibernateUser userMedical;
}
