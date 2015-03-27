package com.danekja.edu.ormprobe.utils;

import com.danekja.edu.ormprobe.domain.BaseObject;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 * @author Karel Zíbar
 */
public class JPAPersistUtil implements DatabasePersistUtil {
	private EntityManagerFactory emf;
	private EntityManager em;


	public JPAPersistUtil(EntityManagerFactory emf) {
		this.emf = emf;
		this.em = emf.createEntityManager();
	}


	/**
	 * Method do all stuffs needed for upcoming database inserts
	 */
	public void beginTransaction() {
		em.getTransaction().begin();
	}

	/**
	 * Method will persist data to database
	 *
	 * @param object
	 * 		- persisting record
	 */
	public void persistData(BaseObject object) {
		em.persist(object);
	}


	/**
	 * Method will commit all changes to database
	 */
	public void commitChanges(){
		em.flush();
		em.getTransaction().commit();
	}



	/**
	 * Method will end all stuffs which were needed for transaction and the database connection will be end too
	 */
	public void endConnection() {
		em.close();
		emf.close();
	}

	/**
	 * Return object from database according to parameter id
	 *
	 * @param clazz
	 * 		- "table" of wanted object
	 * @param id
	 * 		- identifier of wanted object
	 * @return - instance of BaseObject
	 */
	public BaseObject selectObjectById(Class clazz, Long id) {

		if(clazz.equals(Item.class)){

			TypedQuery<Item> query = em.createQuery("SELECT i FROM Item i WHERE i.id = ?1", Item.class);
			query.setParameter(1, id);
			return query.getSingleResult();

		}else if(clazz.equals(Group.class)){

			TypedQuery<Group> query = em.createQuery("SELECT g FROM Group g WHERE g.id = ?1", Group.class);
			query.setParameter(1, id);
			return query.getSingleResult();

		}
		return null;
	}
}
