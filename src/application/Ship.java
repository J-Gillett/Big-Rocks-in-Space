package application;

public class Ship extends PhysicsObject {
	private boolean thrusters;
	private boolean turningLeft;
	private boolean turningRight;
	private Vector2D acceleration;
	// TODO Add type (player, alien... spare?)
	
	public Ship() {
		super();
		this.acceleration = new Vector2D(0.0,0.0);
		this.thrusters = false;
		this.turningLeft = false;
		this.turningRight = false;
	}
	
	@Override
	protected void addParts() {
		this.parts = BitsBox.arrow();
		for (int i=0; i<this.parts.size(); i++) {
			this.getChildren().add(this.parts.get(i));
		}
	}

	
	@Override
	protected void move(double deltaTime) {
		if (turningLeft) {
			this.heading = this.heading + -1*this.angularSpeed*deltaTime;
		}
		if (turningRight) {
			this.heading = this.heading + 1*this.angularSpeed*deltaTime;
		}
		this.heading = this.heading %360; // restrict heading from 0 to 360 degrees
		this.acceleration = this.acceleration.setAngle(this.heading);
		Vector2D deltaPos = Vector2D.copy(this.velocity).multiply(deltaTime); // displacement = initial velocity * time + ...
		if (thrusters) {
			this.velocity.add(this.acceleration.copy().multiply(deltaTime)); // final velocity is initial velocity + acceleration * time
			deltaPos = deltaPos.add(this.acceleration.copy().multiply(deltaTime).multiply(0.5)); // displacement = ... + 0.5 acceleration * time
		}
		this.position.add(deltaPos); // update position
	}
}
