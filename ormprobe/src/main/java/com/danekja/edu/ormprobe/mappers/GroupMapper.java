package com.danekja.edu.ormprobe.mappers;

import com.danekja.edu.ormprobe.domain.Group;

import java.util.List;

/**
 * Created by witz on 6.3.15.
 */
public interface GroupMapper {
    public void insertGroup(Group item);

    public Group getGroupById(Integer id);

    public List<Group> getAllGroups();

    public void update(Group item);

    public void delete(Integer id);
}
