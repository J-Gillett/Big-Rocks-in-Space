package application;

import javafx.geometry.Point2D;

public class Vector2D extends Point2D {

	public Vector2D(double arg0, double arg1) {
		super(arg0, arg1);
	}
		
	public Vector2D add(Vector2D point) {
		return new Vector2D(
				super.add(point).getX(),
				super.add(point).getY()
				);
	}
		
	public Vector2D multiply(double factor) {
		Point2D vector = super.multiply(factor);
		return new Vector2D(
				vector.getX(),
				vector.getY()
				);
	}
	
	public Vector2D atAngle(double angle) {
		angle = angle %360;
		angle = Math.toRadians(angle);
		double length = this.magnitude();
		double newX = length * Math.cos(angle);
		double newY = length * Math.sin(angle);
		return new Vector2D(newX,newY);
	}

}
