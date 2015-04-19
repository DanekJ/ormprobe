package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.Item;
import com.danekja.edu.ormprobe.persistance.ItemMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by witz on 18.4.15.
 */
@Service
public class ItemService implements ItemMapper {

  @Autowired
  private ItemMapper itemMapper;

  @Transactional
  public List<Item> getAll() {
    return itemMapper.getAll();
  }

  @Transactional
  public Item getById(@Param("id") long id) {
    return itemMapper.getById(id);
  }

  @Transactional
  public void delete(@Param("id") long id) {
    itemMapper.delete(id);
  }

  @Transactional
  public void insert(Item item) {
    itemMapper.insert(item);
  }

  @Transactional
  public void update(Item item) {
    itemMapper.update(item);
  }

  @Transactional
  public Set<Item> listBigGroupsItems(@Param("id") long id) {
    return itemMapper.listBigGroupsItems(id);
  }
}
