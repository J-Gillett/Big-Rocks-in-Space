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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
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
			BackgroundSize reducedSize = new BackgroundSize(reducedWidth,reducedHeight,false,false,true,true);
			// TITLE SCENE
			Pane titleRoot = new Pane();
			Scene titleScene = new Scene(titleRoot);
			titleScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image titleImage = new Image(getClass().getResource("/assets/tempBackground.png").toString());
			BackgroundImage titleBackgroundImage = new BackgroundImage(titleImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,reducedSize);
			Background titleBackground = new Background(titleBackgroundImage);
			titleRoot.setBackground(titleBackground);
			// GAME SCENE
			Group gameRoot = new Group();
			Scene gameScene = new Scene(gameRoot,Color.RED);
			// INSTRUCTION SCENE
			Group instructionRoot = new Group();
			Scene instructionScene = new Scene(instructionRoot,Color.GREEN);
			// HIGH SCORE SCENE
			Group scoreRoot = new Group();
			Scene scoreScene = new Scene(scoreRoot,Color.BLUE);
			
			
			////// TITLE SCENE //////
			// TODO Add background Image
			// TODO Style Buttons
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
			Button scoreButton = new Button("high score");
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
