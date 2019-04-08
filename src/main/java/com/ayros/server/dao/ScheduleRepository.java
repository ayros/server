package com.ayros.server.dao;

import com.ayros.server.model.Schedule;
import com.ayros.server.model.StudentsGroup;
import com.ayros.server.model.SubGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {

    public List<Schedule> findByStudentsGroupAndSubGroup(StudentsGroup group, SubGroup subGroup);
}
