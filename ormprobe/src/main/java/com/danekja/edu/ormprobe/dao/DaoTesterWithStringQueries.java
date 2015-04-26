package com.danekja.edu.ormprobe.dao;

import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;
import com.danekja.edu.ormprobe.domain.OwnershipItem;
import com.danekja.edu.ormprobe.domain.OwnershipType;
import java.math.BigInteger;
import java.util.HashSet;

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
            Query iQuery = session.createQuery("SELECT g FROM Group g WHERE g.id NOT IN "
                    + "(SELECT DISTINCT g2.id FROM OwnershipGroup og JOIN og.lower g2 WHERE og.upper.id = ?) AND g.class != ?");
                iQuery.setLong(0, bigGroupId);
                iQuery.setString(1, BigGroup.class.getSimpleName());
                
		Set<Group> groups = new HashSet<>(iQuery.list());
		System.out.println("List size: " + groups.size());
		return groups;
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
            Query iQuery = session.createQuery("SELECT DISTINCT bg FROM BigGroup bg, OwnershipItem oi, OwnershipGroup og "
                    + "WHERE (bg.id = oi.upper.id AND oi.lower.id = ?) OR (bg.id = og.upper.id AND "
                    + "og.lower.id = oi.upper.id AND oi.lower.id = ?)");
            iQuery.setLong(0, itemId);
            iQuery.setLong(1, itemId);
                
		Set<BigGroup> groups = new HashSet<>(iQuery.list());
		System.out.println("List size: " + groups.size());
		return groups;
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
		Query iQuery = session.createQuery("SELECT DISTINCT og FROM BigGroup bg, OwnershipItem oi, OwnershipGroup og WHERE "
                        + "(oi.upper.id = :bgId AND oi.lower.id = :itemId AND oi.upper.class = :bgClass) OR (og.upper.id = :bgId AND og.lower.id = oi.upper.id "
                        + "AND oi.lower.id = :itemId)");
                iQuery.setParameter("bgId", bigGroupId);
		iQuery.setParameter("itemId", itemId);
                iQuery.setParameter("bgClass", BigGroup.class.getSimpleName());
                
		List list = iQuery.list();
		System.out.println("List size: " + list.size());
                
                if(!list.isEmpty()){
                    return true;
                }
                else{
		return false;
                }
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
            Query iQuery = session.createQuery("SELECT DISTINCT i FROM Item i, OwnershipGroup og, OwnershipItem oi WHERE (oi.upper.id = :bgId AND oi.lower.id = i.id AND oi.upper.class = :bgClass) OR (og.upper.id = :bgId AND og.lower.id = oi.upper.id AND oi.lower.id = i.id)");
	        iQuery.setParameter("bgId", bigGroupId);
                iQuery.setParameter("bgClass", BigGroup.class.getSimpleName());
                
		Set<Item> items = new HashSet<>(iQuery.list());
		System.out.println("List size: " + items.size());
		return items;
	}
}