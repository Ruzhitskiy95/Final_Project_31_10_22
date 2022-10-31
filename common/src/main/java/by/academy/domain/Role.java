package by.academy.domain;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Long id;
    private String roleName;
    private Timestamp creationDate;
    private  Timestamp modificationDate;
}
