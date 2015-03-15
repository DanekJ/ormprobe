package com.danekja.edu.ormprobe.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Zuzana Mikolášová
 * @version 1.0
 * @created 20-II-2015 18:10:41
 */

@MappedSuperclass
public abstract class BaseObject {
    private int id;
    
    public void setId(int id){
        this.id = id;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    public int getId(){
        return this.id;
    }
}

