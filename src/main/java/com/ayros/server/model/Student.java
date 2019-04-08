package com.ayros.server.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "Students")
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "subgroup")
    private SubGroup subgroup;

    /*@ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "students_group")
    private StudentsGroup students_group;

    */
}
