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

public class StartSceneController implements Initializable{
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
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Let's play!");
		dialog.setHeaderText(null);
		dialog.setContentText("Enter your name: ");
		dialog.getDialogPane().setPrefWidth(360);
		dialog.getDialogPane().setPrefHeight(120);
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()) {
			System.out.println("name: " + result.get());
		}
	}
	public void showHowToPlay(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("HOW TO PLAY THIS STUPID GAME");
		alert.showAndWait();
		
	}
	public void quitGame(ActionEvent event) {
		Platform.exit();
	}
	
	public void enterPlayButtonHandler(MouseEvent event) {
		playButton.setPrefWidth(220);
		playButton.setPrefHeight(70);
	}
	public void resetPlayButton(MouseEvent event) {
		playButton.setPrefWidth(210);
		playButton.setPrefHeight(65);
	}
	
	public void enterHowToPlayButtonHandler(MouseEvent event) {
		howToPlayButton.setPrefWidth(220);
		howToPlayButton.setPrefHeight(70);
	}
	public void resetHowToPlayButton(MouseEvent event) {
		howToPlayButton.setPrefWidth(210);
		howToPlayButton.setPrefHeight(65);
	}
	
	public void enterQuitButtonHandler(MouseEvent event) {
		quitButton.setPrefWidth(220);
		quitButton.setPrefHeight(70);
	}
	public void resetQuitButton(MouseEvent event) {
		quitButton.setPrefWidth(210);
		quitButton.setPrefHeight(65);
	}
	
}
