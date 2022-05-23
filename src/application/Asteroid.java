package application;

public class Asteroid extends PhysicsObject {
	public Asteroid() {
		this.addParts();
		this.setHitbox();
		this.position = new Vector2D(Main.screenWidth/2,Main.screenHeight/2-50.0);
		this.velocity = new Vector2D(100+Math.random()*200,0.0);
		this.heading = Math.random()*360;
		this.velocity.setAngle(this.heading);
		this.angularSpeed = (Math.random()-0.5)*180.0;
		this.proximityRadius = 0.0;
		this.render();
	}
	
	private void addParts() {
		this.parts = BitsBox.bigRock();
		for (int i=0; i<this.parts.size(); i++) {
			this.getChildren().add(this.parts.get(i));
		}
	}

}
