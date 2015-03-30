package com.danekja.edu.ormprobe.dao;


import com.danekja.edu.ormprobe.utils.DataGenerator;
import com.danekja.edu.ormprobe.utils.JPAPersistUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Karel Zíbar
 */
public class DaoTesterWithJPQLTest {
	private static EntityManager em;
	private static EntityManagerFactory emf;

	@BeforeClass
	public static void tearUp(){
		emf = Persistence.createEntityManagerFactory("JET");
		em = emf.createEntityManager();

		DataGenerator generator = new DataGenerator( new JPAPersistUtil(emf, em) );
		generator.generateData(false);
	}

	@AfterClass
	public static void tearDown(){
		emf.close();
		em.close();
	}


	@Test
	public void testIsDirectConnectedToBigGroup() throws Exception {
//		DaoTester tester = new DaoTesterWithJPQL(emf);
//		TypedQuery<Ownership> query = em.createQuery("SELECT o FROM Ownership o WHERE o.ownershipType = ?1", Ownership.class);
//		query.setParameter(1, OwnershipType.GROUP);
//		List<Ownership> ois = query.getResultList();
//		Ownership oi = ois.get(new Random().nextInt(ois.size()));
//
//		boolean res = tester.isConnectedToBigGroup(oi.getUpper().getId(), oi.getLower().getId());
//		Assert.assertTrue(res);
	}

}
