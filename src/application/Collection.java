package application;

import java.util.LinkedList;

import javafx.scene.Group;

public class Collection extends Group {
	public LinkedList<PhysicsObject> objects = new LinkedList<>();
	
	public void add(PhysicsObject object) {
		objects.add(object);
		this.getChildren().add(object);
	}
	
	public void remove(PhysicsObject object) {
		objects.remove(object);
		this.getChildren().remove(object);
	}
	
	public PhysicsObject get(int i) {
		return this.objects.get(i);
	}
	
	public LinkedList<PhysicsObject> children() {
		return this.objects;
	}
	
	public int size() {
		return this.objects.size();
	}
}
