package entity.card;

import javafx.scene.paint.Color;
import logic.GameAction;
import logic.GameLogic;

public class DrawCard extends EffectCard {

	public DrawCard(Color color) {
		super(12, color);
		this.gameAction = GameAction.PICK;
	}

	@Override
	public boolean isPlaceable() {
		// same as NormalCard
		if (GameLogic.getInstance().getColorState() == Color.BLACK) {
			return true;
		} else if (GameLogic.getInstance().getColorState() == this.getColor()
				|| GameLogic.getInstance().getNumberState() == this.getNumber()) {
			return true;
		}
		return false;
	}

	@Override
	public void takeAction() {
		if (GameLogic.getInstance().getCurrentPlayer() != GameLogic.getInstance().getUser()) {
			// take time to show effect...
			GameLogic.getInstance().longProcessing();
		}
		GameLogic.getInstance().getNextPlayer().drawCard(2);
	}

	@Override
	public String toString() {

		return "Draw Card" + " (" + GameLogic.getInstance().myColorToString(getColor()) + ")";
	}
}
