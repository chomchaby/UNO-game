package logic;

import java.util.ArrayList;

public class UpdatableHolder {

	private static UpdatableHolder instance = null;
	private ArrayList<Updatable> entities;

	private UpdatableHolder() {
//  	// anonymous inner class 
//		Updatable updatableInstance = new Updatable() {
//			public void update() {
//				System.out.println("loading");
//			}
//		};
		entities = new ArrayList<Updatable>();
//		entities.add(updatableInstance);
	}

	public static UpdatableHolder getInstance() {
		if (instance == null) {
			instance = new UpdatableHolder();
		}
		return instance;
	}

	public void setEntities(ArrayList<Updatable> entities) {
		this.entities = entities;
	}

	public void updateScreen() {
		for (Updatable item : entities) {
			item.update();
		}
	}
}
