package gui;

import entity.card.UnitCard;
import entity.player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import logic.GameLogic;

public class UserDeckPane extends GridPane implements Updatable {

	private Player owner;
	private int cardListSize;

	private static final Border NORMAL_BORDER = new Border(
			new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(14), new BorderWidths(12)));
	private static final Border GREEN_BORDER = new Border(
			new BorderStroke(Color.CHARTREUSE, BorderStrokeStyle.SOLID, new CornerRadii(14), new BorderWidths(12)));
	private static final Border RED_BORDER = new Border(
			new BorderStroke(Color.CRIMSON, BorderStrokeStyle.SOLID, new CornerRadii(14), new BorderWidths(12)));

	public UserDeckPane(Player owner) {
		this.owner = owner;
		this.setVgap(8);
		this.setMaxWidth(900);
		this.setPrefHeight(270);
		this.setPadding(new Insets(10));
		this.setBorder(NORMAL_BORDER);
		this.setAlignment(Pos.CENTER);

		BackgroundFill bgFill = new BackgroundFill(Color.MOCCASIN, new CornerRadii(8), Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));
		updateCard();

	}

	@Override
	public void update() {

		// update border
		if (!(GameLogic.getInstance().getCurrentPlayer() == owner)) {
			this.setBorder(NORMAL_BORDER);
		} else if (owner.isPlayable()) {
			if (this.getBorder() == NORMAL_BORDER) {
				this.setBorder(GREEN_BORDER);
			} else {
				this.setBorder(NORMAL_BORDER);
			}
		} else {
			if (this.getBorder() == NORMAL_BORDER) {
				this.setBorder(RED_BORDER);
			} else {
				this.setBorder(NORMAL_BORDER);
			}
		}

		// update cards when they are changed
		if (cardListSize != owner.getCardList().size()) {
			updateCard();
		}

	}

	private void updateCard() {

		this.getChildren().clear();
		cardListSize = owner.getCardList().size();

		// horizontal gap
		if (cardListSize < 9) {
			this.setHgap(15);
		} else if (cardListSize <= 20) {
			this.setHgap(9);
		} else {
			this.setHgap(0);
		}

		// set col, row
		if (cardListSize <= 18) {
			int roww = cardListSize / 9;
			int ind = 0;
			for (int row = 0; row <= roww; row++) {
				for (int col = 0; col < Math.min(cardListSize, 9); col++) {
					if (ind == cardListSize)
						break;
					UnitCard cardToAdd = owner.getCardList().get(ind);
					this.add(new FontCardPane(cardToAdd), col, row);
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
					UnitCard cardToAdd = owner.getCardList().get(ind);
					this.add(new FontCardPane(cardToAdd), col, row);
					ind++;
				}
			}
		}

	}

}
