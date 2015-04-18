package com.danekja.edu.ormprobe.dao;

import com.danekja.edu.ormprobe.domain.BigGroup;
import com.danekja.edu.ormprobe.domain.Group;
import com.danekja.edu.ormprobe.domain.Item;

import java.util.Set;

/**
 * DAO interface with to-be-tested queries. The following 
 * metrics are to be monitored:
 * 1. Query implementation difficulty (subjective)
 * 2. Query optimization options (with results - see next item)
 * 3. Performance based on amount of data
 * 		a. Try various amounts of data.
 *
 * Use Profiler: XRebel (www.zeroturnaround.com) - paid, 2 week eval license.
 * Use Profiler: JMeter, VisualVM - free
 */
public interface TestDao {
	
	/**
	 * Returns list of all groups which are not joined to the BigGroup yet.
	 * @param bigGroupId PK of the big group
	 */
	Set<Group> listOwnershipCandidates(long bigGroupId);

	/**
	 * Returns list of all BigGroups the item is somehow attached to.
	 * There are two options items maybe connected to a BigGroup:
	 * 1. Directly - OwnershipItem relationship between the BigGroup and Item
	 * 2. Indirectly - OwnershipItem relationship between the Item and a Group with
	 *                 OwnershipGroup relationship the the BigGroup
	 * @param itemId PK of the item
	 */
	Set<BigGroup> listItemsBigGroups(long itemId);

	/**
	 * Returns true if there is an existing connection between the Item and the BigGroup
	 * There are two options items maybe connected to a BigGroup:
	 * 1. Directly - OwnershipItem relationship between the BigGroup and Item
	 * 2. Indirectly - OwnershipItem relationship between the Item and a Group with
	 *                 OwnershipGroup relationship the the BigGroup
	 * @param bigGroupId PK of the BigGroup
	 * @param itemId PK of the item
	 */
	boolean isConnectedToBigGroup(long bigGroupId, long itemId);

	/**
	 * Returns set of all items connected to the BigGroup.
	 * There are two options items maybe connected to a BigGroup:
	 * 1. Directly - OwnershipItem relationship between the BigGroup and Item
	 * 2. Indirectly - OwnershipItem relationship between the Item and a Group with
	 *                 OwnershipGroup relationship the the BigGroup
	 * @param bigGroupId PK of the BigGroup
	 */
	Set<Item> listBigGroupsItems(long bigGroupId);
}