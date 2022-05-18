package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.GameRun;
import logic.UpdatableHolder;
import screen.GamePlayScene;
import sharedObject.AudioLoader;
import sharedObject.ImageLoader;
import javafx.scene.Parent;

public class Main extends Application {

	private static Stage primaryStage;
	private static Parent startScene;
	private static GamePlayScene gamePlayScene;
	private static GameRun gameRun;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Main.primaryStage = primaryStage;

		// set up bgSound
		AudioLoader.startBGSound.setCycleCount(Timeline.INDEFINITE);
		AudioLoader.startBGSound.setVolume(0.8);
		AudioLoader.gamePlayBGSound.setCycleCount(Timeline.INDEFINITE);
		AudioLoader.gamePlayBGSound.setVolume(0.6);
		// set up other sound volume
		AudioLoader.mouseEnterSound.setVolume(0.5);
		AudioLoader.buttonClickSound.setVolume(0.5);

		// set up sound button image
		ImageLoader.soundOffImg.setFitHeight(40);
		ImageLoader.soundOffImg.setPreserveRatio(true);
		ImageLoader.soundOnImg.setFitHeight(40);
		ImageLoader.soundOnImg.setPreserveRatio(true);

		// set timeline to consistently update screen
		Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				UpdatableHolder.getInstance().updateScreen();
			}
		}), new KeyFrame(Duration.seconds(0.4)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		initializeStartScene();
	}

	public static void initializeStartScene() {

		// create startScene
		try {
			startScene = FXMLLoader.load(Main.class.getClassLoader().getResource("screen/StartScene.fxml"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		primaryStage.setScene(new Scene(startScene));
		primaryStage.setTitle("UNO 24 | presented by chomchaby");
		primaryStage.setResizable(false);
		primaryStage.show();

		// play bgSound
		AudioLoader.gamePlayBGSound.stop();
		AudioLoader.startBGSound.play();

	}

	public static void initializeGamePlayScene() {

		// create gamePlayScene
		gamePlayScene = new GamePlayScene();

		primaryStage.setScene(new Scene(gamePlayScene));
		primaryStage.setTitle("UNO 24 | presented by chomchaby");
		primaryStage.show();
		// play bgSound
		AudioLoader.startBGSound.stop();
		AudioLoader.gamePlayBGSound.play();

		// start gameRun thread
		runGame();

	}

	public static void runGame() {
//		gameRun.interrupt();
		gameRun = new GameRun();
		gameRun.start();
	}

	public static void getRidOfGameRun() {
		gameRun.interrupt();
		gameRun.stop();
	}
}
