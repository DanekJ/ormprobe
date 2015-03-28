package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.Item;
import com.danekja.edu.ormprobe.mappers.ItemMapper;

/**
 * Created by witz on 13.3.15.
 */
public class ItemService extends SuperService<Item, ItemMapper> implements ItemMapper {

    public ItemService() {
        super(ItemMapper.class, Item.class);
    }
}
