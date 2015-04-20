package com.danekja.edu.ormprobe.dao;

import com.danekja.edu.ormprobe.domain.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Karel Zíbar
 */
public class DaoTesterWithHQL extends DaoTester{



	public DaoTesterWithHQL(EntityManagerFactory emf) {
		super(emf);
	}


	/**
	 * Returns list of all groups which are not joined to the BigGroup yet.
	 *
	 * @param bigGroupId
	 * 		PK of the big group
	 */
	@Override
	public Set<Group> listOwnershipCandidates(long bigGroupId) {

		TypedQuery<Group> query = em.createQuery(
			"SELECT g FROM Group g WHERE " +
				"g.id NOT IN (SELECT DISTINCT g2.id FROM OwnershipGroup og JOIN og.lower g2 WHERE og.upper.id = ?1)" +
				" AND g.class != ?2", Group.class);

		query.setParameter(1, bigGroupId);
		query.setParameter(2, BigGroup.class.getSimpleName());

		List<Group> groups = query.getResultList();
		return new HashSet<Group>(groups);
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
		TypedQuery<BigGroup> query = em.createQuery(
			"SELECT DISTINCT bg FROM BigGroup bg, OwnershipItem oi, OwnershipGroup og " +
				"WHERE (bg.id = oi.upper.id AND oi.lower.id = :itemId) OR" +
				"(bg.id = og.upper.id AND og.lower.id = oi.upper.id AND oi.lower.id = :itemId)", BigGroup.class);

		query.setParameter("itemId", itemId);

		List<BigGroup> bigGroups = query.getResultList();
		return new HashSet<BigGroup>(bigGroups);
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
		TypedQuery<Ownership> gQuery = em.createQuery(
			"SELECT DISTINCT og FROM BigGroup bg, OwnershipItem oi, OwnershipGroup og " +
				"WHERE (oi.upper.id = :bgId AND oi.lower.id = :itemId) OR " +
					"(og.upper.id = :bgId AND og.lower.id = oi.upper.id AND oi.lower.id = :itemId)", Ownership.class);
		gQuery.setParameter("bgId", bigGroupId);
		gQuery.setParameter("itemId", itemId);

		List<Ownership> ogList = gQuery.getResultList();
		return !ogList.isEmpty();
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
		TypedQuery<Item> query = em.createQuery(
			"SELECT DISTINCT i FROM Item i, OwnershipGroup og, OwnershipItem oi " +
				"WHERE (oi.upper.id = :bgId AND oi.lower.id = i.id) OR " +
					"(og.upper.id = :bgId AND og.lower.id = oi.upper.id AND oi.lower.id = i.id) ", Item.class);

		query.setParameter("bgId", bigGroupId);

		List<Item> items = query.getResultList();
		return new HashSet<Item>(items);
	}
}
