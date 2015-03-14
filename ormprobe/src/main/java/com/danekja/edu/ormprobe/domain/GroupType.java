package com.danekja.edu.ormprobe.domain;

/**
 * Created by witz on 12.3.15.
 */
public enum GroupType {
    Group(Group.class),
    BigGroup(BigGroup.class);

    private Class clazz;

    private GroupType(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
