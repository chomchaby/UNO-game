package gui;

import entity.player.Bot;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.GameLogic;

public class CenterPane extends StackPane implements Updatable {

	private TablePane tablePane;
	private ColorSelectionPane colorSelectionPane;
	private Text challengeResult;
	private ScorePane scoreBoard;

	private VBox gameEndText;
	private boolean hasGameEndText;

	public CenterPane() {

		this.setPadding(new Insets(10, 0, 10, 0));
		this.setAlignment(Pos.CENTER);

		// create tablePane
		tablePane = new TablePane();

		// create colorSelectionPane
		colorSelectionPane = new ColorSelectionPane();

		// create challengeResult
		challengeResult = new Text("challenge result");

		// create scoreBoard
		scoreBoard = new ScorePane();
		scoreBoard.setVisible(false);

		// add fields
		this.getChildren().addAll(tablePane, colorSelectionPane, challengeResult, scoreBoard);
		StackPane.setAlignment(tablePane, Pos.CENTER);
		StackPane.setAlignment(colorSelectionPane, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(challengeResult, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(scoreBoard, Pos.CENTER);
		challengeResult.setTranslateY(-18);
//		scoreBoard.setVisible(false);

		update();

	}

	@Override
	public void update() {
		// score board
		if (GameLogic.getInstance().isGameEnd() && tablePane.isVisible()) {
			// set score once after isGameEnd() becomes true
			scoreBoard.setScore();
			scoreBoard.setVisible(true);
		}
		if (!GameLogic.getInstance().isGameEnd()) {
			scoreBoard.setVisible(false);
		}
		
		// tablePane 
		tablePane.update();
		if (GameLogic.getInstance().isGameEnd()) {
			tablePane.setVisible(false);
		}
		else {
			tablePane.setVisible(true);
		}
		// colorSelectionPane and challengeResult
		setColorSelectionAndChallengeResult();
		

	}

	private void setColorSelectionAndChallengeResult() {

		if (GameLogic.getInstance().isColorSelectionState()) {
			colorSelectionPane.setVisible(true);
		} else if (GameLogic.getInstance().isChallengeState()) {
			// end of color selection
			colorSelectionPane.setVisible(false);
			if (GameLogic.getInstance().isChallengeWin()) {
				challengeResult.setText("Challenge Result : WIN (" + GameLogic.getInstance().getNextPlayer().getName()
						+ "draws 4 cards)");
			} else {
				challengeResult.setText("Challenge Result : LOSS ("
						+ GameLogic.getInstance().getCurrentPlayer().getName() + "draws 2 cards)");
			}
			challengeResult.setVisible(true);
		} else {
			colorSelectionPane.setVisible(false);
			challengeResult.setVisible(false);
		}
	}


//	private void setColorSelectionAndChallengeResult() {
//		// remove if the game is over
//		if (GameLogic.getInstance().isGameEnd()) {
//			this.getChildren().remove(colorSelectionPane);
//			this.getChildren().remove(challengeResultText);
//		}
//		// set ColorSelection Pane
//		else if (GameLogic.getInstance().isColorSelectionState() && !(hasColorSelectionText)) {
//			colorSelectionPane = new ColorSelectionPane();
//			this.getChildren().add(colorSelectionPane);
//			hasColorSelectionText = true;
//
//			// remove ColorSelection pane
//		} else if (!GameLogic.getInstance().isColorSelectionState()) {
//
//			if (hasColorSelectionText) {
//				this.getChildren().remove(colorSelectionPane);
//				hasColorSelectionText = false;
//				// create challenge result text
//			} else if (GameLogic.getInstance().isChallengeState() && !hasChallegeResultText) {
//				String result = "";
//				if (GameLogic.getInstance().isChallengeWin()) {
//					result = "Congrats! " + GameLogic.getInstance().getCurrentPlayer().getName()
//							+ "'s guess is correct. " + "\n" + GameLogic.getInstance().getNextPlayer().getName()
//							+ " has no "
//							+ GameLogic.getInstance().myColorToString(GameLogic.getInstance().getChallengeColor())
//							+ " card." + "\n" + GameLogic.getInstance().getNextPlayer().getName()
//							+ ", please pick 4 cards.";
//
//				} else {
//					result = "Oops... " + GameLogic.getInstance().getCurrentPlayer().getName()
//							+ "'s guess is not correct. " + "\n" + GameLogic.getInstance().getNextPlayer().getName()
//							+ " has some "
//							+ GameLogic.getInstance().myColorToString(GameLogic.getInstance().getChallengeColor())
//							+ " cards." + "\n" + GameLogic.getInstance().getCurrentPlayer().getName()
//							+ ", please pick 2 cards.";
//					;
//				}
//				challengeResultText = new Text(result);
//				this.getChildren().add(challengeResultText);
//				hasChallegeResultText = true;
//
//				// remove challenge result text
//			} else if (!GameLogic.getInstance().isChallengeState() && hasChallegeResultText) {
//				this.getChildren().remove(challengeResultText);
//				hasChallegeResultText = false;
//			}
//		}
//	}

//	private void setGameEndText() {

//		if (GameLogic.getInstance().isGameEnd()) {
//
//			this.getChildren().remove(gameEndText);
//
//			gameEndText = new VBox();
//			gameEndText.setSpacing(10);
//			gameEndText.setAlignment(Pos.CENTER);
//
//			String endPicURL;
//			String gameResult;
//
//			if (GameLogic.getInstance().getCurrentPlayer() instanceof Bot) {
//				endPicURL = ClassLoader.getSystemResource("image/lose.png").toString();
//				gameResult = "LOSE";
//			} else {
//				endPicURL = ClassLoader.getSystemResource("image/win.jpg").toString();
//				gameResult = "WIN";
//			}
//			gameEndText.getChildren().add(new Text("   ---" + "YOU " + gameResult + "   ---"));
//
//			ImageView imageView = new ImageView(new Image(endPicURL));
//			imageView.setFitHeight(120);
//			imageView.setPreserveRatio(true);
//			gameEndText.getChildren().add(imageView);
//
//			gameEndText.getChildren()
//					.add(new Text("The winner is " + GameLogic.getInstance().getCurrentPlayer().getName()));
//
//			Button newGameButton = new Button("New Game");
//			newGameButton.setPrefWidth(100);
//			newGameButton.setOnAction(new EventHandler<ActionEvent>() {
//				@Override
//				public void handle(ActionEvent event) {
//					GameLogic.getInstance().setGameEnd(false);
//					GameLogic.getInstance().newGame();
//				}
//			});
//			gameEndText.getChildren().add(newGameButton);
//
//			this.getChildren().add(gameEndText);
//			hasGameEndText = true;
//
//		} else if (!GameLogic.getInstance().isGameEnd()) {
//			this.getChildren().remove(gameEndText);
//		}
//
//	}
//	private void initializeNewGameButton() {
//		
//		
//		if (GameLogic.getInstance().isGameEnd() && !hasNewGameButton) {
//			newGameButton = new Button("New Game");
//			newGameButton.setPrefWidth(100);
//			newGameButton.setOnAction(new EventHandler<ActionEvent>() {
//				@Override
//				public void handle(ActionEvent event) {
//					GameLogic.getInstance().setGameEnd(false);
//					GameLogic.getInstance().newGame();
//				}
//			});
//			this.getChildren().add(newGameButton);
//			hasNewGameButton = true;
//			
//		} else if (!GameLogic.getInstance().isGameEnd()) {
//			this.getChildren().remove(newGameButton);
//			hasNewGameButton = false;
//		}
//
//	}

}
