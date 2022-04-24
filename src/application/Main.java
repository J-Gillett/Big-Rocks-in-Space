package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;


public class Main extends Application {
	public Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	public int screenWidth = (int) screenBounds.getWidth();
	public int screenHeight = (int) screenBounds.getHeight();
	public int reducedWidth = (int) (screenWidth*0.8);
	public int reducedHeight = (int) (screenHeight*0.8);
	private final int BUTTON_WIDTH = 150;
	private final int BUTTON_HEIGHT = 60;
	private final int BUTTON_GAP = 6;
	private final int BUTTON_MARGIN = 50;
	
	@Override
	public void start(Stage stage) {
		try {
			// STAGE SETUP
			// TODO add icon
			stage.setTitle("Big Rocks in Space");
			stage.setWidth(reducedWidth);
			stage.setHeight(reducedHeight);
			stage.initStyle(StageStyle.UNDECORATED); // borderless window
			stage.setResizable(false);
			stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("F11"));
			stage.setFullScreenExitHint("");
			
			// INITIALIZE SCENES
			// Title Scene
			Group titleRoot = new Group();
			Scene titleScene = new Scene(titleRoot,Color.BLACK);
			// Game Scene
			Group gameRoot = new Group();
			Scene gameScene = new Scene(gameRoot,Color.RED);
			// Instruction Scene
			Group instructionRoot = new Group();
			Scene instructionScene = new Scene(instructionRoot,Color.GREEN);
			// High Score Scene
			Group scoreRoot = new Group();
			Scene scoreScene = new Scene(scoreRoot,Color.BLUE);
			
			// RETURN BUTTON
			// set up here so it can be added as a child to instructionRoot or scoreRoot as needed
			Button back = new Button("return");
			back.setPrefWidth(BUTTON_WIDTH);
			back.setPrefHeight(BUTTON_HEIGHT);
			back.setLayoutX(reducedWidth - BUTTON_WIDTH - BUTTON_MARGIN);
			back.setLayoutY(reducedHeight - BUTTON_HEIGHT - BUTTON_MARGIN);
			back.setOnAction(actionEvent ->  {
			    stage.setScene(titleScene);
			});
			
			// TITLE SCENE
			// TODO Add background Image
			// TODO Style Buttons
			// START BUTTON
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
			// HIGH SCORES BUTTON
			Button scoreButton = new Button("high score");
			scoreButton.setPrefWidth(BUTTON_WIDTH);
			scoreButton.setPrefHeight(BUTTON_HEIGHT);
			scoreButton.setLayoutX(reducedWidth - BUTTON_WIDTH - BUTTON_MARGIN);
			scoreButton.setLayoutY(reducedHeight - BUTTON_MARGIN - 3*BUTTON_HEIGHT - 2*BUTTON_GAP);
			scoreButton.setOnAction(actionEvent ->  {
				scoreRoot.getChildren().addAll(back);
			    stage.setScene(scoreScene);
			});
			// INSTRUCTIONS BUTTON
			Button instructionsButton = new Button("instructions");
			instructionsButton.setPrefWidth(BUTTON_WIDTH);
			instructionsButton.setPrefHeight(BUTTON_HEIGHT);
			instructionsButton.setLayoutX(reducedWidth - BUTTON_WIDTH - BUTTON_MARGIN);
			instructionsButton.setLayoutY(reducedHeight - BUTTON_MARGIN - 2*BUTTON_HEIGHT - 1*BUTTON_GAP);
			instructionsButton.setOnAction(actionEvent ->  {
				instructionRoot.getChildren().addAll(back);
			    stage.setScene(instructionScene);
			});			
			// exit game button
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
			
			// HIGH SCORES SCENE
			
			// INSTRUCTIONS SCENE

			// GAME SCENE
			
			
			stage.setScene(titleScene);
			stage.show(); // keep at end of start method, shows stage
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
