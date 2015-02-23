package com.danekja.edu.ormprobe.domain;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */
public abstract class Ownership<T extends BaseObject> extends BaseObject {

	private Group upper;
	private T lower;
	private Long lowerId;
	private OwnershipType ownershipType;

	public Ownership(){

	}

	public void finalize() throws Throwable {

	}

	public Group getUpper(){
		return upper;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUpper(Group newVal){
		upper = newVal;
	}

	public T getLower(){
		return lower;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLower(T newVal){
		lower = newVal;
	}

	public Long getLowerId(){
		return lowerId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLowerId(Long newVal){
		lowerId = newVal;
	}

	public OwnershipType getOwnershipType(){
		return ownershipType;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOwnershipType(OwnershipType newVal){
		ownershipType = newVal;
	}

	/**
	 * Returns true when the ownership type is GROUP.
	 */
	public boolean isGroup(){
		return false;
	}

	/**
	 * Returns true if the ownershipType is ITEM.
	 */
	public boolean isItem(){
		return false;
	}

}