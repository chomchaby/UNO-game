package gui;

import entity.card.UnitCard;
import entity.player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameLogic;
import sharedObject.ImageLoader;

public class UserPane extends VBox implements Updatable {

	private Player user;
//	private int cardListSize;
	private GridPane cardPane;
	private Text nameText;

	private static final Border NORMAL_BORDER = new Border(
			new BorderStroke(Color.TAN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(12)));
	private static final Border GREEN_BORDER = new Border(
			new BorderStroke(Color.CHARTREUSE, BorderStrokeStyle.SOLID,  CornerRadii.EMPTY, new BorderWidths(12)));
	private static final Border RED_BORDER = new Border(
			new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,  CornerRadii.EMPTY, new BorderWidths(12)));

	public UserPane(Player user) {
		this.user = user;

		// set up UserPane
		this.setMaxWidth(830);
		this.setPrefWidth(830);
		this.setMaxHeight(300);
		this.setPrefHeight(300);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);

		// set up cardPane
		cardPane = new GridPane();
		this.cardPane.setVgap(8);
		this.cardPane.setMaxWidth(830);
		this.cardPane.setPrefHeight(260);
		this.cardPane.setPadding(new Insets(10));
		this.cardPane.setBorder(NORMAL_BORDER);
		this.cardPane.setAlignment(Pos.CENTER);

//		BackgroundFill bgFill = new BackgroundFill(Color.MOCCASIN, new CornerRadii(20), Insets.EMPTY);
//		BackgroundFill[] bgFillA = { bgFill };
//		this.cardPane.setBackground(new Background(bgFillA));
		BackgroundImage bImg = new BackgroundImage(ImageLoader.woodTableImg, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		Background bGround = new Background(bImg);
		this.cardPane.setBackground(bGround);
//		this.setStyle(getAccessibleHelp());

		// create and set up nameText
		this.nameText = new Text("Player : " + user.getName());
		this.nameText.setStyle("-fx-font-size: 20;");
		this.nameText.maxHeight(35);
		this.nameText.setVisible(true);

		// add all field into UserPane
		this.getChildren().addAll(nameText, cardPane);

		// first time update to complete cardPane
		update();

	}

	@Override
	public void update() {

		// update border
		if (!(GameLogic.getInstance().getCurrentPlayer() == user)) {
			this.cardPane.setBorder(NORMAL_BORDER);
			this.nameText.setStyle("-fx-font-size: 20;");
			this.nameText.setVisible(true);
		} else if (user.isPlayable()) {
			if (this.cardPane.getBorder() == NORMAL_BORDER) {
				this.cardPane.setBorder(GREEN_BORDER);
				this.nameText.setStyle("-fx-font-size: 24; -fx-fill: green; -fx-font-weight: bold;");
				this.nameText.setVisible(true);
			} else {
				this.cardPane.setBorder(NORMAL_BORDER);
				this.nameText.setVisible(false);
			}
		} else {
			if (this.cardPane.getBorder() == NORMAL_BORDER) {
				this.cardPane.setBorder(RED_BORDER);
				this.nameText.setStyle("-fx-font-size: 24; -fx-fill: red; -fx-font-weight: bold;");
				this.nameText.setVisible(true);
			} else {
				this.cardPane.setBorder(NORMAL_BORDER);
				this.nameText.setVisible(false);
			}
		}

		// update only cards when they are changed
//		if (cardListSize != user.getCardList().size()) {
		updateCard();
//		}

	}

	private void updateCard() {

		this.cardPane.getChildren().clear();
		int cardListSize = user.getCardList().size();

		// horizontal gap
		if (cardListSize < 9) {
			this.cardPane.setHgap(15);
		} else if (cardListSize <= 20) {
			this.cardPane.setHgap(9);
		} else {
			this.cardPane.setHgap(0);
		}

		// set col, row
		if (cardListSize <= 18) {
			int roww = cardListSize / 9;
			int ind = 0;
			for (int row = 0; row <= roww; row++) {
				for (int col = 0; col < Math.min(cardListSize, 9); col++) {
					if (ind == cardListSize)
						break;
					UnitCard cardToAdd = user.getCardList().get(ind);
					this.cardPane.add(new FontCardPane(cardToAdd), col, row);
					ind++;
				}
			}
		} else {
			int column = (cardListSize + 1) / 2;
			int ind = 0;
			for (int row = 0; row <= 1; row++) {
				for (int col = 0; col < column; col++) {
					if (ind == cardListSize)
						break;
					UnitCard cardToAdd = user.getCardList().get(ind);
					this.cardPane.add(new FontCardPane(cardToAdd), col, row);
					ind++;
				}
			}
		}

	}

}
