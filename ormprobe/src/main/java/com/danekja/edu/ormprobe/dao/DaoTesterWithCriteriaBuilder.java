package com.danekja.edu.ormprobe.dao;

import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;
import com.danekja.edu.ormprobe.domain.OwnershipGroup;
import com.danekja.edu.ormprobe.domain.OwnershipItem;
import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

/**
 * @author Karel Zíbar
 */
public class DaoTesterWithCriteriaBuilder extends DaoTester{

	public DaoTesterWithCriteriaBuilder(SessionFactory factory) {
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
            DetachedCriteria inOg = DetachedCriteria.forClass(OwnershipGroup.class);
            inOg.add(Restrictions.eq("upper.id", bigGroupId));
            inOg.setProjection(Projections.property("lower.id"));
            
            Criteria crit = session.createCriteria(Group.class, "Group");
            crit.add(Subqueries.propertyNotIn("id", inOg));
            crit.add(Restrictions.ne("Group.class", BigGroup.class.getSimpleName()));
            
                List cats = crit.list();
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
            DetachedCriteria inOi = DetachedCriteria.forClass(OwnershipItem.class);
            inOi.add(Restrictions.eq("lower.id", itemId));
            inOi.setProjection(Projections.property("upper.id"));
            
            DetachedCriteria inOg = DetachedCriteria.forClass(OwnershipGroup.class);
            inOg.add(Subqueries.propertyIn("lower.id", inOi));
            inOg.setProjection(Projections.property("upper.id"));
            
            DetachedCriteria inOi1 = DetachedCriteria.forClass(OwnershipItem.class);
            inOi1.add(Restrictions.eq("lower.id", itemId));
            inOi1.setProjection(Projections.property("upper.id"));
            
            Criteria c = session.createCriteria(BigGroup.class);
            Criterion s1 = Subqueries.propertyIn("id", inOi1);
            Criterion s2 = Subqueries.propertyIn("id", inOg);
            
            c.add(Restrictions.or(s1, s2));
            List<BigGroup> bgl = c.list();
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
            DetachedCriteria inOg = DetachedCriteria.forClass(OwnershipGroup.class);
            inOg.add(Restrictions.eq("upper.id", bigGroupId));
            inOg.setProjection(Projections.property("lower.id"));
            
            Criteria c = session.createCriteria(OwnershipItem.class);
            Criterion or = (Restrictions.or(Subqueries.propertyIn("id", inOg), Restrictions.eq("upper.id", bigGroupId)));
            c.add(Restrictions.and(or, Restrictions.eq("lower.id", itemId)));
            
            if(c.list().size() == 0){
                return false;
            }
            else{
                return true;
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
            DetachedCriteria inOg = DetachedCriteria.forClass(OwnershipGroup.class);
            inOg.add(Restrictions.eq("upper.id", bigGroupId));
            inOg.setProjection(Projections.property("lower.id"));
            
            DetachedCriteria crit = DetachedCriteria.forClass(OwnershipItem.class);
            crit.add(Restrictions.or(Subqueries.propertyIn("upper.id", inOg), Restrictions.eq("upper.id", bigGroupId)));
            crit.setProjection(Projections.property("lower.id"));
            
            Criteria c = session.createCriteria(Item.class);
            c.add(Subqueries.propertyIn("id", crit));
            
            List list = c.list();
		return null;
	}
}