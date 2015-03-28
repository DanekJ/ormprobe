package com.danekja.edu.ormprobe.utils;

import com.danekja.edu.ormprobe.domain.*;
import com.danekja.edu.ormprobe.service.GroupService;
import com.danekja.edu.ormprobe.service.ItemService;
import com.danekja.edu.ormprobe.service.OwnershipService;

/**
 * Created by witz on 28.3.15.
 */
public class MyBatisPersistUtil implements DatabasePersistUtil {
    @Override
    public void beginTransaction() {

    }

    @Override
    public void persistData(BaseObject object) {
        if(object.getClass().equals(Item.class)) {
            new ItemService().insert((Item) object);
        }else if(object.getClass().equals(Group.class)
                || object.getClass().equals(BigGroup.class)){
            new GroupService().insert((Group) object);
        }else if(object.getClass().equals(Ownership.class)
                || object.getClass().equals(OwnershipGroup.class)
                || object.getClass().equals(OwnershipItem.class)) {
            new OwnershipService().insert((Ownership) object);
        }
    }

    @Override
    public void commitChanges() {

    }

    @Override
    public void endConnection() {

    }

    @Override
    public BaseObject selectObjectById(Class clazz, Long id) {
        if(clazz.equals(Item.class)) {
            return new ItemService().getById(id.intValue());
        }else if(clazz.equals(Group.class)
                || clazz.equals(BigGroup.class)){
            return new GroupService().getById(id.intValue());
        }else if(clazz.equals(Ownership.class)
                || clazz.equals(OwnershipGroup.class)
                || clazz.equals(OwnershipItem.class)) {
            return new OwnershipService().getById(id.intValue());
        }else return null;
    }
}
