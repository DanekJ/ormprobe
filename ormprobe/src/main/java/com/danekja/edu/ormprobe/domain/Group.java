package com.danekja.edu.ormprobe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */

@Entity
@Table(name = "group_table")
public class Group extends BaseObject {

	private String name;

	public Group(){

	}

	public void finalize() throws Throwable {

	}
        
        @Column(nullable = false)
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
        @Transient
	public boolean isBigGroup(){
		return false;
	}

}