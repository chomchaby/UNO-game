package screen;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import logic.GameLogic;
import main.Main2;
import sharedObject.AudioLoader;

public class StartSceneController implements Initializable {

	@FXML
	private BorderPane startPane;
	@FXML
	private HBox menuPane;
	@FXML
	private Button playButton;
	@FXML
	private Button howToPlayButton;
	@FXML
	private Button quitButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	public void playGame(ActionEvent event) {
		AudioLoader.mouseClick1Sound.play();
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Let's play!");
		dialog.setHeaderText(null);
		dialog.setContentText("Enter your name: ");
		dialog.getDialogPane().setPrefWidth(360);
		dialog.getDialogPane().setPrefHeight(120);
		Optional<String> name = dialog.showAndWait();
		if (name.isPresent()) {
			// set User name (create GameLogic Instance for the first time)
			GameLogic.getInstance().setUserName(name.get());
			// change to GamePlayScene
			Main2.initializeGamePlayScene();
		}
		AudioLoader.mouseClick1Sound.play();
	}

	public void showHowToPlay(ActionEvent event) {
		AudioLoader.mouseClick1Sound.play();
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("HOW TO PLAY THIS STUPID GAME");
		alert.showAndWait();
	}

	public void quitGame(ActionEvent event) {
		Platform.exit();
	}

	public void enterPlayButtonHandler(MouseEvent event) {
		enterButtonHandler(playButton);
	}

	public void resetPlayButton(MouseEvent event) {
		resetButtonHandler(playButton);
	}

	public void enterHowToPlayButtonHandler(MouseEvent event) {
		enterButtonHandler(howToPlayButton);
	}

	public void resetHowToPlayButton(MouseEvent event) {
		resetButtonHandler(howToPlayButton);
	}

	public void enterQuitButtonHandler(MouseEvent event) {
		enterButtonHandler(quitButton);
	}

	public void resetQuitButton(MouseEvent event) {
		resetButtonHandler(quitButton);
	}
	
	private void enterButtonHandler(Button button) {
		AudioLoader.mouseEnterSound.play();
		button.setPrefWidth(230);
		button.setPrefHeight(75);
		button.setStyle("-fx-cursor: hand;");
	}
	
	private void resetButtonHandler(Button button) {
		button.setPrefWidth(210);
		button.setPrefHeight(65);
	}

}
