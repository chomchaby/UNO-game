package entity.player;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import entity.card.UnitCard;
import javafx.scene.paint.Color;
import logic.GameLogic;

public class User extends Player {

	private boolean drawn, picked, turnEnd;

	public User() {
		super();
	}

	@Override
	public void play() {
		System.out.println("User Turn");
		setDrawableCardList();
		while (!turnEnd) {
			// have to do something... whyyyy
			//System.out.print("");
			doNothing();
			if (isDrawn()) {
				turnEnd = true;
			} 
			else if (isPicked()) {
				boolean endTurn = true;
				for (UnitCard card : this.getCardList()) {
					if (card.isDrawable()) {
						endTurn = false;
						break;
					}
				}
				if (endTurn)
					turnEnd = true;
			}
		}
//		System.out.println("turnEnd: " + turnEnd);
//		System.out.println("Drawn: " + isDrawn());
//		System.out.println("Picked: " + isPicked());
		setDrawn(false);
		setPicked(false);
		turnEnd = false;
		GameLogic.getInstance().shortProcessing();
	}

	@Override
	public void drawCard(UnitCard card) {
		super.drawCard(card);
		System.out.println("User drawn....");
		if (card.getColor() != Color.BLACK)
			setDrawn(true);
//		System.out.println(isDrawn());
//		System.out.println(turnEnd);
	}

	@Override
	public void pick(int n) {
		super.pick(n);
		if (GameLogic.getInstance().getCurrentPlayer() == GameLogic.getInstance().getUser()) {
			setDrawableCardList();
			setPicked(true);
		}
	}

	public boolean isDrawn() {
		return drawn;
	}

	public void setDrawn(boolean drawn) {
		this.drawn = drawn;
	}

	public boolean isPicked() {
		return picked;
	}

	public void setPicked(boolean picked) {
		this.picked = picked;
	}
	
	private void doNothing() {
		System.out.print("");
	}
}
