package com.danekja.edu.ormprobe.mappers;

import com.danekja.edu.ormprobe.domain.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by witz on 7.3.15.
 */
public interface ItemMapper {
    public List<Item> getAll();

    public Item getById(@Param("id") Integer id);

    public void delete(@Param("id")Integer id);

    public void insert(Item item);

    public void update(Item item);
}
