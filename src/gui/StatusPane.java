package gui;

import javafx.geometry.Pos;
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
		setCurrentPlayerText();
		setColorSelectionText();
	}

	private void setCurrentPlayerText() {
		if (GameLogic.getInstance().getCurrentPlayer().isPlayable()) {
			currentPlayerText.setText("Player Turn : " + GameLogic.getInstance().getCurrentPlayer().getName());
		} else {
			currentPlayerText
					.setText("Player Turn : " + GameLogic.getInstance().getCurrentPlayer().getName() + "  (Blocked)");
		}

	}

	private void setColorSelectionText() {
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

}
