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

//	private static AudioClip clickSound1 = new AudioClip(ClassLoader.getSystemResource("audio/Mouse_Click1.mp3").toString());

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
		AudioLoader.mouseEnterSound.play();
		playButton.setPrefWidth(230);
		playButton.setPrefHeight(75);
		playButton.setStyle("-fx-cursor: hand;");
	}

	public void resetPlayButton(MouseEvent event) {
		playButton.setPrefWidth(210);
		playButton.setPrefHeight(65);
		playButton.setStyle("-fx-cursor: default;");
	}

	public void enterHowToPlayButtonHandler(MouseEvent event) {
		AudioLoader.mouseEnterSound.play();
		howToPlayButton.setPrefWidth(230);
		howToPlayButton.setPrefHeight(75);
		howToPlayButton.setStyle("-fx-cursor: hand;");
	}

	public void resetHowToPlayButton(MouseEvent event) {
		howToPlayButton.setPrefWidth(210);
		howToPlayButton.setPrefHeight(65);
		howToPlayButton.setStyle("-fx-cursor: default;");
	}

	public void enterQuitButtonHandler(MouseEvent event) {
		AudioLoader.mouseEnterSound.play();
		quitButton.setPrefWidth(230);
		quitButton.setPrefHeight(75);
		quitButton.setStyle("-fx-cursor: hand;");
	}

	public void resetQuitButton(MouseEvent event) {
		quitButton.setPrefWidth(210);
		quitButton.setPrefHeight(65);
		quitButton.setStyle("-fx-cursor: default;");
	}

}
