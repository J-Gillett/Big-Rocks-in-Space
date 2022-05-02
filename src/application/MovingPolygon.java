package application;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class MovingPolygon extends Polygon {
	protected Point2D position;
	protected Point2D velocity;
	protected Point2D acceleration;
	protected double heading;
	protected double angularMomentum;
	protected double proximityRadius;
	
	public MovingPolygon() {
		// TESTING CRAP
		this.setCoordinates();
		this.position = new Point2D(100.0,100.0);
		this.velocity = new Point2D(50.0,0.0);
		this.acceleration = new Point2D(0.0,0.0);
		this.heading = 0.0;
		this.angularMomentum = 0.0;
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
	
	public void update(double dt) {
		this.heading = this.heading + this.angularMomentum*dt; // change heading based on angular momentum
		this.heading = this.heading %360; // restrict heading from 0 to 360 degrees
		// TODO change acceleration based on heading
		// TODO change velocity based on acceleration
		this.position = this.position.add(this.velocity.multiply(dt)); // TODO take into account acceleration
		this.setTranslateX(this.position.getX());
		this.setTranslateY(this.position.getY());
		this.setRotate(this.heading);
	}
}
