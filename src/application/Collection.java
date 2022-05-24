package application;

import java.util.LinkedList;

import javafx.scene.Group;

public class Collection extends Group {
	public LinkedList<PhysicsObject> objects = new LinkedList<>();
	
	public void add(PhysicsObject object) {
		this.objects.add(object);
		this.getChildren().add(object);
	}
	
	public void add(Collection collection) {
		for (int i=0; i<collection.size(); i++) {
			this.add(collection.get(i));
		}
	}
	
	public void remove(PhysicsObject object) {
		this.objects.remove(object);
		this.getChildren().remove(object);
	}
	
	public void remove(int i) {
		this.objects.remove(i);
		this.getChildren().remove(i);
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
