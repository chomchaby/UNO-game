package main;

import java.util.Scanner;

import gui.BotPane;
import gui.CenterPane;
import gui.Updatable;
import gui.UserPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.GameLogic;
import logic.UpdatableHolder;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// ask name
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter your name: ");
		String userName = kb.nextLine();
		System.out.println("Let's get started");

		// create game
		GameLogic.getInstance().start(userName);

		// set root pane
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		root.setPrefHeight(750);
		root.setPrefWidth(1250);

		// set other player pane & status pane
		UserPane userPane = new UserPane(GameLogic.getInstance().getUser());
		BotPane botJesicaPane = new BotPane(GameLogic.getInstance().getBotJesica());
		Rotate rotation90 = new Rotate(90, Rotate.Z_AXIS);
		rotation90.setPivotX(195);
		rotation90.setPivotY(50);
		botJesicaPane.getTransforms().add(rotation90);

		BotPane botMagaretPane = new BotPane(GameLogic.getInstance().getBotMagaret());
		Rotate rotation180 = new Rotate(180, Rotate.Z_AXIS);
		rotation180.setPivotX(180);
		rotation180.setPivotY(60);
		botMagaretPane.getTransforms().add(rotation180);

		BotPane botVandaPane = new BotPane(GameLogic.getInstance().getBotVanda());
		Rotate rotation270 = new Rotate(270, Rotate.Z_AXIS);
		rotation270.setPivotX(165);
		rotation270.setPivotY(50);
		botVandaPane.getTransforms().add(rotation270);

		CenterPane centerPane = new CenterPane();

		// set Pane in root
		root.setCenter(centerPane);
		BorderPane.setAlignment(centerPane, Pos.CENTER);
		root.setBottom(userPane);
		BorderPane.setAlignment(userPane, Pos.CENTER);
		root.setLeft(botJesicaPane);
		BorderPane.setAlignment(botJesicaPane, Pos.CENTER);
		root.setTop(botMagaretPane);
		BorderPane.setAlignment(botMagaretPane, Pos.CENTER);
		root.setRight(botVandaPane);
		BorderPane.setAlignment(botVandaPane, Pos.CENTER);

		// create UpdatableHolder
		Updatable[] updatableArray = { userPane, botJesicaPane, botMagaretPane, botVandaPane, centerPane };
		UpdatableHolder.createInstance(updatableArray);
		// set scene
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("I just wanna pen fan you dai bor?");
		primaryStage.show();

		Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				UpdatableHolder.getInstance().updateScreen();

			}
		}), new KeyFrame(Duration.seconds(0.4)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		Thread t = new Thread(() -> {
			while (true) {
				GameLogic.getInstance().shortProcessing();
				while (!GameLogic.getInstance().isRoundEnd()) {
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
						GameLogic.getInstance().sleepThree();
						GameLogic.getInstance().getCurrentPlayer().setPlayable(true);

					}
					if (GameLogic.getInstance().getCurrentPlayer().isWin()) {
						GameLogic.getInstance().setRoundEnd(true);
						break;
					}
					GameLogic.getInstance().setUpForNewTurn();
					GameLogic.getInstance().shortProcessing();
				}
			}
		});

		t.start();

//		try to run UpdatableHolder.getInstance().updateScreen() at times

//		with thread is also working 
//		Thread t = new Thread(() -> {
//			Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler<ActionEvent>() {
//				@Override
//				public void handle(ActionEvent actionEvent) {
//					UpdatableHolder.getInstance().updateScreen();
//
//				}
//			}), new KeyFrame(Duration.seconds(0.5)));
//			timeline.setCycleCount(Timeline.INDEFINITE);
//			Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					timeline.play();
//				}
//			});
//		});
//		t.start();

//		Too fast
//		AnimationTimer animation = new AnimationTimer() {
//			public void handle(long now) {
//				UpdatableHolder.getInstance().updateScreen();
//			}
//		};
//		animation.start();

// 		not working
//		Thread showTurn = new Thread(() -> {
//			try {
//				while (true) {
//					Thread.sleep(2000);
//					Platform.runLater(new Runnable() {
//						@Override
//						public void run() {
//							UpdatableHolder.getInstance().updateScreen();
//						}
//					});
//				}
//			}
//			catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		});
//		showTurn.start();
//		Thread.sleep(20000);
//		showTurn.interrupt();

//		stopping main crack!

//		while (!GameLogic.getInstance().isGameEnd()) {
//		if (GameLogic.getInstance().isPlayable()) {
//			GameLogic.getInstance().getCurrentPlayer().play();
//		} else {
//			GameLogic.getInstance().setPlayable(true);
//		}
//		GameLogic.getInstance().setUpForNewTurn();
//	}

	}

}
