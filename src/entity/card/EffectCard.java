package entity.card;

import javafx.scene.paint.Color;

public abstract class EffectCard extends UnitCard implements Actionable {

	protected EffectCard(int number, Color color) {
		super(number, color);
	}

}
	