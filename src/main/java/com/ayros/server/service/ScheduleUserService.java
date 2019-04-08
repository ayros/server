package com.ayros.server.service;

import com.ayros.server.dao.GroupRepository;
import com.ayros.server.dao.ScheduleRepository;
import com.ayros.server.dao.SubgroupRepository;
import com.ayros.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class ScheduleUserService implements ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private GroupRepository repository;

    @Autowired
    private SubgroupRepository subgroupRepository;

    @Override
    public List<Schedule> getStudentsSchedule(Student student) {
        StudentsGroup group = student.getSubgroup().getStudentsGroup();
        List<Schedule> list = scheduleRepository.findByStudentsGroupAndSubGroup(group,student.getSubgroup());
        list.addAll(scheduleRepository.findByStudentsGroupAndSubGroup(group,null));
        return list;
    }

    @PostConstruct
    private void init(){
        Schedule schedule;
        Lesson lesson = new Lesson();
        lesson.setStart("9:00");
        lesson.setEnd("10:30");
        lesson.setName("Аппаратное и програмное обеспечение ЭВМ");
        lesson.setInstructor("Иванов И.И");
        lesson.setType("лекция");
        for(int day_code = 0; day_code < 10; day_code++){
            for(int num_lesson = 0; num_lesson < 6; num_lesson++){
                for (int group = 106; group < 407; group+=100){
                    for(int i = 1; i < 3; i++){
                        schedule = new Schedule();
                        schedule.setDay_code(day_code);
                        schedule.setNum_lesson(num_lesson);
                        StudentsGroup studentsGroup = repository.findByGroupNum(group);
                        schedule.setStudentsGroup(studentsGroup);
                        lesson.setHoll("" + num_lesson);
                        SubGroup subGroup = subgroupRepository.findByNumAndStudentsGroup(i,studentsGroup);
                        schedule.setSubGroup(subGroup);
                        schedule.setLesson(lesson);
                        scheduleRepository.save(schedule);
                        scheduleRepository.flush();
                    }
                }
            }
        }
    }

}
