package application;

import javafx.scene.paint.Color;

public class Asteroid extends PhysicsObject {
	public Asteroid() {
		this.addParts();
		this.setHitbox();
		this.position = this.randomPosition();
		this.velocity = new Vector2D(100+Math.random()*200,0.0);
		this.heading = Math.random()*360;
		this.velocity.setAngle(this.heading);
		this.angularSpeed = (Math.random()-0.5)*180.0;
		this.render();
	}
	
	private void addParts() {
		this.parts = BitsBox.bigRock();
		for (int i=0; i<this.parts.size(); i++) {
			this.getChildren().add(this.parts.get(i));
		}
	}
	
	private Vector2D randomPosition() {
		// F'NG HELL - creates a giant cross of no-go zones.
		double minSpawnDistance = 200.0;
		double randomX = (Math.random()-0.5)*2;
		double rangeX = Main.screenWidth/2-minSpawnDistance;
		double randomY = (Math.random()-0.5)*2;
		double rangeY = Main.screenHeight/2-minSpawnDistance;
		double x = (Main.screenWidth/2)+randomX*(rangeX+minSpawnDistance/Math.abs(randomX));
		double y = (Main.screenHeight/2)+randomY*(rangeY+minSpawnDistance/Math.abs(randomY));
		return new Vector2D(x,y);
	}
	
	@Override
	public void collisionResponse(PhysicsObject other) {
		this.hitbox.setFill(Color.RED);
		this.hitbox.setStroke(Color.RED);
		Main.gameObjects.remove(this); // THROW IT IN THE BIN! AT END OF FRAME, EMPTY THE BIN!
	}

}
