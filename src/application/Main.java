package application;
	
import java.util.LinkedList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;


public class Main extends Application {
	// Getting Screen Size
	public static Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	public static int screenWidth = (int) screenBounds.getWidth();
	public static int screenHeight = (int) screenBounds.getHeight();
	
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
			Pane gameRoot = new Pane();
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
//			startButton.setOnAction(actionEvent ->  {
//			    stage.setScene(gameScene);
//			    stage.setWidth(screenWidth);
//			    stage.setHeight(screenHeight);
//			    stage.setFullScreen(true);
//			});
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
			LinkedList<PhysicsObject> gameObjects = new LinkedList<>();
			// TESTING OBJECTS
			for (int i=0; i<100; i++) {
				Ship temp = new Ship();
				gameObjects.add(temp);
				gameRoot.getChildren().add(temp);
			}
//			Ship testObject = new Ship();
//			gameObjects.add(testObject);
//			gameRoot.getChildren().add(testObject);
			gameScene.setCursor(Cursor.NONE);

			
			
			////// GAME LOOP //////
			AnimationTimer gameloop = new AnimationTimer()
			{
				private long prevFrame = 0;
				private double deltaTime;

				@Override
				public void handle(long nano) {
					// Get time since last frame
					if (prevFrame != 0) {
						deltaTime = (nano - prevFrame)/Math.pow(10, 9); // delta time = current time-stamp - previous time-stamp / a billion seconds
					}
					prevFrame = nano; // set previous frame time-stamp to current
					double FR = 1/deltaTime;
					System.out.println(FR);
					
					////// UPDATES //////
					for (int i=0; i < gameObjects.size(); i++) {
						gameObjects.get(i).update(deltaTime);
					}
					
					////// COLLISIONS //////
					// TODO Add Collisions
					
					////// RENDERING //////
					for (int i=0; i < gameObjects.size(); i++) {
						gameObjects.get(i).render();
					}

					////// GAME STATE ///////
					// TODO Check end of level
					// TODO Check if game over
				}
				
			};
			

			startButton.setOnAction(actionEvent ->  {
			    stage.setScene(gameScene);
			    stage.setWidth(screenWidth);
			    stage.setHeight(screenHeight);
			    stage.setFullScreen(true);
				gameloop.start();
			});
			
			
			////// START THE APPLICATION //////
			stage.setScene(titleScene);
//			gameloop.start();
			stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
