package application;

import java.util.LinkedList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/* This Class is purely to hide away the outlines so they don't clutter up the main classes */

public class BitsBox {

	public static LinkedList<Shape> testShape() {
		LinkedList<Shape> bits = new LinkedList<>();

		Polygon gun = new Polygon();
		gun.getPoints().setAll(new Double[] {
				0.0, 1.0,
				10.0, 1.0,
				10.0, -1.0,
				0.0, -1.0
		});
		gun.setTranslateX(2.0);
		gun.setTranslateY(-5.0);
		gun.setFill(Color.WHITE);
		gun.setStroke(Color.YELLOW);
		gun.setStrokeWidth(2.0);
		bits.add(gun);

		Polygon body = new Polygon();
		body.getPoints().setAll(new Double[] {
				-10.0, 10.0,
				30.0, 0.0,
				-10.0, -10.0
		});
		body.setFill(Color.WHITE);
		body.setStroke(Color.YELLOW);
		body.setStrokeWidth(2.0);
		bits.add(body);

		return bits;
	}
	
	public static LinkedList<Shape> enemyShip() {
		LinkedList<Shape> bits = new LinkedList<>();

		Polygon outline = new Polygon();
		outline.getPoints().setAll(new Double[] {
				-4.0, -5.0,
				-10.0, -10.0,
				0.0, -14.0,
				30.0, -7.0,
				2.0, -7.0,
				8.0, 0.0,
				2.0, 7.0,
				30.0, 7.0,
				0.0, 14.0,
				-10.0, 10.0,
				-4.0, 5.0
		});
		outline.setFill(Color.WHITE);
		outline.setStroke(Color.YELLOW);
		outline.setStrokeWidth(2.0);
		bits.add(outline);

		return bits;
	}
	
}
