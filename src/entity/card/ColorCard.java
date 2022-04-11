package entity.card;

import java.util.Scanner;

import javafx.scene.paint.Color;
import logic.GameAction;
import logic.GameLogic;

public class ColorCard extends EffectCard {

	public ColorCard() {
		super(-1, Color.WHITE);
		this.gameAction = GameAction.CHANGECOLOR;
	}

	@Override
	public boolean isDrawable() {
		return true;
	}

	@Override
	public void takeAction() {
		GameLogic.getInstance().setColorSelectionState(true);
		GameLogic.getInstance().setNumberState(-1);
	}

	@Override
	public String toString() {
		return "Color" + "(" + getColor().toString() + ")";
	}
}
