package application;

import javafx.geometry.Point2D;

public class ExtendedPoint2D extends Point2D {

	public ExtendedPoint2D(double arg0, double arg1) {
		super(arg0, arg1);
	}
		
	public ExtendedPoint2D add(ExtendedPoint2D point) {
		return new ExtendedPoint2D(
				super.add(point).getX(),
				super.add(point).getY()
				);
	}
		
	public ExtendedPoint2D multiply(double factor) {
		Point2D vector = super.multiply(factor);
		return new ExtendedPoint2D(
				vector.getX(),
				vector.getY()
				);
	}
	
	public ExtendedPoint2D atAngle(double angle) {
		angle = angle %360;
		angle = Math.toRadians(angle);
		double length = this.magnitude();
		double newX = length * Math.cos(angle);
		double newY = length * Math.sin(angle);
		return new ExtendedPoint2D(newX,newY);
	}

}
