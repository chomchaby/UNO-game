package main;

import gui.Updatable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.GameLogic;
import screen.GamePlayScene;
import sharedObject.AudioLoader;
import javafx.scene.Parent;

public class Main2 extends Application {

	private static Stage primaryStage;
	private static Parent startScene;
	private static GamePlayScene gamePlayScene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Main2.primaryStage = primaryStage;
		
		// create startScene
		try {
			startScene = FXMLLoader.load(getClass().getClassLoader().getResource("screen/StartScene.fxml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// create gamePlayScene
		gamePlayScene = new GamePlayScene();
//		gamePlayScene = FXMLLoader.load(getClass().getClassLoader().getResource("screen/GamePlayScene2.fxml"));
		
		// set up bgSound
		AudioLoader.startBGSound.setCycleCount(Timeline.INDEFINITE);
		AudioLoader.startBGSound.setVolume(0.9);
		AudioLoader.gamePlayBGSound.setCycleCount(Timeline.INDEFINITE);
		AudioLoader.gamePlayBGSound.setVolume(0.5);

		initializeStartScene();
	}

	public static void initializeStartScene() {

		primaryStage.setScene(new Scene(startScene));
		primaryStage.setTitle("Uno Unoo");
		primaryStage.setResizable(false);
		primaryStage.show();
		// play bgSound
		AudioLoader.gamePlayBGSound.stop();
		AudioLoader.startBGSound.play();

	}

	public static void initializeGamePlayScene() {

		primaryStage.setScene(new Scene(gamePlayScene));
		primaryStage.setTitle("I just wanna pen fan you dai bor?");
		primaryStage.show();
		// play bgSound
		AudioLoader.startBGSound.stop();
		AudioLoader.gamePlayBGSound.play();

		Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				for (Updatable item : gamePlayScene.getUpdatableItems()) {
					item.update();
				}
//				((BotDeckPane)GamePlaySceneController.botMagaretDeckPane).update();
				
			}
		}), new KeyFrame(Duration.seconds(0.4)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
		Thread runGame = new Thread(() -> {
			while (true) {
				GameLogic.getInstance().shortProcessing();
				while (!GameLogic.getInstance().isGameEnd()) {
					System.err.println("---- NEW TURN ----");
					System.out.println(">> " + GameLogic.getInstance().getCurrentPlayer().getName() + " Turn");
//					System.out.println("Turn : " + GameLogic.getInstance().getPlayerTurn());
//					System.out.println("Playable : " + GameLogic.getInstance().getCurrentPlayer().isPlayable());
//					System.out.println("Clockwise : " + GameLogic.getInstance().isClockwise());
//					System.out.println("Current Player: " + GameLogic.getInstance().getCurrentPlayer().getName());
//					System.out.println("Next Player: " + GameLogic.getInstance().getNextPlayer().getName());
					if (GameLogic.getInstance().getCurrentPlayer().isPlayable()) {

						GameLogic.getInstance().getCurrentPlayer().play();

					} else {
						System.out.println(" - Blocked - ");
						GameLogic.getInstance().longProcessing();
						GameLogic.getInstance().getCurrentPlayer().setPlayable(true);

					}
					if (GameLogic.getInstance().getCurrentPlayer().isWin()) {
						GameLogic.getInstance().setGameEnd(true);
						break;
					}
					GameLogic.getInstance().setUpForNewTurn();
					GameLogic.getInstance().shortProcessing();
				}
			}
		});

		runGame.start();
	}
	
	public static GamePlayScene getGamePlayScene() {
		return gamePlayScene;
	}

}
