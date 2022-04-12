package entity.card;

import javafx.scene.paint.Color;
import logic.GameAction;
import logic.GameLogic;

public class RotateCard extends EffectCard {

	public RotateCard(Color color) {
		super(11, color);
		this.gameAction = GameAction.ROTATE;
	}

	@Override
	public boolean isDrawable() {
		// same as NormalCard
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
	public void takeAction() {
		// for User, the animation might stop for one second, for me it's fine 555
		GameLogic.getInstance().shortProcessing();
		GameLogic.getInstance().setClockwise(!GameLogic.getInstance().isClockwise());
	}

	@Override
	public String toString() {

		return "Rotate Card" + " (" + GameLogic.getInstance().myColorToString(getColor()) + ")";
	}
}
