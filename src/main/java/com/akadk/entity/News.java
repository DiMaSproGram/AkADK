package com.akadk.entity;


import com.akadk.common.Constant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "news")
public class News extends AbstractEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_seq")
    @SequenceGenerator(name = "news_seq", sequenceName = "news_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Size(min = 2, max = 50, message = Constant.ERROR_MESSAGE_FOR_NEWS_HEADER)
    @Column(name = "header", nullable = false)
    private String header;

    @Column(name = "description", nullable = false)
    private String description;

    public News(String header, String description) {
        this.header = header;
        this.description = description;
    }
}
