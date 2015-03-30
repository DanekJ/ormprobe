package com.danekja.edu.ormprobe.dao;

import com.danekja.edu.ormprobe.domain.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
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
		CriteriaQuery<Group> cq = builder.createQuery(Group.class);
		Root group = cq.from(Group.class);
		Root ownershipGroup = cq.from(OwnershipGroup.class);

		cq.select(group);
		cq.where(
			builder.and(
				builder.equal(ownershipGroup.get("upper_id"), bigGroupId),
				builder.equal(ownershipGroup.get("lower_id"), group.get("id"))
			)
		);


		TypedQuery<Group> query = this.em.createQuery(cq);
		List<Group> results = query.getResultList();

		for (int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i));
		}
		return new HashSet<Group>(results);
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

		EntityType<OwnershipItem> OwnershipItem_ = model.entity(OwnershipItem.class);
		CriteriaQuery<OwnershipItem> criteria = builder.createQuery(OwnershipItem.class);
		Root<OwnershipItem> root = criteria.from(OwnershipItem.class);
		criteria.select(root);
//		criteria.where(builder.equal(root.get(OwnershipItem_.upper_id), bigGroupId));

		List<OwnershipItem> results = em.createQuery(criteria).getResultList();

		if(!results.isEmpty()) return true;


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
