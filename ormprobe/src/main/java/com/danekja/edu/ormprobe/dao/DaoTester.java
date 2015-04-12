package com.danekja.edu.ormprobe.dao;

import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Karel Zíbar
 */
public class DaoTester implements TestDao{
        private SessionFactory factory;
	protected Session session;


	public DaoTester(SessionFactory factory) {
		this.factory = factory;
                session = factory.openSession();
	}

	
	public Set<Group> listOwnershipCandidates(long bigGroupId) {
		return null;
	}

	public Set<BigGroup> listItemsBigGroups(long itemId) {
		return null;
	}

	public boolean isConnectedToBigGroup(long bigGroupId, long itemId) {
		return false;
	}

	public Set<Item> listBigGroupsItems(long bigGroupId) {
		return null;
	}
}