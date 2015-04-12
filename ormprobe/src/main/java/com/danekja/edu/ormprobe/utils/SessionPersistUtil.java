package com.danekja.edu.ormprobe.utils;

import com.danekja.edu.ormprobe.domain.BaseObject;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * @author Karel Zíbar
 */
public class SessionPersistUtil implements DatabasePersistUtil {
        private SessionFactory factory;
        private Session session;
        private Transaction t;

	public SessionPersistUtil(SessionFactory factory) {
            this.factory = factory;  
        }


	/**
	 * Method do all stuffs needed for upcoming database inserts
	 */
	public void beginTransaction() {
            session=factory.openSession();
            t=session.beginTransaction();  
	}

	/**
	 * Method will persist data to database
	 *
	 * @param object
	 * 		- persisting record
	 */
	public void persistData(BaseObject object) {
		session.persist(object);
	}


	/**
	 * Method will commit all changes to database
	 */
	public void commitChanges(){
		t.commit();
	}



	/**
	 * Method will end all stuffs which were needed for transaction and the database connection will be end too
	 */
	public void endConnection() {
		session.close();
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
			return (BaseObject)session.get(Item.class, id);

		}else if(clazz.equals(Group.class)){
                        return (BaseObject)session.get(Group.class, id);
		}
                else{
                        return null;
                }
	}
}