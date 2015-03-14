package com.danekja.edu.ormprobe.mappers;

import com.danekja.edu.ormprobe.domain.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by witz on 6.3.15.
 */
public interface GroupMapper {
    public void insertGroup(Group item);

    public Group getGroupById(@Param ("id")Integer id);

    public List<Group> getAllGroups();

    public void update(Group item);

    public void delete(@Param ("id")Integer id);
}
