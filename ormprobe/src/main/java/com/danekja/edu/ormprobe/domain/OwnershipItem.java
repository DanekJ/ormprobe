package com.danekja.edu.ormprobe.domain;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */
public class OwnershipItem extends Ownership<Item> {

	public OwnershipItem(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

    @Override
    public OwnershipType getOwnershipType(){
        return OwnershipType.ITEM;
    }

}