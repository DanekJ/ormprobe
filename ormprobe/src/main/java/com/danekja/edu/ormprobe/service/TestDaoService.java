package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.dao.TestDao;
import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;
import com.danekja.edu.ormprobe.persistance.GroupMapper;
import com.danekja.edu.ormprobe.persistance.ItemMapper;
import com.danekja.edu.ormprobe.persistance.OwnershipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by witz on 18.4.15.
 */
public class TestDaoService implements TestDao {
  @Autowired
  private GroupMapper groupMapper;

  @Autowired
  private OwnershipMapper ownershipMapper;

  @Autowired
  private ItemMapper itemMapper;

  @Transactional
  public Set<Group> listOwnershipCandidates(long bigGroupId) {
    return groupMapper.listOwnershipCandidates(bigGroupId);
  }

  @Transactional
  public Set<BigGroup> listItemsBigGroups(long itemId) {
    return groupMapper.listItemsBigGroups(itemId);
  }

  @Transactional
  public boolean isConnectedToBigGroup(long bigGroupId, long itemId) {
    return ownershipMapper.isConnectedToBigGroup(bigGroupId, itemId);
  }

  @Transactional
  public Set<Item> listBigGroupsItems(long bigGroupId) {
    return itemMapper.listBigGroupsItems(bigGroupId);
  }
}
