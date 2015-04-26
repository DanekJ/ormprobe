package com.danekja.edu.ormprobe;

import com.danekja.edu.ormprobe.dao.DaoTester;
import com.danekja.edu.ormprobe.dao.DaoTesterWithCriteriaBuilder;
import com.danekja.edu.ormprobe.dao.DaoTesterWithStringQueries;
import com.danekja.edu.ormprobe.servlet.Servlet;
import com.danekja.edu.ormprobe.utils.DataGenerator;
import com.danekja.edu.ormprobe.utils.SessionPersistUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {

        AnnotationConfiguration cfg=new AnnotationConfiguration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        DataGenerator generator = new DataGenerator( new SessionPersistUtil(factory) );

        generator.generateData(false);

        DaoTester tester = new DaoTesterWithStringQueries(factory);
        tester.listOwnershipCandidates(1);
          tester.isConnectedToBigGroup(1, 5);
          tester.listBigGroupsItems(8);
        
        tester.listItemsBigGroups(5);
        
        DaoTesterWithCriteriaBuilder dcb = new DaoTesterWithCriteriaBuilder(factory);
        
        dcb.listBigGroupsItems(8);
        dcb.listOwnershipCandidates(1);
        dcb.listItemsBigGroups(5);
        dcb.isConnectedToBigGroup(1, 5);
         
          System.out.println("Jsem tam");
//        DaoTester tester = new DaoTesterWithCriteriaBuilder(emf);
    }
}