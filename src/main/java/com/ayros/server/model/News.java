package com.ayros.server.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "time",nullable = false, updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date time;

    @Column(name = "title")
    @Size(min = 3, max = 20)
    private String title;

    @Column(name = "description")
    @Size(min = 0, max = 255)
    private String description;
}
