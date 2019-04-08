package com.ayros.server;

import com.ayros.server.dao.GroupRepository;
import com.ayros.server.dao.StudentsRepository;
import com.ayros.server.dao.SubgroupRepository;
import com.ayros.server.model.Student;
import com.ayros.server.model.StudentsGroup;
import com.ayros.server.model.SubGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthorizationRepTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentsRepository newsRepository;

    @Autowired
    private SubgroupRepository subgroupRepository;

    @Autowired
    private GroupRepository groupRepository;

    private StudentsGroup studentsGroup;
    private SubGroup subGroup;
    private Student student;

    @Test
    public void addTest(){
        entityManager.persist(studentsGroup);
        entityManager.persist(subGroup);
        entityManager.persist(student);
        Student student = newsRepository.findByLogin("ayros");
        assertThat(student).isEqualTo(this.student);
    }

    @Test
    public void getSubgroupTest(){
        entityManager.persist(studentsGroup);
        entityManager.persist(subGroup);
        entityManager.persist(student);
        SubGroup subGroup = subgroupRepository.findByNumAndStudentsGroup(1,groupRepository.getOne(106));
        assertThat(subGroup).isEqualTo(this.subGroup);
    }

    @Test
    public void userExistsTest(){
        entityManager.persist(studentsGroup);
        entityManager.persist(subGroup);
        entityManager.persist(student);
        Student student = newsRepository.findByLoginAndPassword("ayros","1234567");
        assertThat(student).isEqualTo(this.student);
    }

    @PostConstruct
    public void init(){
        studentsGroup = new StudentsGroup();
        subGroup = new SubGroup();
        student = new Student();
        studentsGroup.setGroupNum(106);
        subGroup.setNum(1);
        subGroup.setStudentsGroup(studentsGroup);
        List<SubGroup> subGroups = new ArrayList<>();
        subGroups.add(subGroup);
        studentsGroup.setSubGroup(subGroups);

        student.setLogin("ayros");
        student.setPassword("1234567");
        //student.setStudents_group(studentsGroup);
        student.setSubgroup(subGroup);

    }
}
