package com.danekja.edu.ormprobe.domain;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "lowerId", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @Override
    public Group getLower() {
        return super.getLower();
    }

    @Override
    public void setLower(Group newVal) {
        super.setLower(newVal);
    }

//    @Transient
////    @JoinColumn(name = "upper")
//    @Override
//    public BigGroup getUpper(){
//        return (BigGroup) super.getUpper();
//      }

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

}