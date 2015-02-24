package com.danekja.edu.ormprobe.domain;

import javax.persistence.*;
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






    public void finalize() throws Throwable {

    }

}