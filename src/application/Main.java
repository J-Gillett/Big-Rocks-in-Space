package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	// Getting Screen Size
	public static Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	public static int screenWidth = (int) screenBounds.getWidth();
	public static int screenHeight = (int) screenBounds.getHeight();
	private long prevFrame = 0;
	private boolean displayFPS = false;
	private boolean gamePaused = false;
	private boolean debugPause = false;
	private double timeWarpFactor = 1.0;
	public Collection gameObjects = new Collection();
	private Ship playerShip;

	
	@Override
	public void start(Stage stage) {
		try {

			////// STAGE SETUP //////
			final int REDUCED_WIDTH = (int) (screenWidth*0.8); // menu scenes will be 80% of full screen width
			final int REDUCED_HEIGHT = (int) (screenHeight*0.8); // menu scenes will be 80% of full screen height
			final int BUTTON_WIDTH = (int) (REDUCED_WIDTH/6); // button width will be relative to menu scenes
			final int BUTTON_HEIGHT = (int) (REDUCED_HEIGHT/10); // button height will be relative to menu scenes
			final int BUTTON_GAP = (int) (BUTTON_HEIGHT/10); // button gape will be relative to menu scenes
			final int BUTTON_MARGIN = (int) (BUTTON_WIDTH/4); // button margin will be relative to menu scenes
			stage.setTitle("Big Rocks in Space");
			// TODO add icon
			stage.setWidth(REDUCED_WIDTH); // start off in menu scenes
			stage.setHeight(REDUCED_HEIGHT); // start of in menu scenes
			stage.initStyle(StageStyle.UNDECORATED); // border-less window
			stage.setResizable(false); // I'm not that nice to let people choose the size of the window
			stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("F11")); // ESC used for pausing the game
			stage.setFullScreenExitHint(""); // full screen exit button is not advertised
			
			
			
			////// INITIALIZE SCENES //////
			// Title Scene
			Pane titleRoot = new Pane();
			Scene titleScene = new Scene(titleRoot);
			titleRoot.setId("title");
			titleScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(titleScene);

			// High Score Scene
			Pane scoreRoot = new Pane();
			Scene scoreScene = new Scene(scoreRoot);
			scoreRoot.setId("score");
			scoreScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Instructions Scene
			Pane instructionRoot = new Pane();
			Scene instructionScene = new Scene(instructionRoot);
			instructionRoot.setId("instructions");
			instructionScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Game Scene
			BorderPane gameRoot = new BorderPane();
			Scene gameScene = new Scene(gameRoot);
			gameRoot.setId("game");
			gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			
			
			////// TITLE SCENE //////
			// Start Button
			Button startButton = new Button("start game");
			startButton.setDefaultButton(true);
			startButton.setPrefWidth(BUTTON_WIDTH);
			startButton.setPrefHeight(BUTTON_HEIGHT);
			startButton.setLayoutX(REDUCED_WIDTH - BUTTON_WIDTH - BUTTON_MARGIN);
			startButton.setLayoutY(REDUCED_HEIGHT - BUTTON_MARGIN - 4*BUTTON_HEIGHT - 3*BUTTON_GAP);
			// Action set below game loop

			// High Scores Button
			Button scoreButton = new Button("high scores");
			scoreButton.setPrefWidth(BUTTON_WIDTH);
			scoreButton.setPrefHeight(BUTTON_HEIGHT);
			scoreButton.setLayoutX(REDUCED_WIDTH - BUTTON_WIDTH - BUTTON_MARGIN);
			scoreButton.setLayoutY(REDUCED_HEIGHT - BUTTON_MARGIN - 3*BUTTON_HEIGHT - 2*BUTTON_GAP);
			scoreButton.setOnAction(actionEvent ->  {
			    stage.setScene(scoreScene);
			});
			// Instructions Button
			Button instructionsButton = new Button("instructions");
			instructionsButton.setPrefWidth(BUTTON_WIDTH);
			instructionsButton.setPrefHeight(BUTTON_HEIGHT);
			instructionsButton.setLayoutX(REDUCED_WIDTH - BUTTON_WIDTH - BUTTON_MARGIN);
			instructionsButton.setLayoutY(REDUCED_HEIGHT - BUTTON_MARGIN - 2*BUTTON_HEIGHT - 1*BUTTON_GAP);
			instructionsButton.setOnAction(actionEvent ->  {
			    stage.setScene(instructionScene);
			});			
			// Exit Game Button
			Button exitButton = new Button("exit");
			exitButton.setCancelButton(true);
			exitButton.setPrefWidth(BUTTON_WIDTH);
			exitButton.setPrefHeight(BUTTON_HEIGHT);
			exitButton.setLayoutX(REDUCED_WIDTH - BUTTON_WIDTH - BUTTON_MARGIN);
			exitButton.setLayoutY(REDUCED_HEIGHT - BUTTON_MARGIN - 1*BUTTON_HEIGHT - 0*BUTTON_GAP);
			exitButton.setOnAction(actionEvent ->  {
			    Platform.exit();
			});
			// adding children to titleRoot
			titleRoot.getChildren().addAll(startButton, scoreButton, instructionsButton, exitButton);			


			
			////// HIGH SCORES SCENE //////
			// Back Button
			Button backFromHighScores = new Button("return");
			backFromHighScores.setPrefWidth(BUTTON_WIDTH);
			backFromHighScores.setPrefHeight(BUTTON_HEIGHT);
			backFromHighScores.setLayoutX(REDUCED_WIDTH - BUTTON_WIDTH - BUTTON_MARGIN);
			backFromHighScores.setLayoutY(REDUCED_HEIGHT - BUTTON_HEIGHT - BUTTON_MARGIN);
			backFromHighScores.setOnAction(actionEvent ->  {
			    stage.setScene(titleScene);
			});
			// adding children to scoreRoot
			scoreRoot.getChildren().addAll(backFromHighScores);
			
			
			
			
			////// INSTRUCTIONS SCENE //////			
			// Back Button
			Button backFromInstructions = new Button("return");
			backFromInstructions.setPrefWidth(BUTTON_WIDTH);
			backFromInstructions.setPrefHeight(BUTTON_HEIGHT);
			backFromInstructions.setLayoutX(REDUCED_WIDTH - BUTTON_WIDTH - BUTTON_MARGIN);
			backFromInstructions.setLayoutY(REDUCED_HEIGHT - BUTTON_HEIGHT - BUTTON_MARGIN);
			backFromInstructions.setOnAction(actionEvent ->  {
			    stage.setScene(titleScene);
			});
			// adding children to instructionRoot
			instructionRoot.getChildren().addAll(backFromInstructions);


			
			
			////// GAME SCENE //////
			// PAUSE OBJECTS
			Rectangle pauseBG = new Rectangle(screenWidth,screenHeight);
			pauseBG.setFill(Color.BLACK);
			pauseBG.setOpacity(0.8);
			gameScene.setCursor(Cursor.NONE);
			// Pause Text
			Text pauseText = new Text("GAME PAUSED");
			pauseText.setFill(Color.WHITE);
			pauseText.setFont(new Font(80));
			// Resume Button
			Button resume = new Button("resume");
			resume.setPrefWidth(BUTTON_WIDTH);
			resume.setPrefHeight(BUTTON_HEIGHT);
			// Quit Button
			Button quit = new Button("quit");
			quit.setPrefWidth(BUTTON_WIDTH);
			quit.setPrefHeight(BUTTON_HEIGHT);
			VBox pause = new VBox(pauseText,resume,quit);
			pause.setSpacing(50);
			pause.setAlignment(Pos.CENTER);
			// FPS display
			Text fpsDisplay = new Text("0");
			fpsDisplay.setFill(Color.WHITE);
			fpsDisplay.setStroke(Color.BLACK);
			fpsDisplay.setFont(new Font(80));
			BorderPane.setAlignment(fpsDisplay,Pos.BOTTOM_RIGHT);

			gameRoot.getChildren().add(gameObjects);
			
			Controller control = new Controller();

			// GAME LOOP //
			AnimationTimer gameloop = new AnimationTimer()
			{
				private double deltaTime;
				private double fpsPeriod = 0.5; // number of seconds between fps updates
				private double fpsTimer = fpsPeriod;
				private int frameCount = 0;
				private int fpsMean;

				@Override
				public void handle(long nano) {
					// Get time since last frame & updating FPS counter
					if (prevFrame != 0) {
						deltaTime = (nano - prevFrame)/Math.pow(10, 9); // delta time = current time-stamp - previous time-stamp / a billion seconds

						double FR = (1/deltaTime);
						if (fpsTimer > 0) {
							fpsMean += FR;
							fpsTimer -= deltaTime;
							frameCount++;
						} else {
							fpsMean += FR;
							fpsMean /= frameCount;
							fpsDisplay.setText(Integer.toString(fpsMean));
							fpsTimer = fpsPeriod;
							frameCount=0;
						}

						deltaTime *= timeWarpFactor; // change the passage of time...

					
					}
					prevFrame = nano; // set previous frame time-stamp to current
					
										
					
					////// ENEMY STEERING //////
					// TODO Create enemy controller(s)
					
					
					////// UPDATES //////
					for (int i=0; i < gameObjects.size(); i++) {
						gameObjects.get(i).update(deltaTime);
						gameObjects.get(i).hitbox.setFill(Color.TRANSPARENT);
						gameObjects.get(i).hitbox.setStroke(Color.TRANSPARENT);
					}
					
					////// COLLISIONS //////
					for (int i=gameObjects.size()-1; i>=0; i--) {
						for (int j=gameObjects.size()-1; j>=0; j--) {
							if (gameObjects.get(i) == gameObjects.get(j)) {
								continue;
							}
							if (!gameObjects.get(i).isProximal(gameObjects.get(j))) {
								continue;
							}
							if (gameObjects.get(i).hasCollided(gameObjects.get(j))) {
								gameObjects.get(i).hitbox.setFill(Color.RED);
								gameObjects.get(j).hitbox.setFill(Color.RED);
								gameObjects.get(i).hitbox.setStroke(Color.RED);
								gameObjects.get(j).hitbox.setStroke(Color.RED);
							}
						}
					}
					
					////// RENDERING //////
					for (int i=0; i < gameObjects.size(); i++) {
						gameObjects.get(i).render();
					}

					////// GAME STATE ///////
					// TODO Check end of level
					// TODO Check if game over
				}
				
			};
			
			// ACTIONS //
			startButton.setOnAction(actionEvent ->  {
			    stage.setScene(gameScene);
			    stage.setWidth(screenWidth);
			    stage.setHeight(screenHeight);
			    stage.setFullScreen(true);
			    playerShip = new Ship();
			    gameObjects.add(playerShip);
			    int initialAsteroids = 1;
				for (int i=0; i<initialAsteroids; i++) {
					gameObjects.add(new Asteroid());
				}
				gameloop.start();
			});

			gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					KeyCode pressedKey = event.getCode();
					switch (pressedKey) {
					case ESCAPE:
						if (gamePaused == false) {
							gamePaused = true;
							gameloop.stop();
							prevFrame = 0;
							gameRoot.getChildren().add(pauseBG);
							gameRoot.setCenter(pause);
							gameScene.setCursor(Cursor.DEFAULT);
						} else {
							gamePaused = false;
							gameScene.setCursor(Cursor.NONE);
							gameRoot.getChildren().remove(pauseBG);
							gameRoot.getChildren().remove(pause);
							gameloop.start();
						}
						break;
					case P:
						if (debugPause == false) {
							debugPause = true;
							gameloop.stop();
							prevFrame = 0;
							gameScene.setCursor(Cursor.DEFAULT);
						} else {
							debugPause = false;
							gameScene.setCursor(Cursor.NONE);
							gameloop.start();
						}
						break;
					case OPEN_BRACKET:
						if (debugPause == true) {
							for (int i=0; i < gameObjects.size(); i++) {
								gameObjects.get(i).update(-0.02);
								gameObjects.get(i).render();
							}
						}
						break;
					case CLOSE_BRACKET:
						if (debugPause == true) {
							for (int i=0; i < gameObjects.size(); i++) {
								gameObjects.get(i).update(0.02);
								gameObjects.get(i).render();
							}
						}
						break;
					case MINUS:
						timeWarpFactor *= 0.5;
						break;
					case EQUALS:
						timeWarpFactor *= 2;
						break;
					case F:
						if (displayFPS == false) {
							displayFPS = true;
							gameRoot.setBottom(fpsDisplay);
						} else {
							displayFPS = false;
							gameRoot.getChildren().remove(fpsDisplay);
						}
						break;
					default:
						control.keyPressed(pressedKey, playerShip);
						break;
					}
				}
			});
			gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					KeyCode releasedKey = event.getCode();
					control.keyReleased(releasedKey, playerShip);
				}
			});
			resume.setOnAction(actionEvent ->  {
				// UNPAUSE THE GAME
				gamePaused = false;
				gameScene.setCursor(Cursor.NONE);
				gameRoot.getChildren().remove(pauseBG);
				gameRoot.getChildren().remove(pause);
				gameloop.start();
			});
			quit.setOnAction(actionEvent ->  {
				// TODO change to resetting the game and returning to title scene
				Platform.exit();
			});

			
			
			////// BEGIN //////
			stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
