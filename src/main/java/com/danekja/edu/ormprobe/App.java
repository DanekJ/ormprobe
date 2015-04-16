package com.danekja.edu.ormprobe;

import com.danekja.edu.ormprobe.dao.DaoTester;
import com.danekja.edu.ormprobe.dao.DaoTesterWithCriteriaQueries;
import com.danekja.edu.ormprobe.dao.DaoTesterWithHQL;
import com.danekja.edu.ormprobe.domain.BaseObject;
import com.danekja.edu.ormprobe.utils.DataGenerator;
import com.danekja.edu.ormprobe.utils.JPAPersistUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JET");
        EntityManager em = emf.createEntityManager();
        DataGenerator generator = new DataGenerator( new JPAPersistUtil(emf, em) );

        generator.generateData(false);
        System.out.println("====================================================================================================================");
        DaoTester tester = new DaoTesterWithHQL(emf);
        DaoTester tester2 = new DaoTesterWithCriteriaQueries(emf);

//        TypedQuery<Ownership> query = em.createQuery("SELECT o FROM Ownership o WHERE o.ownershipType = ?1 ", Ownership.class);
//        query.setParameter(1, OwnershipType.GROUP);
//        List<Ownership> ogs = query.getResultList();
//        Ownership oi = ogs.get( new Random().nextInt(ogs.size()) );
//
//
//        System.out.println(tester.isConnectedToBigGroup(1L, 1L));
//        System.out.println(tester.isConnectedToBigGroup(oi.getUpper().getId(), oi.getLower().getId()));



        Set<BaseObject> result = new HashSet<BaseObject>();
        Set<BaseObject> result2 = new HashSet<BaseObject>();
        result = new HashSet<BaseObject>(tester.listOwnershipCandidates(8L));
//        result = new HashSet<BaseObject>(tester.listItemsBigGroups(5L));
//        System.out.println(tester.isConnectedToBigGroup(9L, 5L));
//        result = new HashSet<BaseObject>(tester.listBigGroupsItems(8L));

        result2 = new HashSet<BaseObject>(tester2.listOwnershipCandidates(8L));
//        result2 = new HashSet<BaseObject>(tester2.listItemsBigGroups(5L));
//        System.out.println(tester2.isConnectedToBigGroup(9L, 5L));
//        result2 = new HashSet<BaseObject>(tester2.listBigGroupsItems(8L));

        System.out.println("------------------------------------------------------------------------");
        for (BaseObject g : result)
            System.out.println(g);

        System.out.println("------------------------------------------------------------------------");
        for (BaseObject g : result2)
            System.out.println(g);

    }
}
