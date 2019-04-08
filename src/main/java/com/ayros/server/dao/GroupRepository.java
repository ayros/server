package com.ayros.server.dao;

import com.ayros.server.model.StudentsGroup;
import com.ayros.server.model.SubGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<StudentsGroup,Integer> {

    public StudentsGroup findByGroupNum(Integer num);

}
