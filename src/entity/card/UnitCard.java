package entity.card;

import javafx.scene.paint.Color;
import logic.GameAction;

public abstract class UnitCard {
	private int number;
	private Color color;
	protected GameAction gameAction;

	public UnitCard(int number, Color color) {
		this.number = number;
		this.color = color;
	}

	public abstract boolean isDrawable();
	
	public abstract String toString();

	public int getNumber() {
		return this.number;
	}

	public Color getColor() {
		return this.color;
	}

	public GameAction getAction() {
		return this.gameAction;
	}



}
