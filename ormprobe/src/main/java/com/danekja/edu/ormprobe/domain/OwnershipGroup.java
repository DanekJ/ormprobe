package com.danekja.edu.ormprobe.domain;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */
public class OwnershipGroup extends Ownership<Group> {

	public OwnershipGroup(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public BigGroup getUpper(){
		return (BigGroup) super.getLower();
	}

	/**
	 * Implementation must ensure the newVal parameter is of type BigGroup.
	 * 
	 * @param newVal
	 */
	public void setUpper(Group newVal){
		if(upper instanceof BigGroup) {
            super.setUpper(upper);
        } else {
            throw new IllegalArgumentException("Only BigGroup can be the upper entity in OwnershipGroup relationship!");
        }
	}

}