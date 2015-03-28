package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.mappers.GroupMapper;

/**
 * Created by witz on 9.3.15.
 */
public class GroupService extends SuperService<Group, GroupMapper> implements GroupMapper {
    public GroupService() {
        super(GroupMapper.class, Group.class);
    }
}
