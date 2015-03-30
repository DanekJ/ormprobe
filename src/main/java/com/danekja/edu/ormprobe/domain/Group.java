package com.danekja.edu.ormprobe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.lang.Long;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */
@Entity
@Table(name = "group_table")
public class Group extends BaseObject {
	protected String name;


    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    public boolean isBigGroup() {
        return this instanceof BigGroup;
    }


    @Override
    public String toString() {
        return "Group{" +
          "id=" + id + ", " +
          "name='" + name + '\'' +
            '}';
    }
}