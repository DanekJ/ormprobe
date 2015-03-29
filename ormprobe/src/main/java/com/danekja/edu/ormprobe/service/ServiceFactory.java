package com.danekja.edu.ormprobe.service;

import com.danekja.edu.ormprobe.domain.*;

/**
 * Created by witz on 29.3.15.
 */
public class ServiceFactory {
    public static SuperService getServiceForClass(Class clazz) {
        SuperService ss = null;
        if(clazz.equals(Item.class))
            ss = new ItemService();

        else if(clazz.equals(Group.class)
                || clazz.equals(BigGroup.class))
            ss = new GroupService();

        else if(clazz.equals(Ownership.class)
                || clazz.equals(OwnershipItem.class)
                || clazz.equals(OwnershipGroup.class))
            ss = new OwnershipService();
        else
            System.out.println("No service for class " + clazz);
        return ss;
    }
}
