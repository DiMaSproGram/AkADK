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
@Table(name = "marks")
public class Mark extends AbstractEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mark_seq")
    @SequenceGenerator(name = "mark_seq", sequenceName = "mark_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Size(max = 50, message = Constant.ERROR_MESSAGE_FOR_MARK_STATS)
    @Column(name = "mark_stats")
    private String markStats;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private List<User> users;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "subject_id")
    private Subject subjects;

    public Mark(String markStats) {
        this.markStats = markStats;
    }
}
