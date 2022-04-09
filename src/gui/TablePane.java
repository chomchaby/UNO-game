package gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import logic.GameLogic;

public class TablePane extends HBox implements Updatable{

	private BackCardPane cardPilePane;
	private FontCardPane cardOnTablePane;


	public TablePane() {
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		this.setMaxHeight(150);
		this.cardPilePane = new BackCardPane();
		this.cardPilePane.setPrefWidth(80);
		this.cardPilePane.setMaxHeight(110);
		this.cardPilePane.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				cardPilePaneOnClickHandler();
			}
		});
		this.getChildren().add(cardPilePane);

		this.cardOnTablePane = new FontCardPane(GameLogic.getInstance().getCardOnTable());
		this.cardOnTablePane.setMaxHeight(110);
		this.getChildren().add(cardOnTablePane);


	}
	@Override
	public void updateCardInPane() {
		setCardOnTablePane();
	}

	private void cardPilePaneOnClickHandler() {

	}


	private void setCardOnTablePane() {
		this.cardOnTablePane = new FontCardPane(GameLogic.getInstance().getCardOnTable());
		this.cardOnTablePane.setMaxHeight(110);
	}

}
