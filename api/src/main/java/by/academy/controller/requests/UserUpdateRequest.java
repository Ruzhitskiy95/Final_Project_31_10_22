package by.academy.controller.requests;

import by.academy.domain.Gender;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserUpdateRequest {
    private Long id;
    private String userName;
    private String surName;
    private Timestamp birthDate;
    private String login;
    private String password;
    private Timestamp modificationDate;
    private Gender gender;

}
