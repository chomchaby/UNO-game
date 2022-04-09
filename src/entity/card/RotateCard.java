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
		if (GameLogic.getInstance().getColorState() == this.getColor()
				|| GameLogic.getInstance().getNumberState() == this.getNumber())
			return true;
		return false;
	}

	@Override
	public void takeAction() {
		GameLogic.getInstance().setClockwise(!GameLogic.getInstance().isClockwise());
	}

	@Override
	public String toString() {
		return "Rotate" + "(" + getColor().toString() + ")";
	}
}