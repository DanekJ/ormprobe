package com.danekja.edu.ormprobe.dao;

import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Set;

/**
 * @author Karel Zíbar
 */
public class DaoTester implements TestDao{
	private EntityManagerFactory emf;

	protected EntityManager em;


	public DaoTester(EntityManagerFactory emf) {
		this.emf = emf;
		this.em = emf.createEntityManager();
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
