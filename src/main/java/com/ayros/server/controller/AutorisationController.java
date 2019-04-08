package com.ayros.server.controller;

import com.ayros.server.model.Student;
import com.ayros.server.service.GroupService;
import com.ayros.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autorisation")
public class AutorisationController {

    private final static String VALID = "Valid";
    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @PostMapping(value = "/login")
    public @ResponseBody
    String login(@RequestBody String[] student){
        System.out.println(student);
        Student student1 = userService.getUser(student[0],student[1]);
        if (student1 != null){
            return "Valid";
        }
        else{
            return "Invalid";
        }
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public @ResponseBody
    String[] register(@RequestBody String[] args){
        String[] response = userService.isUserValid(args[0],args[1],args[2],args[3]);
        userService.addUser(args[0],args[1],args[2],args[3]);

        return response;
    }
}
