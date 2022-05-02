package application;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class MovingPolygon extends Polygon {
	protected Point2D position;
	protected Point2D velocity;
	protected Point2D acceleration;
	protected double heading;
	protected double angularSpeed;
	protected double proximityRadius;
	
	public MovingPolygon() {
		// TESTING CRAP
		this.setCoordinates();
		this.position = new Point2D(100.0,100.0);
		this.velocity = new Point2D(50.0,0.0);
		this.acceleration = new Point2D(0.0,0.0);
		this.heading = 0.0;
		this.angularSpeed = 360.0;
		this.proximityRadius = 0.0;
		this.setFill(Color.RED);
	}
	
	// COORDINATES FOR TESTING PURPOSES ONLY
	protected void setCoordinates() {
		this.getPoints().setAll(
				-10.0,10.0,
				20.0,0.0,
				-10.0,-10.0
				);
	}
	
	public void update(double deltaTime) {
		this.heading = this.heading + this.angularSpeed*deltaTime; // change heading based on angular speed
		this.heading = this.heading %360; // restrict heading from 0 to 360 degrees
		// TODO change acceleration based on heading... extend point2D
		Point2D deltaPos = new Point2D(this.velocity.getX(),this.velocity.getY()); // initialize change in position
		this.velocity = this.velocity.add(this.acceleration.multiply(deltaTime)); // change velocity based on acceleration
		deltaPos = deltaPos.add(this.velocity).multiply(0.5).multiply(deltaTime); // change in position = (final velocity + initial velocity)/2 * delta time
		this.position = this.position.add(deltaPos);
		this.setTranslateX(this.position.getX()); // set translation based on position
		this.setTranslateY(this.position.getY()); // set translation based on position
		this.setRotate(this.heading); // set rotation based on heading
	}
}
