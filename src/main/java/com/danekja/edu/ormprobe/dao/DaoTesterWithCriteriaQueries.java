package com.danekja.edu.ormprobe.dao;

import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;
import com.danekja.edu.ormprobe.domain.OwnershipGroup;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Metamodel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Karel Zíbar
 */
public class DaoTesterWithCriteriaQueries extends DaoTester{
	CriteriaBuilder builder;
	Metamodel model;


	public DaoTesterWithCriteriaQueries(EntityManagerFactory emf) {
		super(emf);
		builder = this.em.getCriteriaBuilder();
		model = this.em.getMetamodel();
	}


	/**
	 * Returns list of all groups which are not joined to the BigGroup yet.
	 *
	 * @param bigGroupId
	 * 		PK of the big group
	 */
	@Override
	public Set<Group> listOwnershipCandidates(long bigGroupId) {
		CriteriaQuery<Group> query = builder.createQuery(Group.class);
		Root<Group> from = query.from(Group.class);
		Path path = from.get("id");
		CriteriaQuery<Group> select = query.select(from);

		Subquery<OwnershipGroup> subQuery = query.subquery(OwnershipGroup.class);
		Root<OwnershipGroup> subFrom = subQuery.from(OwnershipGroup.class);
		subQuery.select(subFrom.get("lower").<OwnershipGroup>get("id"));
		subQuery.where(
				builder.equal(subFrom.get("upper").get("id"), bigGroupId)
		);

		Subquery<BigGroup> subQuery2 = query.subquery(BigGroup.class);
		Root<BigGroup> subFrom2 = subQuery2.from(BigGroup.class);
		subQuery2.select(subFrom2.<BigGroup>get("id"));

		select.where(
			builder.not(
				builder.in(path).value(subQuery)
			),
			builder.not(
				builder.in(path).value(subQuery2)
			)
		);

		TypedQuery<Group> typedQuery = em.createQuery(select);
		List<Group> resultList = typedQuery.getResultList();
		return new HashSet<Group>(resultList);
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
		return false;
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
