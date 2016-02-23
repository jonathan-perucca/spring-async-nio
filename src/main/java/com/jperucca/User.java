package com.jperucca;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static com.jperucca.User.Visibility.PUBLIC;
import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    @Enumerated(STRING)
    private Visibility visibility;

    @JsonIgnore
    public boolean isPublic() {
        return PUBLIC == visibility;
    }

    public enum Visibility {
        PRIVATE,
        PUBLIC
    }
}