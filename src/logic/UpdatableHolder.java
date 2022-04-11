package logic;

import gui.Updatable;

public class UpdatableHolder {

	private static UpdatableHolder instance = null;
	Updatable[] entities;

	public UpdatableHolder(Updatable[] entities) {
		this.entities = entities;
	}

	public static UpdatableHolder getInstance() {
		return instance;
	}
	
	public static void createInstance(Updatable[] entities) {
		if (instance == null) 
			instance = new UpdatableHolder(entities);
	}

	public void updateScreen() {
		for (Updatable item : entities) {
			item.update();
		}
	}
}
