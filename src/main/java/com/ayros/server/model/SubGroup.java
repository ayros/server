package com.ayros.server.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class SubGroup {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "number", nullable = false)
    private Integer num;

    @OneToMany(mappedBy = "subgroup",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "students_group")
    private StudentsGroup studentsGroup;

    @OneToMany(mappedBy = "subGroup")
    private List<Schedule> schedule = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubGroup)) return false;
        SubGroup subGroup = (SubGroup) o;
        return num.equals(subGroup.num) &&
                studentsGroup.equals(subGroup.studentsGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, studentsGroup);
    }
}
