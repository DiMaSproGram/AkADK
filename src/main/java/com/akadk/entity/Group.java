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
@Table(name = "groups")
public class Group extends AbstractEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq", sequenceName = "group_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Size(min = 5, max = 50, message = Constant.ERROR_MESSAGE_FOR_GROUP_NAME)
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "group")
    private List<User> groups;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;

    public Group(String name) {
        this.name = name;
    }
}
