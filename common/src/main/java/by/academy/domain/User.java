package by.academy.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.Objects;
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class User {

    private Long id;
    private String userName;
    private String surName;
    private Timestamp birthDate;
    private Boolean isDeleted;
    private String login;
    private String password;
    private Timestamp modificationDate;
    private Timestamp creationDate;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
