package main;

import java.util.Scanner;

import gui.BotDeckPane;
import gui.StatusPane;
import gui.Updatable;
import gui.UserDeckPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.GameLogic;
import logic.UpdatableHolder;
import javafx.scene.Parent;

public class Main2 extends Application {
	
	private static Stage primaryStage;
	private static AudioClip bgSound;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Main2.primaryStage = primaryStage;

		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("screen/StartScene.fxml"));
			primaryStage.setTitle("Uno Unoo");
			primaryStage.setScene(new Scene(root));
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		bgSound = new AudioClip(ClassLoader.getSystemResource("audio/Funky_Bass.mp3").toString());
		bgSound.play();
	}

	public static void setGamePlayScene() {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		root.setPrefHeight(750);
		root.setPrefWidth(1250);
		
		// change primaryStage to gamePlayScene
		Main2.primaryStage.setScene(new Scene(root));
		bgSound.stop();
		bgSound = new AudioClip(ClassLoader.getSystemResource("audio/Soft_Piano.mp3").toString());
		bgSound.play();
	}
	
	

}
