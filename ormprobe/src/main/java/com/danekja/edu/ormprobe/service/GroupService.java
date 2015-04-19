package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.persistance.GroupMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by witz on 30.3.15.
 */
@Service
public class GroupService implements GroupMapper {

  @Autowired
  private GroupMapper groupMapper;

  @Transactional
  public void insert(Group g) {
    groupMapper.insert(g);
  }

  @Transactional
  public Group getById(@Param("id") long id) {
    return groupMapper.getById(id);
  }

  @Transactional
  public List<Group> getAll() {
    return groupMapper.getAll();
  }

  @Transactional
  public void update(Group item) {
    groupMapper.update(item);
  }

  @Transactional
  public void delete(@Param("id") long id) {
    groupMapper.delete(id);
  }

  @Transactional
  public Set<Group> listOwnershipCandidates(long id) {
    return groupMapper.listOwnershipCandidates(id);
  }

  @Transactional
  public Set<BigGroup> listItemsBigGroups(long id) {
    return groupMapper.listItemsBigGroups(id);
  }
}
