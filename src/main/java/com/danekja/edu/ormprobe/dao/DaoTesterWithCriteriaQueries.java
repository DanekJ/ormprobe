package com.danekja.edu.ormprobe.dao;

import com.danekja.edu.ormprobe.domain.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Karel Zíbar
 */
public class DaoTesterWithCriteriaQueries extends DaoTester{
	CriteriaBuilder builder;


	public DaoTesterWithCriteriaQueries(EntityManagerFactory emf) {
		super(emf);
		builder = this.em.getCriteriaBuilder();
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
		subQuery.distinct(true);

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
		CriteriaQuery<BigGroup> query = builder.createQuery(BigGroup.class);
		Root<BigGroup> fromBG = query.from(BigGroup.class);
		Root<OwnershipItem> fromOI = query.from(OwnershipItem.class);
		Root<OwnershipGroup> fromOG = query.from(OwnershipGroup.class);

		CriteriaQuery<BigGroup> select = query.select(fromBG);
		select.where(
			builder.or(
				builder.and(
					builder.equal(fromBG.get("id"), fromOG.get("upper").get("id")),
					builder.equal(fromOG.get("lower").get("id"), fromOI.get("upper").get("id")),
					builder.equal(fromOI.get("lower").get("id"), itemId)
				),
				builder.and(
					builder.equal(fromOI.get("lower").get("id"), itemId),
					builder.equal(fromBG.get("id"), fromOI.get("upper").get("id"))
				)
			)
		);

		TypedQuery<BigGroup> typedQuery = em.createQuery(select);
		List<BigGroup> resultList = typedQuery.getResultList();
		return new HashSet<BigGroup>(resultList);
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
		CriteriaQuery<BigGroup> query = builder.createQuery(BigGroup.class);
		Root<BigGroup> fromBG = query.from(BigGroup.class);
		Root<OwnershipItem> fromOI = query.from(OwnershipItem.class);
		Root<OwnershipGroup> fromOG = query.from(OwnershipGroup.class);

		CriteriaQuery<BigGroup> select = query.select(fromBG);
		select.where(
			builder.or(
				builder.and(
					builder.equal(fromOG.get("upper").get("id"), bigGroupId),
					builder.equal(fromOG.get("lower").get("id"), fromOI.get("upper").get("id")),
					builder.equal(fromOI.get("lower").get("id"), itemId)
				),
				builder.and(
					builder.equal(fromOI.get("upper").get("id"), bigGroupId),
					builder.equal(fromOI.get("lower").get("id"), itemId)
				)
			)
		);

		TypedQuery<BigGroup> typedQuery = em.createQuery(select);
		List<BigGroup> resultList = typedQuery.getResultList();
		return !resultList.isEmpty();
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
		CriteriaQuery<Item> query = builder.createQuery(Item.class);
		Root<Item> fromI = query.from(Item.class);
		Root<OwnershipItem> fromOI = query.from(OwnershipItem.class);
		Root<OwnershipGroup> fromOG = query.from(OwnershipGroup.class);

		CriteriaQuery<Item> select = query.select(fromI).distinct(true);
		select.where(
			builder.or(
				builder.and(
					builder.equal(fromI.get("id"), fromOI.get("lower").get("id")),
					builder.equal(fromOG.get("lower").get("id"), fromOI.get("upper").get("id")),
					builder.equal(fromOG.get("upper").get("id"), bigGroupId)
				),
				builder.and(
					builder.equal(fromOI.get("lower").get("id"), fromI.get("id")),
					builder.equal(fromOI.get("upper").get("id"), bigGroupId)
				)
			)
		);

		TypedQuery<Item> typedQuery = em.createQuery(select);
		List<Item> resultList = typedQuery.getResultList();
		return new HashSet<Item>(resultList);
	}
}
