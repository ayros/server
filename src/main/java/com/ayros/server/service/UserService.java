package com.ayros.server.service;

import com.ayros.server.model.Student;

public interface UserService {

    public Student getUser(String name, String password);

    public boolean addUser(String login, String password, String group, String subgroup);

    public boolean userExists(String name, String password);

    public boolean deleteUser(Student user);

    public String[] isUserValid(String login,String password, String group, String subgroup);
}
