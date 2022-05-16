package screen;

import java.net.URL;
import java.util.ResourceBundle;

import gui.BotPane;
import gui.UserPane;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import logic.GameLogic;
import sharedObject.AudioLoader;

public class GamePlaySceneController implements Initializable {

	@FXML
	private BorderPane gamePlayPane;
	// top
	@FXML
	private VBox botMagaretPane;
	@FXML
	private TextArea botMagaretTurnText;
	// left
	@FXML
	private VBox botJesicaPane;
	@FXML
	private TextArea botJesicaTurnText;
	// right
	@FXML
	private VBox botVandaPane;
	@FXML
	private TextArea botVandaTurnText;
	// center
	@FXML
	private StackPane tablePane;
	@FXML
	private HBox statusPane;
	@FXML
	private HBox colorSelectionPane;
	// bottom
	@FXML
	private StackPane bottomGamePlayPane;
	@FXML
	private VBox userPane;
	@FXML
	private TextArea userTurnText;
	@FXML
	private TilePane menuPane;
	@FXML
	private Button scoreButton;
	@FXML
	private Button homeButton;
	@FXML
	private Button quitButton;
	@FXML
	private ToggleButton soundButton;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("Hello");
//		botMagaretDeckPane = new BotDeckPane(GameLogic.getInstance().getBotMagaret());

//		botMagaretPane.getChildren().add(new BotDeckPane(GameLogic.getInstance().getBotMagaret()));
//		botJesicaPane.getChildren().add(new BotDeckPane(GameLogic.getInstance().getBotJesica()));
//		botVandaPane.getChildren().add(new BotDeckPane(GameLogic.getInstance().getBotVanda()));
//		userPane.getChildren().add(new UserDeckPane(GameLogic.getInstance().getUser()));
	}
	
	// handlers for buttons in menuPane
	public void showScore() {
		AudioLoader.mouseClick1Sound.play();

	}
	public void returnToStartScene() {
		AudioLoader.mouseClick1Sound.play();
	}
	public void quitGame() {
		Platform.exit();
	}
	public void enterScoreButtonHandler() {
		enterButtonHandler(scoreButton);
	}
	public void enterHomeButtonHandler() {
		enterButtonHandler(homeButton);
	}
	public void enterQuitButtonHandler() {
		enterButtonHandler(quitButton);
	}
	public void resetScoreButtonHandler() {
		resetButtonHandler(scoreButton);
	}
	public void resetHomeButtonHandler() {
		resetButtonHandler(homeButton);
	}
	public void resetQuitButtonHandler() {
		resetButtonHandler(quitButton);
	}
	private void enterButtonHandler(Button button) {
		AudioLoader.mouseEnterSound.play();
		button.setPrefWidth(120);
		button.setPrefHeight(30);
		button.setStyle("-fx-cursor: hand;");
	}
	private void resetButtonHandler(Button button) {
		button.setPrefWidth(80);
		button.setPrefHeight(30);
	}
	
	// setSound
	public void setSound() {
		
	}
	
}
