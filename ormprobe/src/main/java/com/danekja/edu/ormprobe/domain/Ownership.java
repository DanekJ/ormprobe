package com.danekja.edu.ormprobe.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ownershipType", discriminatorType = DiscriminatorType.STRING)
public abstract class Ownership<T extends BaseObject> extends BaseObject {

	Group upper;
	private T lower;
	private Long lowerId;
	private OwnershipType ownershipType;

	public Ownership(){

	}

	public void finalize() throws Throwable {

	}

        @ManyToOne
        @JoinColumn(nullable = true)
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

        @Transient
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

        @Transient
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

        @Column(name = "ownershipType", nullable = false, insertable = false, updatable = false)
        @Enumerated(EnumType.STRING)
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
        @Transient
	public boolean isGroup(){
		return false;
	}

	/**
	 * Returns true if the ownershipType is ITEM.
	 */
        @Transient
	public boolean isItem(){
		return false;
	}

}