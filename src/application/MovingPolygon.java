package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class MovingPolygon extends Polygon {
	protected ExtendedPoint2D position;
	protected ExtendedPoint2D velocity;
	protected ExtendedPoint2D acceleration;
	protected double heading;
	protected double angularSpeed;
	protected double proximityRadius;
	
	public MovingPolygon() {
		// TESTING CRAP
		this.setPoints();
		this.position = new ExtendedPoint2D(Main.screenWidth/2,Main.screenHeight/2-50.0);
		this.velocity = new ExtendedPoint2D(0.0,0.0);
		this.acceleration = new ExtendedPoint2D(100.0,0.0);
		this.heading = 270.0;
		this.angularSpeed = 0.0;
		this.proximityRadius = 0.0;
		this.setFill(Color.WHITE);
		this.setStroke(Color.YELLOW);
		this.setStrokeWidth(2.0);
	}
	
	// COORDINATES FOR TESTING PURPOSES ONLY
	protected void setPoints() {
		this.getPoints().setAll(
				-10.0,10.0,
				20.0,0.0,
				-10.0,-10.0
				);
	}
	
	public void update(double deltaTime) {
		// Updating position
		this.heading = this.heading + this.angularSpeed*deltaTime; // change heading based on angular speed
		this.heading = this.heading %360; // restrict heading from 0 to 360 degrees
		this.acceleration = this.acceleration.atAngle(this.heading);
		ExtendedPoint2D deltaPos = new ExtendedPoint2D(this.velocity.getX(),this.velocity.getY()).multiply(deltaTime); // displacement = initial velocity * time + ...
		this.velocity = this.velocity.add(this.acceleration.multiply(deltaTime)); // change velocity based on acceleration
		deltaPos = deltaPos.add(this.acceleration.multiply(0.5).multiply(deltaTime*deltaTime)); // displacement = ... + 0.5 acceleration * time^2
		this.position = this.position.add(deltaPos); // update position
		// Screen Wrapping
		if (this.position.getX() > Main.screenWidth) {
			this.position = new ExtendedPoint2D(this.position.getX()-Main.screenWidth,this.position.getY());
		}
		if (this.position.getX() < 0) {
			this.position = new ExtendedPoint2D(this.position.getX()+Main.screenWidth,this.position.getY());
		}
		if (this.position.getY() > Main.screenHeight) {
			this.position = new ExtendedPoint2D(this.position.getX(),this.position.getY()-Main.screenHeight);
		}
		if (this.position.getY() < 0) {
			this.position = new ExtendedPoint2D(this.position.getX(),this.position.getY()+Main.screenHeight);
		}
		// Setting rendering position to position
		this.setTranslateX(this.position.getX()); // set translation based on position
		this.setTranslateY(this.position.getY()); // set translation based on position
		this.setRotate(this.heading); // set rotation based on heading
	}
}
