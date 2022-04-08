package gui;

import entity.card.UnitCard;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.GameLogic;

public class UserCardPane extends BotCardPane {

	public UserCardPane(UnitCard card) {
		super(card);
		this.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				onClickHandler();
			}
		});

	}

	private void onClickHandler() {
		if (GameLogic.getInstance().isGameEnd())
			return;
		else {
			System.out.println("Press me again");
		}
	}

	@Override
	protected void draw() {
		BackgroundFill bgFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		this.setBackground(new Background(bgFillA));
		Rectangle shape = new Rectangle(70, 100, card.getColor());
		shape.setArcWidth(8);
		shape.setArcHeight(8);
		this.getChildren().add(shape);
		Text cardText = new Text(Integer.toString(card.getNumber()));
		cardText.setStyle("-fx-font-style: italic; -fx-font-weight: bold; -fx-font-size: 50; -fx-font-color: yellow");
		this.getChildren().add(cardText);

	}

}
