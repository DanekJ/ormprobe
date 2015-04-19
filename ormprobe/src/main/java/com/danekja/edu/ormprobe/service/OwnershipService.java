package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.Ownership;
import com.danekja.edu.ormprobe.persistance.OwnershipMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by witz on 18.4.15.
 */
public class OwnershipService implements OwnershipMapper {

  @Autowired
  private OwnershipMapper ownershipMapper;

  public List<Ownership> getAll() {
    return ownershipMapper.getAll();
  }

  public Ownership getById(@Param("id") long id) {
    return ownershipMapper.getById(id);
  }

  public void insert(Ownership o) {
    ownershipMapper.insert(o);
  }

  public void delete(@Param("id") long id) {
    ownershipMapper.delete(id);
  }

  public boolean isConnectedToBigGroup(@Param("bigGroupId") long bigGroupId, @Param("itemId") long itemId) {
    return ownershipMapper.isConnectedToBigGroup(bigGroupId, itemId);
  }
}
