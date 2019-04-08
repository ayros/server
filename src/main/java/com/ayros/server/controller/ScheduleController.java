package com.ayros.server.controller;

import com.ayros.server.model.Schedule;
import com.ayros.server.model.Student;
import com.ayros.server.service.ScheduleService;
import com.ayros.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "/download",method = RequestMethod.POST)
    public List<Schedule> download(@RequestBody String[] args){
        Student student = userService.getUser(args[0],args[1]);

        List<Schedule> schedules = scheduleService.getStudentsSchedule(student);
        //Schedule[] arr = new Schedule[schedules.size()];
        for(Schedule schedule: schedules){
            System.out.println(schedule.getDay_code()+" "+schedule.getNum_lesson() +" "+schedule.getLesson());
        }
        return schedules;
    }

}
