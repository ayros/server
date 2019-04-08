package com.ayros.server.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@Entity(name = "StudentsGroup")
@Table(name = "studentsGroup")
public class StudentsGroup {

    @Id
    private Integer groupNum;

    @OneToMany(mappedBy = "studentsGroup")
    private List<SubGroup> subGroup = new ArrayList<>();

    @OneToMany(mappedBy = "studentsGroup")
    private List<Schedule> schedule = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentsGroup)) return false;
        StudentsGroup that = (StudentsGroup) o;
        return groupNum.equals(that.groupNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNum);
    }
}
