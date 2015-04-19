package com.danekja.edu.ormprobe.persistance;

import com.danekja.edu.ormprobe.domain.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by witz on 7.3.15.
 */
public interface ItemMapper {
    public List<Item> getAll();

    public Item getById(@Param("id") long id);

    public void delete(@Param("id") long id);

    public void insert(Item item);

    public void update(Item item);

    public Set<Item> listBigGroupsItems(@Param("id") long id);
}
