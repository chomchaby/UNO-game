package entity.player;

import java.util.ArrayList;
import java.util.Collections;

import entity.card.UnitCard;
import entity.card.EffectCard;
import logic.GameAction;
import logic.GameLogic;

public abstract class Player {
	protected String name;
	protected ArrayList<UnitCard> cardList;
	protected ArrayList<UnitCard> drawableCardList;
	protected boolean isPlayable;

	public Player() {
		cardList = new ArrayList<UnitCard>();
		setPlayable(true);
	}

	public Player(String name) {
		this.name = name;
		cardList = new ArrayList<UnitCard>();
		setPlayable(true);
	}

	public abstract void play();

	public void drawCard(UnitCard card) {
		this.getCardList().remove(card);
		GameLogic.getInstance().getCardPile().add(GameLogic.getInstance().getCardOnTable());
		Collections.shuffle(GameLogic.getInstance().getCardPile());
		GameLogic.getInstance().setCardOnTable(card);
		if (card.getAction() != GameAction.NONE) {
			((EffectCard) card).takeAction();
		}
	}

	public void pick(int n) {
		for (int i = 0; i < n; i++) {
			UnitCard newCard = GameLogic.getInstance().getCardPile().get(0);
			this.getCardList().add(newCard);
			GameLogic.getInstance().getCardPile().remove(newCard);
		}
	}

	public boolean isWin() {
		return this.getCardList().size() == 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<UnitCard> getCardList() {
		return cardList;
	}

	public void setCardList(ArrayList<UnitCard> cardList) {
		this.cardList = cardList;
	}

	public ArrayList<UnitCard> getDrawableCardList() {
		return drawableCardList;
	}

	public void setDrawableCardList() {
		drawableCardList = new ArrayList<UnitCard>();
		for (UnitCard card : this.getCardList()) {
			if (card.isDrawable())
				this.getDrawableCardList().add(card);
		}
	}
	
	public boolean isPlayable() {
		return isPlayable;
	}

	public void setPlayable(boolean isPlayable) {
		this.isPlayable = isPlayable;
	}

}
