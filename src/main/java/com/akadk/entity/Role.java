package com.akadk.entity;


import com.akadk.common.Constant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends AbstractEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Size(max = 50, message = Constant.ERROR_MESSAGE_FOR_ROLE_NAME)
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<User> users;

    public Role(String name) {
        this.name = name;
    }
}
