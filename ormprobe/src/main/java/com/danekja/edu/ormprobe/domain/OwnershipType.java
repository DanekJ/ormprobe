package com.danekja.edu.ormprobe.domain;

/**
 * @author Jakub
 * @version 1.0
 * @created 20-II-2015 18:10:42
 */
public enum OwnershipType {
	GROUP(Group.class),
	ITEM(Item.class);

	private Class clazz;

	private OwnershipType(Class clazz) {
		this.clazz = clazz;
	}

	public Class getClazz() {
		return this.clazz;
	}
}