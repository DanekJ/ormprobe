package com.danekja.edu.ormprobe.domain;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */
public class Group extends BaseObject {

	private String name;

    protected GroupType groupType;

	public Group(){
        this.groupType = GroupType.Group;
	}

    public GroupType getGroupType() {
        return groupType;
    }

	public void finalize() throws Throwable {

	}

	public String getName(){
		return name;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setName(String newVal){
		name = newVal;
	}

	/**
	 * Returns true if the instance is of class BigGroup, false otherwise.
	 */
	public boolean isBigGroup(){
		return this instanceof BigGroup;
	}

}