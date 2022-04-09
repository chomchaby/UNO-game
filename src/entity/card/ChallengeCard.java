package entity.card;

import javafx.scene.paint.Color;
import logic.GameAction;

public class ChallengeCard extends EffectCard {

	public ChallengeCard() {
		super(14, Color.WHITE);
		this.gameAction = GameAction.CHALLENGE;
	}

	@Override
	public boolean isDrawable() {
		return true;
	}

	@Override
	public void takeAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "Challenge" + "(" + getColor().toString() + ")";
	}
}
