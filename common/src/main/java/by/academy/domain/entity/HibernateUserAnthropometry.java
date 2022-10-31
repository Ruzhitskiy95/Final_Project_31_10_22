package by.academy.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user_anthropometry")
public class HibernateUserAnthropometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "height")
    private Long height;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "biceps_size")
    private Long biceps_size;

    @Column(name = "chest_size")
    private Long chest_size;

    @Column(name = "waist_size")
    private Long waist_size;

    @Column(name = "quadriceps_size")
    private Long quadriceps_size;

    @Column(name = "calf_size")
    private Long calf_size;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference
    private HibernateUser userAnthropometry;


}
