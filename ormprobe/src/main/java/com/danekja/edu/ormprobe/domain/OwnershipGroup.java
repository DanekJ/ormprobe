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
		return (BigGroup) super.getUpper();
	}

	/**
	 * Implementation must ensure the newVal parameter is of type BigGroup.
	 * 
	 * @param newVal
	 */
	public void setUpper(Group newVal){
		if(newVal instanceof BigGroup) {
            super.setUpper(newVal);
        } else {
            throw new IllegalArgumentException("Only BigGroup can be the upper entity in OwnershipGroup relationship!");
        }
	}

    @Override
    public OwnershipType getOwnershipType(){
        return OwnershipType.GROUP;
    }

}