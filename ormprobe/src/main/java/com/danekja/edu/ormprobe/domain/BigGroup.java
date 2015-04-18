package com.danekja.edu.ormprobe.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */

@Entity
@Table(name="group_table")
@DiscriminatorValue("BigGroup")
public class BigGroup extends Group {

	public BigGroup(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
        
        @Transient
        @Override
        public boolean isBigGroup() {
                return super.isBigGroup();
        }

}