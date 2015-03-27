package com.danekja.edu.ormprobe;

import com.danekja.edu.ormprobe.dao.DaoTester;
import com.danekja.edu.ormprobe.dao.DaoTesterWithCriteriaBuilder;
import com.danekja.edu.ormprobe.dao.DaoTesterWithStringQueries;
import com.danekja.edu.ormprobe.utils.DataGenerator;
import com.danekja.edu.ormprobe.utils.JPAPersistUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JET");
        DataGenerator generator = new DataGenerator( new JPAPersistUtil(emf) );

        generator.generateData(false);

        DaoTester tester = new DaoTesterWithStringQueries(emf);
//        DaoTester tester = new DaoTesterWithCriteriaBuilder(emf);
        




    }
}
