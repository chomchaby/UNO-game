package entity.player;

import java.util.ArrayList;
import java.util.Collections;

import entity.card.UnitCard;
import entity.card.EffectCard;
import entity.card.NormalCard;
import logic.GameLogic;

public abstract class Player {
	protected String name;
	protected ArrayList<UnitCard> cardList;
	protected ArrayList<UnitCard> placeableCardList;
	protected boolean isPlayable;
	protected int score;

	public Player() {
		cardList = new ArrayList<UnitCard>();
		setPlayable(true);
		setScore(0);
	}

	public Player(String name) {
		this.name = name;
		cardList = new ArrayList<UnitCard>();
		setPlayable(true);
	}

	public abstract void play();

	public void placeCard(UnitCard card) {
		
		System.out.println(name + " places a card... >> " + card.toString());
		this.getCardList().remove(card);
		GameLogic.getInstance().getCardPile().add(GameLogic.getInstance().getCardOnTable());
		Collections.shuffle(GameLogic.getInstance().getCardPile());
		GameLogic.getInstance().setCardOnTable(card);
		if (card instanceof EffectCard) {
			System.out.println("The card is taking some effect...");
			((EffectCard) card).takeAction();
		}
	}

	public void drawCard(int n) {
		
		System.out.println(name + " draws some cards...");
		for (int i = 0; i < n; i++) {
			UnitCard newCard = GameLogic.getInstance().getCardPile().get(0);
			this.getCardList().add(newCard);
			GameLogic.getInstance().getCardPile().remove(newCard);
		}
	}
	
	public abstract void challenge();

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

	public ArrayList<UnitCard> getPlaceableCardList() {
		return placeableCardList;
	}

	public void setPlaceableCardList() {
		placeableCardList = new ArrayList<UnitCard>();
		for (UnitCard card : this.getCardList()) {
			if (card.isPlaceable())
				this.getPlaceableCardList().add(card);
		}
	}
	
	public boolean isPlayable() {
		return isPlayable;
	}

	public void setPlayable(boolean isPlayable) {
		this.isPlayable = isPlayable;
	}

	public int getScore() {
		return this.score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
