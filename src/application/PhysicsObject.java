package application;

import java.util.LinkedList;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

public class PhysicsObject extends Group implements Collider {
	protected LinkedList<Shape> parts;
	protected Shape hitbox;
	protected Vector2D position;
	protected Vector2D velocity;
	protected double heading;
	protected double angularSpeed;
	protected double proximityRadius;
	
	public PhysicsObject() {
		this.addParts();
		this.setHitbox();
		this.position = new Vector2D(Main.screenWidth/2,Main.screenHeight/2-50.0);
		this.velocity = new Vector2D(100+Math.random()*200,0.0);
		this.heading = Math.random()*360;
		this.velocity.setAngle(this.heading);
		this.angularSpeed = 90.0;
		this.proximityRadius = 0.0;
		this.render();
	}
	
	protected void addParts() {
		this.parts = BitsBox.testShape();
		for (int i=0; i<this.parts.size(); i++) {
			this.getChildren().add(this.parts.get(i));
		}
	}
	
	protected void setHitbox() {
		// TODO loop through parts array setting the hitbox as a union of the parts
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
