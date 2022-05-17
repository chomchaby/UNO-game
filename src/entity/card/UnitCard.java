package entity.card;

import javafx.scene.paint.Color;
import sharedObject.ColorLoader;

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

	public static String myColorToString(Color color) {
		if (color == ColorLoader.BLUE) {
			return "BLUE";
		} else if (color == ColorLoader.RED) {
			return "RED";
		} else if (color == ColorLoader.YELLOW) {
			return "YELLOW";
		} else if (color == ColorLoader.GREEN) {
			return "GREEN";
		}
		return "BLACK";
	}

}
