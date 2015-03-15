package com.danekja.edu.ormprobe;

import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;
import com.danekja.edu.ormprobe.domain.OwnershipItem;
import com.danekja.edu.ormprobe.domain.BigGroup;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args){
    AnnotationConfiguration cfg=new AnnotationConfiguration();  
    cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
      
    //creating seession factory object  
    SessionFactory factory=cfg.buildSessionFactory();  
      
    //creating session object  
    Session session=factory.openSession();  
      
    //creating transaction object  
    Transaction t=session.beginTransaction();  
  
        Group group = new Group();
        group.setName("Mala");
        session.persist(group);
        
        Item item = new Item();
        item.setName("Vec");
        session.persist(item);
        
        BigGroup bg = new BigGroup();
        bg.setName("Velka");
        session.persist(bg);

        OwnershipItem oItem = new OwnershipItem();
        oItem.setLower(item);
        oItem.setUpper(group);
        session.persist(oItem);
      
    t.commit();//transaction is commited  
    session.close();  
      
    System.out.println("successfully saved");  
    }
}
