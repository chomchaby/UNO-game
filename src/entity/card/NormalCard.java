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
		// in case at the beginning cardOnTable is black card 
		if (GameLogic.getInstance().getColorState() == Color.BLACK) {
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

		return Integer.toString(getNumber()) + " (" + GameLogic.getInstance().myColorToString(getColor()) + ")";
	}
}
