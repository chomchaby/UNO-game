package entity.card;

import javafx.scene.paint.Color;
import logic.GameAction;
import logic.GameLogic;

public class ColorCard extends EffectCard {

	public ColorCard() {
		super(13, Color.WHITE);
		this.gameAction = GameAction.CHANGECOLOR;
	}

	@Override
	public boolean isDrawable() {
		return true;
	}

	@Override
	public void takeAction() {
		// create gui for choosing color;
		Color chosenColor = Color.GREEN;
		GameLogic.getInstance().setColorState(chosenColor);
		GameLogic.getInstance().setNumberState(-1);
	}

	@Override
	public String toString() {
		return "Color" + "(" + getColor().toString() + ")";
	}
}
