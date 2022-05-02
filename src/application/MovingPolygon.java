package application;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class MovingPolygon extends Polygon {
	protected Point2D position = new Point2D(100.0,100.0);
	protected Point2D velocity = new Point2D(50.0, 0.0);
	protected double heading;
	
	public void update(double dt) {
		this.position = this.position.add(this.velocity.multiply(dt));
		this.setTranslateX(this.position.getX());
		this.setTranslateY(this.position.getY());
	}
}
