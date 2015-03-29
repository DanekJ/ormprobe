package com.danekja.edu.ormprobe.utils;

import com.danekja.edu.ormprobe.domain.*;
import com.danekja.edu.ormprobe.service.*;

/**
 * Created by witz on 28.3.15.
 */
public class MyBatisPersistUtil implements DatabasePersistUtil {
    @Override
    public void beginTransaction() {

    }

    @Override
    public void persistData(BaseObject object) {
        SuperService ss = ServiceFactory.getServiceForClass(object.getClass());
        ss.insert(object);
    }

    @Override
    public void commitChanges() {

    }

    @Override
    public void endConnection() {

    }

    @Override
    public BaseObject selectObjectById(Class clazz, Long id) {
        SuperService ss = ServiceFactory.getServiceForClass(clazz);
        return ss.getById(id.intValue());
    }
}
