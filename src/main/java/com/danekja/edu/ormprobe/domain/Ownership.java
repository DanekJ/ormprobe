package com.danekja.edu.ormprobe.domain;

import javax.persistence.*;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ownershipType", discriminatorType=DiscriminatorType.STRING)
public abstract class Ownership<T extends BaseObject> extends BaseObject {
	private Group upper;
	private T lower;
  private Long lowerId;
	private OwnershipType ownershipType;

	public Ownership(){

	}

  @ManyToOne
  @JoinColumn(nullable = true)
	public Group getUpper(){
		return upper;
	}


  @Transient
	public T getLower(){
		return lower;
	}

  //@Column(name = "lower_id", insertable = false, updatable = false)
  @Transient
	public Long getLowerId(){
		return lowerId;
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
  public void setUpper(Group newVal){
      upper = newVal;
  }

  /**
   *
   * @param lower
   */
  public void setLower(T lower) {
      this.lower = lower;
  }



  /**
   *
   * @param newVal
   */
  public void setLowerId(Long newVal){
        lowerId = newVal;
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


	@Override
	public String toString() {
		return "Ownership{" +
			"id=" + id +
			", upper=" + upper +
			", lower=" + lower +
			", lowerId=" + lowerId +
			", ownershipType=" + ownershipType +
			'}';
	}
}