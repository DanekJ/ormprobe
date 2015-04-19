package com.danekja.edu.ormprobe.persistance;

import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by witz on 6.3.15.
 */
public interface GroupMapper {
    public void insert(Group item);

    public Group getById(@Param("id") long id);

    public List<Group> getAll();

    public void update(Group item);

    public void delete(@Param("id") long id);

    public Set<Group> listOwnershipCandidates(@Param("id") long id);

    public Set<BigGroup> listItemsBigGroups(@Param("id") long id);
}
