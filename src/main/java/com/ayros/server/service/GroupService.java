package com.ayros.server.service;


import com.ayros.server.model.StudentsGroup;

public interface GroupService {

    public StudentsGroup getGroup(Integer num);

    public boolean groupExists(Integer num);

    public boolean subGroupExists(Integer group_num, Integer subgroup_num);


}
