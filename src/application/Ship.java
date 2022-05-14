package application;

public class Ship extends PhysicsObject {
	private boolean thrusters;
	private boolean turningLeft;
	private boolean turningRight;
	private Vector2D acceleration;
	
	public Ship() {
		super();
		this.acceleration = new Vector2D(100.0,0.0);
	}
	
	@Override
	protected void move(double deltaTime) {
		this.heading = this.heading + this.angularSpeed*deltaTime; // change heading based on angular speed
		this.heading = this.heading %360; // restrict heading from 0 to 360 degrees
		this.acceleration = this.acceleration.setAngle(this.heading);
		Vector2D deltaPos = Vector2D.copy(this.velocity).multiply(deltaTime); // displacement = initial velocity * time + ...
		this.velocity.add(this.acceleration.copy().multiply(deltaTime)); // final velocity is initial velocity + acceleration * time
		deltaPos = deltaPos.add(this.acceleration.copy().multiply(deltaTime).multiply(0.5)); // displacement = ... + 0.5 acceleration * time
		this.position.add(deltaPos); // update position
	}
}
