package com.ayros.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    private Integer day_code;

    private Integer num_lesson;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "students_group")
    private StudentsGroup studentsGroup;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "subgroup")
    private SubGroup subGroup;

    @Embedded
    private Lesson lesson;

}
