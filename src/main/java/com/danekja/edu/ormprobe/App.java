package com.danekja.edu.ormprobe;

import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;
import com.danekja.edu.ormprobe.domain.OwnershipItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JET");
        EntityManager em = emf.createEntityManager();

        Group group = new Group();
        Item item = new Item();
        group.setName("Fuu");
        item.setName("Bar");

        OwnershipItem oItem = new OwnershipItem();

        em.getTransaction().begin();
        em.persist(group);
        em.persist(item);
        em.flush();
        em.getTransaction().commit();

//        em.getTransaction().begin();
//        em.remove(group);
//        em.flush();
//        em.getTransaction().commit();

        em.getTransaction().begin();
        oItem.setUpper(group);
        oItem.setLower(item);
        em.persist(oItem);
        em.flush();
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.remove(item);
        em.flush();
        em.getTransaction().commit();
//        List res = em.createQuery("SELECT o FROM Ownership o").getResultList();
//        em.close();
//        emf.close();








        System.out.println( "Hello World!" );
    }
}
