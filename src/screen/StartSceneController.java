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
import javafx.scene.layout.StackPane;
import logic.GameLogic;
import main.Main2;
import sharedObject.AudioLoader;
import sharedObject.ImageLoader;

public class StartSceneController implements Initializable {

	@FXML
	private StackPane stackPane;
	@FXML
	private BorderPane startPane;
	@FXML
	private HBox menuPane;
	@FXML
	private Button startButton;
	@FXML
	private Button howToPlayButton;
	@FXML
	private Button quitButton;
	@FXML
	private Button soundBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// set BGImg to sound toggle button
		soundBtn.setGraphic(ImageLoader.soundOffImg);
	}

	public void startGame(ActionEvent event) {
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
			GameLogic.getInstance().start(name.get());
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
		enterButtonHandler(startButton);
	}

	public void resetPlayButton(MouseEvent event) {
		resetButtonHandler(startButton);
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
	}
	
	private void resetButtonHandler(Button button) {
		button.setPrefWidth(210);
		button.setPrefHeight(65);
	}
	
	public void toggleSound(ActionEvent event) {
		// to stop background sound
		if (AudioLoader.startBGSound.isPlaying()) {
	        soundBtn.setGraphic(ImageLoader.soundOnImg);
	        AudioLoader.startBGSound.stop();
		}
		// to play background sound
		else {
	        soundBtn.setGraphic(ImageLoader.soundOffImg);
	        AudioLoader.startBGSound.play();
		}
	}

}
