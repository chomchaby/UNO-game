package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameLogic;
import main.Main;
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

		this.setPadding(new Insets(20, 0, 10, 0));
		BackgroundFill bgFill = new BackgroundFill(Color.STEELBLUE, new CornerRadii(40), Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));

		// title //
		title = new Text("SCORE BOARD");
		title.setStyle("-fx-fill: white; -fx-font-weight: bold; -fx-font-size: 30;");
		this.getChildren().add(title);
		StackPane.setAlignment(title, Pos.TOP_CENTER);

		// score table //
		scoreTable = new GridPane();
		scoreTable.setAlignment(Pos.CENTER);
		scoreTable.setVgap(4);
		scoreTable.setHgap(50);

		// header
		Text name = new Text("Name");
		name.setStyle("-fx-fill: white;-fx-font-weight: bold;-fx-font-size: 18;");
		Text score = new Text("Score");
		score.setStyle("-fx-fill: white;-fx-font-weight: bold;-fx-font-size: 18;");

		// name column
		Text username = new Text(GameLogic.getInstance().getUser().getName() + " (You)");
		Text jesica = new Text(GameLogic.getInstance().getBotJesica().getName());
		Text magaret = new Text(GameLogic.getInstance().getBotMagaret().getName());
		Text vanda = new Text(GameLogic.getInstance().getBotVanda().getName());
		username.setStyle("-fx-fill: white;-fx-font-size: 18;");
		jesica.setStyle("-fx-fill: white;-fx-font-size: 18;");
		magaret.setStyle("-fx-fill: white;-fx-font-size: 18;");
		vanda.setStyle("-fx-fill: white;-fx-font-size: 18;");
		// plus column
		userPlus = new Text("");
		jesicaPlus = new Text("");
		magaretPlus = new Text("");
		vandaPlus = new Text("");
		userPlus.setStyle("-fx-fill: white;-fx-font-size: 18; ");
		jesicaPlus.setStyle("-fx-fill: white;-fx-font-size: 18; ");
		magaretPlus.setStyle("-fx-fill: white;-fx-font-size: 18; ");
		vandaPlus.setStyle("-fx-fill: white;-fx-font-size: 18; ");
		// score column
		userScore = new Text("0");
		jesicaScore = new Text("0");
		magaretScore = new Text("0");
		vandaScore = new Text("0");
		userScore.setStyle("-fx-fill: white;-fx-font-size: 18;");
		jesicaScore.setStyle("-fx-fill: white;-fx-font-size: 18;");
		magaretScore.setStyle("-fx-fill: white;-fx-font-size: 18;");
		vandaScore.setStyle("-fx-fill: white;-fx-font-size: 18;");

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
		scoreTable.setTranslateY(5);

		// button //
		btn = new Button("Continue");
		btn.setPrefWidth(100);
		btn.setStyle("-fx-font-size:15;");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AudioLoader.mouseClick1Sound.play();
				// set disable until next round or game end
				btn.setDisable(true);
				if (GameLogic.getInstance().getWinner() != null) {
					// gameRun thread is terminated
					// the same user play new game
					// set score of all players to zero
					// reset cards in game
					GameLogic.getInstance().start(GameLogic.getInstance().getUser().getName());
					// start gameRun thread again
//					Main2.getRidOfGameRun();
					Main.runGame();
					// use the old gamePlayScene, reset scoreTable
					resetScoreTable();
				} else {
					// save present scores
					// go to second while loop again (game run thread)
					GameLogic.getInstance().newGame();
				}
				// set isRoundEnd() to false
				// set isScoreShown() to false
				// automatically set scoreBoard invisible when new round or new game begin
			}
		});
		btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				AudioLoader.mouseEnterSound.play();
				btn.setStyle("-fx-font-size:15; -fx-font-weight: bold; -fx-cursor: hand;");
				btn.setPrefWidth(120);
			}
		});
		btn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				btn.setStyle("-fx-font-size:15;");
				btn.setPrefWidth(100);
			}
		});
		this.getChildren().add(btn);
		StackPane.setAlignment(btn, Pos.BOTTOM_CENTER);
		btn.setDisable(true);

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
			btn.setText("New Game");
			System.err.println("$$$$$ Final Winner : " + GameLogic.getInstance().getWinner().getName() + " $$$$$");
		}
		// enable btn
		btn.setDisable(false);
	}

	private void resetScoreTable() {
		// title //
		title.setText("SCORE BOARD");
		// score table //
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
		// btn //
		btn.setText("Continue");
	}

}
