package application;

import javafx.geometry.Point2D;

/* A mutable version of Point2D with some minor additional functionality.
 * Don't have equivalent methods to angle() dotProduct() midpoint() or crossProduct()
 * TODO Add static methods for all operations
 * TODO Add static methods for distance(), angleBetween() and dotProduct()
 * TODO Consider adding lerp() method */

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
	
	public Vector2D() {
		this.x = 1.0;
		this.y = 0.0;
	}
	
	public static Vector2D newPolar(double magnitude, double direction) {
		return new Vector2D().setMagnitude(magnitude).setAngle(direction);
	}
	
	public static Vector2D copy(Vector2D vector) {
		return new Vector2D(vector);
	}
	
	public static Vector2D copy(Point2D point) {
		return new Vector2D(point);
	}
	
	public Vector2D copy() {
		return Vector2D.copy(this);
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
	
	public static Vector2D add(Vector2D vector1, Vector2D vector2) {
		return vector1.copy().add(vector2);
	}
	
	public static Vector2D add(Vector2D vector, Point2D point) {
		return vector.copy().add(point);
	}
	
	public static Vector2D add(Point2D point, Vector2D vector) {
		return vector.copy().add(point);
	}
	
	public static Vector2D add(Point2D point1, Point2D point2) {
		return new Vector2D(point1).add(point2);
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
		if (this.getMagnitude() != 0) {
			this.divide(this.getMagnitude());
			return this;			
		} else {
			this.setX(1.0);
			return this;
		}
	}
	
	public Vector2D setMagnitude(double length) {
		this.normalize();
		this.multiply(length);
		return this;
	}
	
	public double getAngle() {
		return Math.atan(this.y/this.x);
	}
	
	public Vector2D setAngle(double direction) {
		direction = Math.toRadians(direction % 360);
		double magnitude = this.getMagnitude();
		this.x = Math.cos(direction) * magnitude;
		this.y = Math.sin(direction) * magnitude;
		return this;
	}
	
	public Vector2D rotate(double angle) {
		angle += this.getAngle();
		return this.setAngle(angle);
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
