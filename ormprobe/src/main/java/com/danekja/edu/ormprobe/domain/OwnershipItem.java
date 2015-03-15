package com.danekja.edu.ormprobe.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */

@Entity
@DiscriminatorValue(value="ITEM")
public class OwnershipItem extends Ownership<Item> {

	public OwnershipItem(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
        
         @ManyToOne
        @JoinColumn(name = "LowerId")
        @Override
        public Item getLower() {
            return super.getLower();
        }

        @Override
        public void setLower(Item newVal) {
            super.setLower(newVal);
        }        

}
