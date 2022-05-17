package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import logic.GameLogic;
import logic.GameRun;
import sharedObject.AudioLoader;

public class ScorePane extends StackPane {

	private Text title;

	private GridPane scoreTable;

	private Text userPlus;
	private Text jesicaPlus;
	private Text magaretPlus;
	private Text vandaPlus;

	private Text userScore;
	private Text jesicaScore;
	private Text magaretScore;
	private Text vandaScore;

	private Button btn;

	public ScorePane() {

		// title
		title = new Text("SCORE BOARD");
		title.setStyle("-fx-font-weight: bold; -fx-font-size: 30;");
		this.getChildren().add(title);
		StackPane.setAlignment(title, Pos.TOP_CENTER);

		// score table
		scoreTable = new GridPane();
		scoreTable.setAlignment(Pos.CENTER);
		scoreTable.setVgap(5);
		scoreTable.setHgap(50);

		// header
		Text name = new Text("Name");
		name.setStyle("-fx-font-weight: bold;-fx-font-size: 20;");
		Text score = new Text("Score");
		score.setStyle("-fx-font-weight: bold;-fx-font-size: 20;");

		// name column
		Text username = new Text(GameLogic.getInstance().getUser().getName() + " (You)");
		Text jesica = new Text(GameLogic.getInstance().getBotJesica().getName());
		Text magaret = new Text(GameLogic.getInstance().getBotMagaret().getName());
		Text vanda = new Text(GameLogic.getInstance().getBotVanda().getName());
		username.setStyle("-fx-font-size: 20;");
		jesica.setStyle("-fx-font-size: 20;");
		magaret.setStyle("-fx-font-size: 20;");
		vanda.setStyle("-fx-font-size: 20;");
		// plus column
		userPlus = new Text("");
		jesicaPlus = new Text("");
		magaretPlus = new Text("");
		vandaPlus = new Text("");
		userPlus.setStyle("-fx-font-size: 20; -fx-stroke: green;");
		jesicaPlus.setStyle("-fx-font-size: 20; -fx-stroke: green;");
		magaretPlus.setStyle("-fx-font-size: 20; -fx-stroke: green;");
		vandaPlus.setStyle("-fx-font-size: 20; -fx-stroke: green;");
		// score column
		userScore = new Text("0");
		jesicaScore = new Text("0");
		magaretScore = new Text("0");
		vandaScore = new Text("0");
		userScore.setStyle("-fx-font-size: 20;");
		jesicaScore.setStyle("-fx-font-size: 20;");
		magaretScore.setStyle("-fx-font-size: 20;");
		vandaScore.setStyle("-fx-font-size: 20;");

		// add fields
		// name
		scoreTable.add(name, 0, 0);
		scoreTable.add(username, 0, 1);
		scoreTable.add(jesica, 0, 2);
		scoreTable.add(magaret, 0, 3);
		scoreTable.add(vanda, 0, 4);
		// plus
		scoreTable.add(userPlus, 1, 1);
		scoreTable.add(jesicaPlus, 1, 2);
		scoreTable.add(magaretPlus, 1, 3);
		scoreTable.add(vandaPlus, 1, 4);
		// score
		scoreTable.add(score, 2, 0);
		GridPane.setHalignment(score, HPos.RIGHT);
		scoreTable.add(userScore, 2, 1);
		scoreTable.add(jesicaScore, 2, 2);
		scoreTable.add(magaretScore, 2, 3);
		scoreTable.add(vandaScore, 2, 4);
		GridPane.setHalignment(userScore, HPos.RIGHT);
		GridPane.setHalignment(jesicaScore, HPos.RIGHT);
		GridPane.setHalignment(magaretScore, HPos.RIGHT);
		GridPane.setHalignment(vandaScore, HPos.RIGHT);
		this.getChildren().add(scoreTable);
		StackPane.setAlignment(scoreTable, Pos.CENTER);
		scoreTable.setTranslateY(10);

		// button
		// continueBtn
		btn = new Button("Continue");
		btn.setStyle("-fx-font-size:15");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AudioLoader.mouseClick1Sound.play();
				if (GameLogic.getInstance().getWinner() != null) {
					// the same user play new game
					// set score of all players to zero
					// reset cards in game
					GameLogic.getInstance().start(GameLogic.getInstance().getUser().getName());
					// create new GameRun thread
					new GameRun().start();
					// use the old gamePlayScene, reset scoreTable
					resetScoreTable();
				} else {
					GameLogic.getInstance().newGame();
					GameLogic.getInstance().setRoundEnd(false);
				}
			}
		});
		btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				AudioLoader.mouseEnterSound.play();
				btn.setStyle("-fx-font-size:15; -fx-cursor: hand;");
			}
		});
		this.getChildren().add(btn);
		StackPane.setAlignment(btn, Pos.BOTTOM_CENTER);

	}

	public void setScore() {
		int score = GameLogic.getInstance().getDeckSize() - (GameLogic.getInstance().getCardPile().size() + 1);
		if (GameLogic.getInstance().getCurrentPlayer() == GameLogic.getInstance().getUser()) {
			userPlus.setText("(+" + score + ")");
			jesicaPlus.setText("");
			magaretPlus.setText("");
			vandaPlus.setText("");
			GameLogic.getInstance().getUser().setScore(GameLogic.getInstance().getUser().getScore() + score);
			userScore.setText("" + GameLogic.getInstance().getUser().getScore());
		} else if (GameLogic.getInstance().getCurrentPlayer() == GameLogic.getInstance().getBotJesica()) {
			jesicaPlus.setText("(+" + score + ")");
			userPlus.setText("");
			magaretPlus.setText("");
			vandaPlus.setText("");
			GameLogic.getInstance().getBotJesica().setScore(GameLogic.getInstance().getBotJesica().getScore() + score);
			jesicaScore.setText("" + GameLogic.getInstance().getBotJesica().getScore());
		} else if (GameLogic.getInstance().getCurrentPlayer() == GameLogic.getInstance().getBotMagaret()) {
			magaretPlus.setText("(+" + score + ")");
			userPlus.setText("");
			jesicaPlus.setText("");
			vandaPlus.setText("");
			GameLogic.getInstance().getBotMagaret()
					.setScore(GameLogic.getInstance().getBotMagaret().getScore() + score);
			magaretScore.setText("" + GameLogic.getInstance().getBotMagaret().getScore());
		} else {
			vandaPlus.setText("(+" + score + ")");
			userPlus.setText("");
			jesicaPlus.setText("");
			magaretPlus.setText("");
			GameLogic.getInstance().getBotVanda().setScore(GameLogic.getInstance().getBotVanda().getScore() + score);
			vandaScore.setText("" + GameLogic.getInstance().getBotVanda().getScore());
		}
		System.err.println("***** " + GameLogic.getInstance().getCurrentPlayer().getName() + " Win this Round*****");
		System.out.println("Score : " + GameLogic.getInstance().getCurrentPlayer().getScore());

		// check if whether the game is end
		if (GameLogic.getInstance().getWinner() != null) {
			title.setText(GameLogic.getInstance().getWinner().getName() + " WIN !");
			btn.setText("NEW GAME");
			System.err.println("$$$$$ Final Winner : " + GameLogic.getInstance().getWinner().getName() + " $$$$$");
		}
	}

	private void resetScoreTable() {
		// title
		title.setText("SCORE BOARD");
		// plus column
		userPlus.setText("");
		jesicaPlus.setText("");
		magaretPlus.setText("");
		vandaPlus.setText("");
		// score column
		userScore.setText("0");
		jesicaScore.setText("0");
		magaretScore.setText("0");
		vandaScore.setText("0");
		// btn
		btn.setText("CONTINUE");
	}
}
