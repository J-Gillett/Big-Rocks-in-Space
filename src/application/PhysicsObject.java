package application;

import java.util.LinkedList;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class PhysicsObject extends Group {
	protected LinkedList<Shape> parts;
	protected Shape hitbox;
	protected Vector2D position;
	protected Vector2D velocity;
	protected double heading;
	protected double angularSpeed;
			
	protected void setHitbox() {
		this.hitbox = Shape.union(new Circle(), new Circle());
		for (int i=0; i<this.parts.size(); i++) {
			this.hitbox = Shape.union(this.hitbox, this.parts.get(i));
		}
		this.hitbox.setFill(Color.TRANSPARENT);
		this.hitbox.setStroke(Color.TRANSPARENT);
		this.getChildren().add(this.hitbox);
	}
	
	public boolean isProximal(Node other) {
		return this.getBoundsInParent().intersects(other.getBoundsInParent());
	}
	
	public boolean hasCollided(PhysicsObject other) {
		Shape intersection = Shape.intersect(this.hitbox, other.hitbox);
		return intersection.getBoundsInLocal().getWidth() != -1;
	}
	
	public void collisionResponse(PhysicsObject other) {
		this.hitbox.setFill(Color.RED);
		this.hitbox.setStroke(Color.RED);
	}
	
	public void update(double deltaTime) {
		this.move(deltaTime);
		this.screenWrap();
	}
	
	protected void move(double deltaTime) {
		this.heading = this.heading + this.angularSpeed*deltaTime; // change heading based on angular speed
		this.heading = this.heading %360; // restrict heading from 0 to 360 degrees
		this.position.add(this.velocity.copy().multiply(deltaTime));
	}
	
	protected void screenWrap() {
		if (this.position.getX() > Main.screenWidth) {
			this.position.setX(this.position.getX() - Main.screenWidth);
		}
		if (this.position.getX() < 0) {
			this.position.setX(this.position.getX() + Main.screenWidth);
		}
		if (this.position.getY() > Main.screenHeight) {
			this.position.setY(this.position.getY()-Main.screenHeight);
		}
		if (this.position.getY() < 0) {
			this.position.setY(this.position.getY()+Main.screenHeight);
		}
	}
	
	public void render() {
		this.setTranslateX(this.position.getX()); // set translation based on position
		this.setTranslateY(this.position.getY()); // set translation based on position
		this.setRotate(this.heading); // set rotation based on heading
	}
}
