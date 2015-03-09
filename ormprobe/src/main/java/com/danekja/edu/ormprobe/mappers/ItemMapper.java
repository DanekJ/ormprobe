package com.danekja.edu.ormprobe.mappers;

import com.danekja.edu.ormprobe.domain.Item;

import java.util.List;

/**
 * Created by witz on 7.3.15.
 */
public interface ItemMapper {
    public List<Item> getAllItems();

    public Item getItemById(int id);

    public void deleteItemById(int id);

    public void insertItem(Item item);

    public void updateItem(Item item);
}
