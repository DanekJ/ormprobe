package com.danekja.edu.ormprobe.domain;

import javax.persistence.*;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */
@Entity
public class BigGroup extends Group {

	public BigGroup(){

	}

	public BigGroup(BigGroup bg, OwnershipItem oi){
		this.id = bg.id;
		this.name = bg.name;
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