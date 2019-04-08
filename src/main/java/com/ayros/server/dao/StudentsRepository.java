package com.ayros.server.dao;

import com.ayros.server.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {

    public Student findByLogin(String login);

    public Student findByLoginAndPassword(String login, String password);

}
