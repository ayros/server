package com.ayros.server.service;

import com.ayros.server.model.Schedule;
import com.ayros.server.model.Student;

import java.util.List;

public interface ScheduleService {

    public List<Schedule> getStudentsSchedule(Student student);
}
