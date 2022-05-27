package application;

import java.util.LinkedList;

public class QuadTree {
	private double x;
	private double y;
	private double width;
	private double height;
	private int capacity = 4;
	private LinkedList<PhysicsObject> objects;
	private LinkedList<QuadTree> children;
	
	public QuadTree(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.objects = new LinkedList<>();
	}
	
	private void subdivide() {
		this.children = new LinkedList<>();
		for (int i=0; i<2; i++) {
			for (int j=0; j<2; j++) {
				double newX = this.x + i*(this.width/2);
				double newY = this.y + j*(this.height/2);
				double newWidth = this.width/2;
				double newHeight = this.height/2;
				this.children.add(new QuadTree(newX,newY,newWidth,newHeight));
			}
		}
	}
	
	private boolean contains(PhysicsObject object) {
		return (object.position.getX() >= this.x &&
				object.position.getX() <= (this.x + this.width) && 
				object.position.getY() >= this.y &&
				object.position.getY() <= (this.y + this.height)
				);
	}
	
	private boolean intersects(Vector2D location, double radius) {
		return !(
				(location.getX() + radius) < this.x ||
				(location.getY() + radius) < this.y ||
				(location.getX() - radius) > (this.x + this.width) ||
				(location.getY() - radius) > (this.y + this.height)
				);
	}
	
	public void insert(PhysicsObject object) {
		if (!this.contains(object)) {
			return;
		}
		if (this.objects.size() < this.capacity) {
			this.objects.add(object);
		} else {
			if (this.children == null) {
				this.subdivide();
			}
			for (int i=0; i<this.children.size(); i++) {
				if (this.children.get(i).contains(object)) {
					this.children.get(i).insert(object);
					return;
				}
			}
		}
	}


	public LinkedList<PhysicsObject> query(Vector2D location, double radius) {
		LinkedList<PhysicsObject> foundObjects = new LinkedList<>();
		if (!this.intersects(location, radius)) {
			return foundObjects;
		} else {
			for (int i=0; i<this.objects.size(); i++) {
				Vector2D objectLocation = this.objects.get(i).position;
				if (
						objectLocation.getX() > (location.getX() - radius) &&
						objectLocation.getX() < (location.getX() + radius) &&
						objectLocation.getY() > (location.getY() - radius) &&
						objectLocation.getY() < (location.getY() + radius)
						) {
					foundObjects.add( this.objects.get(i) );
				}
			}
			
			if (!(this.children == null)) {
				for (int i=0; i<this.children.size(); i++) {
					foundObjects.addAll(this.children.get(i).query(location, radius));
				}
			}
			
			return foundObjects;
		}
	}
	
}
