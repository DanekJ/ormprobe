package com.danekja.edu.ormprobe.dao;

import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;
import com.danekja.edu.ormprobe.domain.OwnershipItem;
import com.danekja.edu.ormprobe.domain.OwnershipType;
import java.math.BigInteger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Karel Zíbar
 */
public class DaoTesterWithStringQueries extends DaoTester{

	public DaoTesterWithStringQueries(SessionFactory factory) {
		super(factory);
	}


	/**
	 * Returns list of all groups which are not joined to the BigGroup yet.
	 *
	 * @param bigGroupId
	 * 		PK of the big group
	 */
	@Override
	public Set<Group> listOwnershipCandidates(long bigGroupId) {
            Query iQuery = session.createQuery("SELECT DISTINCT g FROM Group g, OwnershipGroup og WHERE og.upper.id = ? AND g.id != og.lower.id AND og.ownershipType = ?");
                iQuery.setLong(0, bigGroupId);
                iQuery.setParameter(1, OwnershipType.GROUP);
                
		List<Group> groups = iQuery.list();
		System.out.println("List size: " + groups.size());
		return null;
	}

	/**
	 * Returns list of all BigGroups the item is somehow attached to. There are two options items maybe connected to a
	 * BigGroup: 1. Directly - OwnershipItem relationship between the BigGroup and Item 2. Indirectly - OwnershipItem
	 * relationship between the Item and a Group with OwnershipGroup relationship the the BigGroup
	 *
	 * @param itemId
	 * 		PK of the item
	 */
	@Override
	public Set<BigGroup> listItemsBigGroups(long itemId) {
		return null;
	}

	/**
	 * Returns true if there is an existing connection between the Item and the BigGroup There are two options items maybe
	 * connected to a BigGroup: 1. Directly - OwnershipItem relationship between the BigGroup and Item 2. Indirectly -
	 * OwnershipItem relationship between the Item and a Group with OwnershipGroup relationship the the BigGroup
	 *
	 * @param bigGroupId
	 * 		PK of the BigGroup
	 * @param itemId
	 * 		PK of the item
	 */
	@Override
	public boolean isConnectedToBigGroup(long bigGroupId, long itemId) {
		boolean ret = false;
		Query iQuery = session.createQuery("SELECT oi.id FROM OwnershipItem oi where oi.upper = ? AND oi.lower = ?");
                iQuery.setLong(0, bigGroupId);
		iQuery.setLong(1, itemId);
                
		List list = iQuery.list();
		System.out.println("List size: " + list.size());

		return ret;
	}

	/**
	 * Returns set of all items connected to the BigGroup. There are two options items maybe connected to a BigGroup: 1.
	 * Directly - OwnershipItem relationship between the BigGroup and Item 2. Indirectly - OwnershipItem relationship
	 * between the Item and a Group with OwnershipGroup relationship the the BigGroup
	 *
	 * @param bigGroupId
	 * 		PK of the BigGroup
	 */
	@Override
	public Set<Item> listBigGroupsItems(long bigGroupId) {
		return null;
	}
}