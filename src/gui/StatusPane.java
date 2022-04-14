package gui;

import entity.player.Bot;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.GameLogic;

public class StatusPane extends VBox implements Updatable {

	private final String logoURL;
	private TablePane tablePane;

	private Text currentPlayerText;
	private boolean hasCurrentPlayerText;

	private ColorSelection colorSelectionText;
	private boolean hasColorSelectionText;
	private Text challengeResultText;
	private boolean hasChallegeResultText;

	private VBox gameEndText;
	private boolean hasGameEndText;

	public StatusPane() {

		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		// setting logo
		logoURL = ClassLoader.getSystemResource("java.png").toString();
		Image logoPNG = new Image(logoURL);
		ImageView imageView = new ImageView(logoPNG);
		imageView.setFitHeight(50);
		imageView.setFitWidth(80);

		// create tablePane
		tablePane = new TablePane();

		this.getChildren().addAll(imageView, tablePane);
		update();

	}

	@Override
	public void update() {
		tablePane.update();
		initializeCurrentPlayerText();
		initializeColorSelectionText();
		initializeGameEndText();
	}

	private void initializeCurrentPlayerText() {
		// set text to be added
		String text;
		if (GameLogic.getInstance().getCurrentPlayer().isPlayable()) {
			text = "Player Turn : " + GameLogic.getInstance().getCurrentPlayer().getName();
		} else {
			text = "Player Turn : " + GameLogic.getInstance().getCurrentPlayer().getName() + " (Blocked)";
		}

		if (GameLogic.getInstance().isGameEnd()) {
			this.getChildren().remove(currentPlayerText);
			hasCurrentPlayerText = false;
		} else if (!GameLogic.getInstance().isGameEnd()) {
			if (!hasCurrentPlayerText) {
				currentPlayerText = new Text();
				currentPlayerText.setText(text);
				this.getChildren().add(currentPlayerText);
				hasCurrentPlayerText = true;
			} else {
				currentPlayerText.setText(text);
			}
		}
	}

	private void initializeColorSelectionText() {
		// remove if the game is over
		if (GameLogic.getInstance().isGameEnd()) {
			this.getChildren().remove(colorSelectionText);
			this.getChildren().remove(challengeResultText);
		}
		// set ColorSelection Pane
		else if (GameLogic.getInstance().isColorSelectionState() && !(hasColorSelectionText)) {
			colorSelectionText = new ColorSelection();
			this.getChildren().add(colorSelectionText);
			hasColorSelectionText = true;

			// remove ColorSelection pane
		} else if (!GameLogic.getInstance().isColorSelectionState()) {

			if (hasColorSelectionText) {
				this.getChildren().remove(colorSelectionText);
				hasColorSelectionText = false;
				// create challenge result text
			} else if (GameLogic.getInstance().isChallengeState() && !hasChallegeResultText) {
				String result = "";
				if (GameLogic.getInstance().isChallengeWin()) {
					result = "Congrats! " + GameLogic.getInstance().getCurrentPlayer().getName()
							+ "'s guess is correct. " + "\n" + GameLogic.getInstance().getNextPlayer().getName()
							+ " has no "
							+ GameLogic.getInstance().myColorToString(GameLogic.getInstance().getChallengeColor())
							+ " card." + "\n" + GameLogic.getInstance().getNextPlayer().getName()
							+ ", please pick 4 cards.";

				} else {
					result = "Oops... " + GameLogic.getInstance().getCurrentPlayer().getName()
							+ "'s guess is not correct. " + "\n" + GameLogic.getInstance().getNextPlayer().getName()
							+ " has some "
							+ GameLogic.getInstance().myColorToString(GameLogic.getInstance().getChallengeColor())
							+ " cards." + "\n" + GameLogic.getInstance().getCurrentPlayer().getName()
							+ ", please pick 2 cards.";
					;
				}
				challengeResultText = new Text(result);
				this.getChildren().add(challengeResultText);
				hasChallegeResultText = true;

				// remove challenge result text
			} else if (!GameLogic.getInstance().isChallengeState() && hasChallegeResultText) {
				this.getChildren().remove(challengeResultText);
				hasChallegeResultText = false;
			}
		}
	}

	private void initializeGameEndText() {

		if (GameLogic.getInstance().isGameEnd()) {

			this.getChildren().remove(gameEndText);

			gameEndText = new VBox();
			gameEndText.setSpacing(10);
			gameEndText.setAlignment(Pos.CENTER);

			String endPicURL;
			String gameResult;

			if (GameLogic.getInstance().getCurrentPlayer() instanceof Bot) {
				endPicURL = ClassLoader.getSystemResource("lose.png").toString();
				gameResult = "LOSE";
			} else {
				endPicURL = ClassLoader.getSystemResource("win.jpg").toString();
				gameResult = "WIN";
			}
			gameEndText.getChildren().add(new Text("   ---" + "YOU " + gameResult + "   ---"));

			ImageView imageView = new ImageView(new Image(endPicURL));
			imageView.setFitHeight(120);
			imageView.setPreserveRatio(true);
			gameEndText.getChildren().add(imageView);

			gameEndText.getChildren()
					.add(new Text("The winner is " + GameLogic.getInstance().getCurrentPlayer().getName()));

			Button newGameButton = new Button("New Game");
			newGameButton.setPrefWidth(100);
			newGameButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					GameLogic.getInstance().setGameEnd(false);
					GameLogic.getInstance().newGame();
				}
			});
			gameEndText.getChildren().add(newGameButton);

			this.getChildren().add(gameEndText);
			hasGameEndText = true;

		} else if (!GameLogic.getInstance().isGameEnd()) {
			this.getChildren().remove(gameEndText);
		}

	}
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
