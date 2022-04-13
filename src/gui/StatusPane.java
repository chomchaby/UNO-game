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

	private boolean hasColorSelectionText;
	private ColorSelection colorSelectionText;

	private boolean hasChallegeResultText;
	private Text challengeResultText;
	
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

		// setting currentPlayerText
		currentPlayerText = new Text();
		setCurrentPlayerText();
		this.getChildren().addAll(imageView, tablePane, currentPlayerText);

		// set colorSelectionText & challengeResultText
		hasColorSelectionText = false;
		hasChallegeResultText = false;

	}

	@Override
	public void update() {
		tablePane.update();
		// game is over
		if (GameLogic.getInstance().isGameEnd()) {
			this.getChildren().clear();
			initializeGameEndText();
			initializeNewGameButton();
		}
		setCurrentPlayerText();
		initializeColorSelectionText();
	}

	private void setCurrentPlayerText() {
		if (GameLogic.getInstance().getCurrentPlayer().isPlayable()) {
			currentPlayerText.setText("Player Turn : " + GameLogic.getInstance().getCurrentPlayer().getName());
		} else {
			currentPlayerText
					.setText("Player Turn : " + GameLogic.getInstance().getCurrentPlayer().getName() + "  (Blocked)");
		}

	}

	private void initializeColorSelectionText() {
		// set ColorSelection Pane
		if (GameLogic.getInstance().isColorSelectionState() && !(hasColorSelectionText)) {
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
		String endPicURL;
		String gameResult;
		if (GameLogic.getInstance().getCurrentPlayer() instanceof Bot) {
			endPicURL = ClassLoader.getSystemResource("lose.png").toString();
			gameResult = "lose";
		} else {
			endPicURL = ClassLoader.getSystemResource("win.jpg").toString();
			gameResult = "win";
		}
		this.getChildren().add(new Text("You " + gameResult + "!"));

		ImageView imageView = new ImageView(new Image(endPicURL));
		imageView.setFitWidth(200);
		imageView.setPreserveRatio(true);
		this.getChildren().add(imageView);
		
		this.getChildren().add(new Text("The winner is " + GameLogic.getInstance().getCurrentPlayer().getName()));

	}

	private void initializeNewGameButton() {
		Button newGameButton = new Button("New Game");
		newGameButton.setPrefWidth(100);
		newGameButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				GameLogic.getInstance().setGameEnd(false);
				GameLogic.getInstance().newGame();
			}
		});
		this.getChildren().add(newGameButton);
	}

}
