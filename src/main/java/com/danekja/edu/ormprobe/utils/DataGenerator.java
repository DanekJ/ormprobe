package com.danekja.edu.ormprobe.utils;

import com.danekja.edu.ormprobe.domain.*;

import java.util.Random;

/**
 * @author Karel Zíbar
 */
public class DataGenerator {
	public static final int GROUPS_COUNT = 10;

	public static final int ITEMS_COUNT = 10;

	public static final int OWNERSHIPS_COUNT = 40;

	private DatabasePersistUtil persistUtil;



	public DataGenerator(DatabasePersistUtil persistUtil) {

		this.persistUtil = persistUtil;

	}



	public void generateData(boolean endConnection){

			persistUtil.beginTransaction();

			generateGroupsAndItems();
			generateOwnerships();
			persistUtil.commitChanges();

		if(endConnection){
			persistUtil.endConnection();
		}
	}

	private void generateGroupsAndItems(){
		Random random = new Random(1);
		int randomChoise;

		for(int i = 0; i < GROUPS_COUNT; i++){

			randomChoise = random.nextInt(2) + 1;
			if(randomChoise == 1){
				Group group = new Group();
				group.setName("group" + i);
				persistUtil.persistData(group);
			}
			else{
				BigGroup bigGroup = new BigGroup();
				bigGroup.setName("bigGroup" + i);
				persistUtil.persistData(bigGroup);
			}
		}

		for(int j = 0; j < ITEMS_COUNT; j++){
			Item item = new Item();
			item.setName("item" + j);
			persistUtil.persistData(item);
		}
	}



	private void generateOwnerships(){
		Random roitems = new Random(1);
		Group g = new Group();
		Item i = new Item();
		Long randomId;
		int randomChoice;

		for(int k = 0; k < OWNERSHIPS_COUNT; k++){
			randomChoice = roitems.nextInt(2) + 1;

			if(randomChoice == 1){
				OwnershipItem oi = new OwnershipItem();

				randomId = (long) roitems.nextInt(GROUPS_COUNT - 1 + 1) + 1;
				g = (Group) persistUtil.selectObjectById(Group.class, randomId);
				oi.setUpper(g);

				randomId = (long) roitems.nextInt(ITEMS_COUNT - 1 + 1) + 1;
				i = (Item) persistUtil.selectObjectById(Item.class, randomId);
				oi.setLower(i);

				persistUtil.persistData(oi);
			}

			else{
				OwnershipGroup og = new OwnershipGroup();

				randomId = (long) roitems.nextInt(GROUPS_COUNT - 1 + 1) + 1;
				g = (Group) persistUtil.selectObjectById(Group.class, randomId);

				if(g instanceof BigGroup){
					og.setUpper(g);

					randomId = (long) roitems.nextInt(GROUPS_COUNT - 1 + 1) + 1;
					g = (Group) persistUtil.selectObjectById(Group.class, randomId);

					while(g instanceof BigGroup){
						randomId = (long) roitems.nextInt(GROUPS_COUNT - 1 + 1) + 1;
						g = (Group) persistUtil.selectObjectById(Group.class, randomId);
					}

					og.setLower(g);
					persistUtil.persistData(og);
				}

				else{
					og.setLower(g);

					randomId = (long) roitems.nextInt(GROUPS_COUNT - 1 + 1) + 1;
					g = (Group) persistUtil.selectObjectById(Group.class, randomId);

					while(!(g instanceof BigGroup)){
						randomId = (long) roitems.nextInt(GROUPS_COUNT - 1 + 1) + 1;
						g = (Group) persistUtil.selectObjectById(Group.class, randomId);
					}

					og.setUpper(g);
					persistUtil.persistData(og);
				}
			}
		}
	}


	public DatabasePersistUtil getPersistUtil() {
		return persistUtil;
	}
}