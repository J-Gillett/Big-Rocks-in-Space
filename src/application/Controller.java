package application;

import javafx.scene.input.KeyCode;

public class Controller {
	public void keyPressed(KeyCode pressedKey, Ship player) {
		switch(pressedKey) {
		case W:
		case UP:
			player.thrustersOn();
			break;
		case A:
		case LEFT:
			player.turnLeft();
			break;
		case D:
		case RIGHT:
			player.turnRight();
			break;
		default:
			break;
		}
	}
	
	public void keyReleased(KeyCode releasedKey, Ship player) {
		switch(releasedKey) {
		case UP:
			player.thrustersOff();
			break;
		case LEFT:
			player.stopLeftTrun();
			break;
		case RIGHT:
			player.stopRightTurn();
			break;
		default:
			break;
		}
	}
}
