package entity.card;

import javafx.scene.paint.Color;
import logic.GameAction;
import logic.GameLogic;

public class NormalCard extends UnitCard {

	public NormalCard(int number, Color color) {
		super(number, color);
		this.gameAction = GameAction.NONE;
	}

	@Override
	public boolean isDrawable() {

		if (GameLogic.getInstance().getCardOnTable().getColor() == Color.BLACK) {
			return true;
		}
		else if (GameLogic.getInstance().getColorState() == this.getColor()
				|| GameLogic.getInstance().getNumberState() == this.getNumber()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return Integer.toString(getNumber()) + "(" + getColor() + ")";
	}
}
