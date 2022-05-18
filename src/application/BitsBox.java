package application;

import java.util.Collections;
import java.util.LinkedList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

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
		bits.add(body);

		return bits;
	}
	
	public static LinkedList<Shape> enemyShip() {
		LinkedList<Shape> bits = new LinkedList<>();

		Polygon outline = new Polygon();
		outline.getPoints().setAll(new Double[] {
				-4.0, -5.0,
				-10.0, -8.0,
				0.0, -12.0,
				30.0, -5.0,
				2.0, -5.0,
				8.0, 0.0,
				2.0, 5.0,
				30.0, 5.0,
				0.0, 12.0,
				-10.0, 8.0,
				-4.0, 5.0
		});
		outline.setFill(Color.WHITE);
		outline.setStroke(Color.YELLOW);
		outline.setStrokeType(StrokeType.INSIDE);
		bits.add(outline);
		return bits;
	}

	
	public static LinkedList<Shape> playerShip() {
		LinkedList<Shape> bits = new LinkedList<>();

		Polygon body = new Polygon();
		body.getPoints().setAll(new Double[] {
				-5.0, -4.0,
				6.0, -5.0,
				8.0, -10.0,
				32.0, -5.0,
				32.0, 5.0,
				8.0, 10.0,
				6.0, 5.0,
				-5.0, 4.0
		});

		Rectangle leftEngine = new Rectangle(-7.0,-12.0,12.0,5.0);
		Rectangle rightEngine = new Rectangle(-7.0,6.0,12.0,5.0);
		Rectangle leftStrut = new Rectangle(-2.0,-7.0,4.0,3.0);
		Rectangle rightStrut = new Rectangle(-2.0,4.0,4.0,3.0);

		Collections.addAll(bits, leftStrut, rightStrut, leftEngine, rightEngine, body);
		
		for (int i = 0; i < bits.size(); i++) {
			bits.get(i).setFill(Color.WHITE);
			bits.get(i).setStroke(Color.YELLOW);
			bits.get(i).setStrokeType(StrokeType.INSIDE);
		}

		return bits;
	}

}
