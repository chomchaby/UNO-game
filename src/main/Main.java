package main;

import java.util.ArrayList;

import entity.card.NormalCard;
import entity.card.RotateCard;
import entity.card.StopCard;
import entity.card.UnitCard;
import javafx.scene.paint.Color;
import logic.GameLogic;

public class Main {

	public static void main(String[] args) {
		NormalCard YellowOne = new NormalCard(1, Color.YELLOW);
		StopCard GreenStop = new StopCard(Color.GREEN);
		RotateCard RedRotate = new RotateCard(Color.RED);
		ArrayList<UnitCard>  cardList = new ArrayList<UnitCard>();
		cardList.add(YellowOne);
		cardList.add(GreenStop);		
		cardList.add(RedRotate);
		GameLogic.getInstance();

	}
}
