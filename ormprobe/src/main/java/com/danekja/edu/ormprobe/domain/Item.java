package com.danekja.edu.ormprobe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */

@Entity
public class Item extends BaseObject {

	private String name;

	public Item(){

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

}
