package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class Main extends Application {
	public Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	public int screenWidth = (int) screenBounds.getWidth();
	public int screenHeight = (int) screenBounds.getHeight();
	public int reducedWidth = (int) (screenWidth*0.8);
	public int reducedHeight = (int) (screenHeight*0.8);
	private final int BUTTON_WIDTH = (int) (reducedWidth/6);
	private final int BUTTON_HEIGHT = (int) (reducedHeight/10);
	private final int BUTTON_GAP = (int) (BUTTON_HEIGHT/10);
	private final int BUTTON_MARGIN = (int) (BUTTON_WIDTH/4);
	private long prev = 0;
	private long dt = 0;
	
	public double heading = 0.0; // TESTING PURPOSES ONLY
	
	@Override
	public void start(Stage stage) {
		try {

			////// STAGE SETUP //////
			stage.setTitle("Big Rocks in Space");
			stage.setWidth(reducedWidth);
			stage.setHeight(reducedHeight);
			stage.initStyle(StageStyle.UNDECORATED); // borderless window
			stage.setResizable(false);
			stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("F11"));
			stage.setFullScreenExitHint("");
			
			
			
			////// INITIALIZE SCENES //////
			// Title Scene
			Pane titleRoot = new Pane();
			Scene titleScene = new Scene(titleRoot);
			titleRoot.setId("title");

			// High Score Scene
			Pane scoreRoot = new Pane();
			Scene scoreScene = new Scene(scoreRoot);
			scoreRoot.setId("score");

			// Instructions Scene
			Pane instructionRoot = new Pane();
			Scene instructionScene = new Scene(instructionRoot);
			instructionRoot.setId("instructions");

			// Game Scene
			Pane gameRoot = new Pane();
			Scene gameScene = new Scene(gameRoot);
			gameRoot.setId("game");
			

			
			////// TITLE SCENE //////
			// Set style sheet
			titleScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// Start Button
			Button startButton = new Button("start game");
			startButton.setDefaultButton(true);
			startButton.setPrefWidth(BUTTON_WIDTH);
			startButton.setPrefHeight(BUTTON_HEIGHT);
			startButton.setLayoutX(reducedWidth - BUTTON_WIDTH - BUTTON_MARGIN);
			startButton.setLayoutY(reducedHeight - BUTTON_MARGIN - 4*BUTTON_HEIGHT - 3*BUTTON_GAP);
			startButton.setOnAction(actionEvent ->  {
			    stage.setScene(gameScene);
			    stage.setWidth(screenWidth);
			    stage.setHeight(screenHeight);
			    stage.setFullScreen(true);
			});
			// High Scores Button
			Button scoreButton = new Button("high scores");
			scoreButton.setPrefWidth(BUTTON_WIDTH);
			scoreButton.setPrefHeight(BUTTON_HEIGHT);
			scoreButton.setLayoutX(reducedWidth - BUTTON_WIDTH - BUTTON_MARGIN);
			scoreButton.setLayoutY(reducedHeight - BUTTON_MARGIN - 3*BUTTON_HEIGHT - 2*BUTTON_GAP);
			scoreButton.setOnAction(actionEvent ->  {
			    stage.setScene(scoreScene);
			});
			// Instructions Button
			Button instructionsButton = new Button("instructions");
			instructionsButton.setPrefWidth(BUTTON_WIDTH);
			instructionsButton.setPrefHeight(BUTTON_HEIGHT);
			instructionsButton.setLayoutX(reducedWidth - BUTTON_WIDTH - BUTTON_MARGIN);
			instructionsButton.setLayoutY(reducedHeight - BUTTON_MARGIN - 2*BUTTON_HEIGHT - 1*BUTTON_GAP);
			instructionsButton.setOnAction(actionEvent ->  {
			    stage.setScene(instructionScene);
			});			
			// Exit Game Button
			Button exitButton = new Button("exit");
			exitButton.setCancelButton(true);
			exitButton.setPrefWidth(BUTTON_WIDTH);
			exitButton.setPrefHeight(BUTTON_HEIGHT);
			exitButton.setLayoutX(reducedWidth - BUTTON_WIDTH - BUTTON_MARGIN);
			exitButton.setLayoutY(reducedHeight - BUTTON_MARGIN - 1*BUTTON_HEIGHT - 0*BUTTON_GAP);
			exitButton.setOnAction(actionEvent ->  {
			    Platform.exit();
			});
			// adding children to titleRoot
			titleRoot.getChildren().addAll(startButton, scoreButton, instructionsButton, exitButton);			
			

			
			
			////// HIGH SCORES SCENE //////
			// Set style sheet
			scoreScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Back Button
			Button backFromHighScores = new Button("return");
			backFromHighScores.setPrefWidth(BUTTON_WIDTH);
			backFromHighScores.setPrefHeight(BUTTON_HEIGHT);
			backFromHighScores.setLayoutX(reducedWidth - BUTTON_WIDTH - BUTTON_MARGIN);
			backFromHighScores.setLayoutY(reducedHeight - BUTTON_HEIGHT - BUTTON_MARGIN);
			backFromHighScores.setOnAction(actionEvent ->  {
			    stage.setScene(titleScene);
			});
			scoreRoot.getChildren().addAll(backFromHighScores);
			
			
			
			
			////// INSTRUCTIONS SCENE //////
			// Set style sheet
			instructionScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// Back Button
			Button backFromInstructions = new Button("return");
			backFromInstructions.setPrefWidth(BUTTON_WIDTH);
			backFromInstructions.setPrefHeight(BUTTON_HEIGHT);
			backFromInstructions.setLayoutX(reducedWidth - BUTTON_WIDTH - BUTTON_MARGIN);
			backFromInstructions.setLayoutY(reducedHeight - BUTTON_HEIGHT - BUTTON_MARGIN);
			backFromInstructions.setOnAction(actionEvent ->  {
			    stage.setScene(titleScene);
			});
			instructionRoot.getChildren().addAll(backFromInstructions);


			
			
			////// GAME SCENE //////
			// Set Style Sheet
			gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// TESTING OBJECT
			Polygon triangle = new Polygon();
			triangle.getPoints().setAll(
					-10.0,10.0,
					20.0,0.0,
					-10.0,-10.0
					);
			triangle.setFill(Color.PURPLE);
			gameRoot.getChildren().add(triangle);
			
			
			
			////// GAME LOOP //////
			AnimationTimer gameloop = new AnimationTimer()
			{

				@Override
				public void handle(long nano) {
					// Get time since last frame
					if (prev != 0) {
						dt = nano - prev;
					}
					prev = nano;

					// FOR TESTING ONLY
					heading += 5;
					triangle.setRotate(heading);
					triangle.setTranslateX(100.0);
					triangle.setTranslateY(100.0);
				}
				
			};
			

			
			////// START THE APPLICATION //////
			stage.setScene(titleScene);
			gameloop.start();
			stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
