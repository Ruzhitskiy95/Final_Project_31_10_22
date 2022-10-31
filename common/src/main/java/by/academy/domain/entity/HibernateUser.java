package by.academy.domain.entity;

import by.academy.domain.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {
        "roles", "trainingJournalsSets", "exerciseList", "userAnthropometrySet",
        "user"
})
@Table(name = "users")
public class HibernateUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "sur_name")
    private String surName;

    @Column(name = "birth_date")
    private Timestamp birthDate;

    @JsonIgnore
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "login", column = @Column(name = "user_login")),
            @AttributeOverride(name = "password", column = @Column(name = "user_password"))})
    private Credentials credentials;
//    @JsonIgnore
//    @Column(name = "user_login")
//    private String login;
//
//    @JsonIgnore
//    @Column(name = "user_password")
//    private String password;

    @JsonIgnore
    @Column(name = "modification_date")
    private Timestamp modificationDate;

    @JsonIgnore
    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    private Set<HibernateRole> roles;

    @OneToMany(mappedBy = "userTraining",
    fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<HibernateTrainingJournal> trainingJournalsSets;

//    @OneToOne(mappedBy = "userMedical", cascade = CascadeType.ALL,
//    fetch = FetchType.EAGER, orphanRemoval = true)
//    @JsonManagedReference
//    private HibernateMedicalInfo userMedicalInfo;

    @OneToMany(mappedBy = "userAnthropometry",
            fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<HibernateUserAnthropometry> userAnthropometrySet;


}
