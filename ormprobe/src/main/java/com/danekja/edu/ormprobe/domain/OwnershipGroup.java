package com.danekja.edu.ormprobe.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */

@Entity
@DiscriminatorValue(value="GROUP")
public class OwnershipGroup extends Ownership<Group> {

	public OwnershipGroup(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

        @Transient
        @Override
	public Group getUpper(){
		return super.getUpper();
	}

	/**
	 * Implementation must ensure the newVal parameter is of type BigGroup.
	 * 
	 * @param newVal
	 */
        @Override
	public void setUpper(Group newVal){
		if(newVal instanceof BigGroup) {
            super.setUpper(newVal);
        } else {
            throw new IllegalArgumentException("Only BigGroup can be the upper entity in OwnershipGroup relationship!");
        }
	}
        
        @ManyToOne
        @JoinColumn(name="lowerId")
        @Override
        public Group getLower() {
            return super.getLower();
        }

        @Override
        public void setLower(Group newVal) {
            super.setLower(newVal);
        }        

}
