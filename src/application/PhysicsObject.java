package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class PhysicsObject extends Group {
	//TODO add Parts array
	protected Shape hitbox;
	protected Vector2D position;
	protected Vector2D velocity;
	protected Vector2D acceleration;
	protected double heading;
	protected double angularSpeed;
	protected double proximityRadius;
	
	public PhysicsObject() {
		this.addParts();
		this.setHitbox();
		this.position = new Vector2D(Main.screenWidth/2,Main.screenHeight/2-50.0);
		this.velocity = new Vector2D(0.0,0.0);
		this.acceleration = new Vector2D(100.0,0.0);
		this.heading = 270.0;
		this.angularSpeed = 0.0;
		this.proximityRadius = 0.0;
	}
	
	private void addParts() {
		Polygon body = new Polygon();
		body.getPoints().setAll(Outline.testTriangle());
		body.setFill(Color.WHITE);
		body.setStroke(Color.YELLOW);
		body.setStrokeWidth(2.0);
		Circle test = new Circle(5.0);
		test.setTranslateX(10.0);
		test.setTranslateY(10.0);
		test.setFill(Color.WHITE);
		test.setStroke(Color.PINK);
		test.setStrokeWidth(2.0);
		// TODO add parts to parts array
		this.getChildren().addAll(body,test);
	}
	
	private void setHitbox() {
		// TODO loop through parts array setting the hitbox as a union of the parts
	}
	
	public void update(double deltaTime) {
		// Updating position
		this.heading = this.heading + this.angularSpeed*deltaTime; // change heading based on angular speed
		this.heading = this.heading %360; // restrict heading from 0 to 360 degrees
		this.acceleration = this.acceleration.setAngle(this.heading);
		Vector2D deltaPos = Vector2D.copy(this.velocity).multiply(deltaTime); // displacement = initial velocity * time + ...
		this.velocity.add(this.acceleration.copy().multiply(deltaTime)); // final velocity is initial velocity + acceleration * time
		deltaPos = deltaPos.add(this.acceleration.copy().multiply(deltaTime).multiply(0.5)); // displacement = ... + 0.5 acceleration * time
		this.position.add(deltaPos); // update position
		// Screen Wrapping
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
		// Setting rendering position to position
		this.setTranslateX(this.position.getX()); // set translation based on position
		this.setTranslateY(this.position.getY()); // set translation based on position
		this.setRotate(this.heading); // set rotation based on heading
	}
}
