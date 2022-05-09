package application;

import javafx.geometry.Point2D;

/* A mutable version of Point2D with some minor additional functionality.
 * Don't have equivalent methods to angle() dotProduct() midpoint() or crossProduct() */

public class Vector2D {
	private double x;
	private double y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Point2D point) {
		this.x = point.getX();
		this.y = point.getY();
	}
	
	public Vector2D(Vector2D vector) {
		this.x = vector.getX();
		this.y = vector.getY();
	}
	
	public Vector2D add(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector2D add(Point2D point) {
		this.x += point.getX();
		this.y += point.getY();
		return this;
	}

	public Vector2D add(Vector2D vector) {
		this.x += vector.getX();
		this.y += vector.getY();
		return this;
	}

	public Vector2D subtract(double x, double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public Vector2D subtract(Point2D point) {
		this.x -= point.getX();
		this.y -= point.getY();
		return this;
	}
	
	public Vector2D subtract(Vector2D vector) {
		this.x -= vector.getX();
		this.y -= vector.getY();
		return this;
	}
	
	public Vector2D multiply(double factor) {
		this.x *= factor;
		this.y *= factor;
		return this;
	}
	
	public Vector2D divide(double factor) {
		this.x /= factor;
		this.y /= factor;
		return this;
	}

	public double getMagnitude() {
		return Math.sqrt( Math.pow(this.x, 2) + Math.pow(this.y, 2) );
	}

	public Vector2D normalize() {
		this.divide(this.getMagnitude());
		return this;
	}
	
	public Vector2D setMagnitude(double length) {
		this.normalize();
		this.multiply(length);
		return this;
	}
	
	public double getAngle() {
		return Math.atan(this.y/this.x);
	}
	
	public Vector2D setAngle(double theta) {
		theta = Math.toRadians(theta % 360);
		double magnitude = this.getMagnitude();
		this.x = Math.cos(theta) * magnitude;
		this.y = Math.sin(theta) * magnitude;
		return this;
	}

	public double getX() {
		return this.x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return this.y;
	}
	public void setY(double y) {
		this.y = y;
	}
}
