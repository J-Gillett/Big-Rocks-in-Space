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
	
	public static LinkedList<Shape> swift() {
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

	
	public static LinkedList<Shape> gemini() {
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
	
	public static LinkedList<Shape> arrow() {
		LinkedList<Shape> bits = new LinkedList<>();

		Polygon outline = new Polygon();
		outline.getPoints().setAll(new Double[] {
				-6.0, -5.0,
				-15.0, -7.0,
				-2.0, -10.0,
				5.0, -4.0,
				25.0, 0.0,
				5.0, 4.0,
				-2.0, 10.0,
				-15.0, 7.0,
				-6.0, 5.0
		});
		outline.setFill(Color.WHITE);
		outline.setStroke(Color.YELLOW);
		outline.setStrokeType(StrokeType.INSIDE);
		bits.add(outline);
		return bits;
	}
	
	public static LinkedList<Shape> bigRock() {
		LinkedList<Shape> bits = new LinkedList<>();
		Polygon outline = new Polygon();
		outline.setFill(Color.WHITE);
		outline.setStroke(Color.PURPLE);
		outline.setStrokeType(StrokeType.INSIDE);
		
		int wedges = 16;
		double wedgeAngle = 360/wedges;
		double innerRadius = 32;
		double maxRadiusIncrease = 16; // max increase from inner radius to outer
		
		// using arrays and two loops so radii length generation can be switched to perlin noise later
		double[] angles = new double[wedges];
		double[] radii = new double[wedges];
		for (int i = 0; i < wedges; i++) {
			angles[i] =  wedgeAngle*i + Math.random()*wedgeAngle;
			radii[i] = innerRadius + Math.random()*maxRadiusIncrease;
		}
		for (int i = 0; i < wedges; i++) {
			Vector2D outlinePoint = Vector2D.newPolar(radii[i], angles[i]);
			outline.getPoints().addAll(outlinePoint.getX(),outlinePoint.getY());
		}
		
		
		bits.add(outline);
		return bits;
	}


}
