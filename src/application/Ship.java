package application;

public class Ship extends PhysicsObject {
	private boolean thrusters;
	private boolean turningLeft;
	private boolean turningRight;
	private Vector2D acceleration;
	// TODO Add type (player, alien... spare?)
	
	public Ship() {
		super();
		this.angularSpeed = 360.0;
		this.velocity = new Vector2D(0.0,0.0);
		this.heading = 270.0;
		this.acceleration = new Vector2D(200.0,0.0);
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
		this.acceleration.setAngle(this.heading);
		if (thrusters) {
			this.velocity.add(this.acceleration.copy().multiply(deltaTime));			
		}
		this.position.add(this.velocity.copy().multiply(deltaTime));
	}
	
	public void thrustersOn() {
		this.thrusters = true;
	}
	
	public void turnLeft() {
		this.turningLeft = true;
	}
	
	public void turnRight() {
		this.turningRight = true;
	}
	
	public void thrustersOff() {
		this.thrusters = false;
	}
	
	public void stopLeftTrun() {
		this.turningLeft = false;
	}
	
	public void stopRightTurn() {
		this.turningRight = false;
	}
	
}
