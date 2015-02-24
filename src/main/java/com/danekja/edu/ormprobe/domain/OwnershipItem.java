package com.danekja.edu.ormprobe.domain;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "lowerId")
    @Override
    public Item getLower() {
        return super.getLower();
    }

    @Override
    public void setLower(Item newVal) {
        super.setLower(newVal);
    }

    public void finalize() throws Throwable {
		super.finalize();
	}


}