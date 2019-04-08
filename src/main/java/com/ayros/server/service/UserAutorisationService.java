package com.ayros.server.service;

import com.ayros.server.dao.GroupRepository;
import com.ayros.server.dao.StudentsRepository;
import com.ayros.server.dao.SubgroupRepository;
import com.ayros.server.model.Student;
import com.ayros.server.model.StudentsGroup;
import com.ayros.server.model.SubGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAutorisationService implements UserService,GroupService {

    private final static String INVALID = "INVALID";
    private final static String VALID = "VALID";
    @Autowired
    private StudentsRepository usersRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private SubgroupRepository subgroupRepository;


    public UserAutorisationService(){
    }

    @Override
    public Student getUser(String name, String password) {
        return usersRepository.findByLoginAndPassword(name,password);
    }


    @Override
    public boolean addUser(String login, String password, String group, String subgroup) {
        String[] strings = isUserValid(login,password,group,subgroup);
        for (String str: strings){
            if(str.equals(INVALID)){
                return false;
            }
        }
        Student student = new Student();
        student.setLogin(login);
        student.setPassword(password);
        StudentsGroup group1 = groupRepository.getOne(Integer.parseInt(group));
        student.setSubgroup(subgroupRepository.findByNumAndStudentsGroup(Integer.parseInt(subgroup),group1));
        usersRepository.save(student);
        usersRepository.flush();
        return true;
    }

    @Override
    public boolean userExists(String name, String password) {
        if(getUser(name,password) == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUser(Student user) {
        usersRepository.delete(user);
        usersRepository.flush();
        return true;
    }

    @Override
    public StudentsGroup getGroup(Integer num) {
        return groupRepository.findById(num).get();
    }

    @Override
    public boolean groupExists(Integer num) {
        if(groupRepository.findById(num).get()==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean subGroupExists(Integer group_num, Integer subgroup_num) {
        if (subgroupRepository.findByNumAndStudentsGroup(subgroup_num,getGroup(group_num))==null){
            return false;
        }
        return true;
    }

    @Override
    public String[] isUserValid(String login, String password, String group, String subgroup) {
        String[] user = {INVALID,INVALID,INVALID,INVALID};
        if(checkLogin(login)){
            user[0] = VALID;
        }
        if (checkPassword(password)){
            user[1] = VALID;
        }
        if (checkGroup(group,subgroup)){
            user[2] = VALID;
            user[3] = VALID;
        }
        return user;
    }

    private boolean checkLogin(String login){
        System.out.println(usersRepository.findByLogin(login));
        if(usersRepository.findByLogin(login)!=null){
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkPassword(String password){
        if(password.length() < 5){
            return false;
        }
        return true;
    }

    private boolean checkGroup(String group, String subgroup) {
        StudentsGroup studentsGroup = groupRepository.findByGroupNum(Integer.parseInt(group));

        SubGroup subGroup = subgroupRepository.findByNumAndStudentsGroup(Integer.parseInt(subgroup), studentsGroup);
        if (subgroup == null) {
            return false;
        }
        return true;
    }
}