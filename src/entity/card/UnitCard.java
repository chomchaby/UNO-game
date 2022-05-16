package entity.card;

import javafx.scene.paint.Color;

public abstract class UnitCard {
	private int number;
	private Color color;

	public UnitCard(int number, Color color) {
		this.number = number;
		this.color = color;
	}

	public abstract boolean isPlaceable();

	public abstract String toString();

	public int getNumber() {
		return this.number;
	}

	public Color getColor() {
		return this.color;
	}

}
