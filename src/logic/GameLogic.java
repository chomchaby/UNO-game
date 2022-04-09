package logic;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Random;

import entity.card.*;

import entity.player.*;

import javafx.scene.paint.Color;

public class GameLogic {

	private static GameLogic instance = null;
	private boolean isGameEnd;
	private boolean isGameWin;
	private static final Color[] colorArray = { Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW };

	private int playerTurn;
	private boolean isClockwise;
	private boolean playable;
	private int numberState;
	private Color colorState;

	private UnitCard cardOnTable;
	private ArrayList<UnitCard> cardPile;

	private Player beforePlayer;
	private Player currentPlayer;
	private Player nextPlayer;

	private Player bot1;
	private Player bot2;
	private Player bot3;
	private Player user;

	private GameLogic() {
		this.newGame();
	}

	public void newGame() {
		// set parameters
		this.setGameEnd(false);
		this.setPlayable(true);
		Random rand = new Random();
		this.setPlayerTurn(rand.nextInt(4));

		// create all cards, put in cardPiles
		cardPile = new ArrayList<UnitCard>();
		for (Color color : colorArray) {
			for (int i = 0; i < 10; i++) {
				UnitCard card = new NormalCard(i, color);
				cardPile.add(card);
			}
			for (int i = 0; i < 2; i++) {
				UnitCard card = new StopCard(color);
				cardPile.add(card);
			}
			for (int i = 0; i < 2; i++) {
				UnitCard card = new RotateCard(color);
				cardPile.add(card);
			}
			for (int i = 0; i < 2; i++) {
				UnitCard card = new PickCard(color);
				cardPile.add(card);
			}
		}
		for (int i = 0; i < 4; i++) {
			UnitCard card = new ColorCard();
			cardPile.add(card);
		}
		for (int i = 0; i < 4; i++) {
			UnitCard card = new ChallengeCard();
			cardPile.add(card);
		}
		dealCard();

	}

	public static GameLogic getInstance() {
		if (instance == null) {
			instance = new GameLogic();
		}
		return instance;
	}

	private void dealCard() {
		// create all players, then deal 5 cards to each player randomly
		// draw cardOnTable
		user = new Player();
		bot1 = new Player();
		bot2 = new Player();
		bot3 = new Player();

		Collections.shuffle(cardPile);
		for (int i = 0; i < 48; i += 4) {
			user.getCardList().add(cardPile.remove(0));
			bot1.getCardList().add(cardPile.remove(1));
			bot2.getCardList().add(cardPile.remove(2));
			bot3.getCardList().add(cardPile.remove(3));
		}
		cardOnTable = cardPile.remove(0);
	}

	public void runTurn() {
		if (playable) {
			if (getPlayerTurn() % 4 == 0) {
				getUser().play();
			} else if (getPlayerTurn() % 4 == 1) {
				getBot1().play();
			} else if (getPlayerTurn() % 4 == 2) {
				getBot2().play();
			} else {
				getBot3().play();
			}

		} else {
			setPlayable(true);
		}
		move();
	}

	private void move() {
		if (isClockwise)
			playerTurn += 1;
		else
			playerTurn -= 1;
	}
	
	public void setUserName(String name) {
		user.setName(name);
	}

	public ArrayList<UnitCard> getCardPile() {
		return this.cardPile;
	}

	public void setCardPile(ArrayList<UnitCard> cardPile) {
		this.cardPile = cardPile;
	}

	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	public boolean isClockwise() {
		return isClockwise;
	}

	public void setClockwise(boolean isClockwise) {
		this.isClockwise = isClockwise;
	}

	public boolean isPlayable() {
		return playable;
	}

	public void setPlayable(boolean playable) {
		this.playable = playable;
	}

	public int getNumberState() {
		return numberState;
	}

	public void setNumberState(int numberState) {
		this.numberState = numberState;
	}

	public Color getColorState() {
		return colorState;
	}

	public void setColorState(Color colorState) {
		this.colorState = colorState;
	}

	public UnitCard getCardOnTable() {
		return cardOnTable;
	}

	public void setCardOnTable(UnitCard cardOnTable) {
		this.cardOnTable = cardOnTable;
	}

	public Player getBeforePlayer() {
		return beforePlayer;
	}

	public void setBeforePlayer(Player beforePlayer) {
		this.beforePlayer = beforePlayer;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getNextPlayer() {
		return nextPlayer;
	}

	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}

	public boolean isGameEnd() {
		return isGameEnd;
	}

	public void setGameEnd(boolean isGameEnd) {
		this.isGameEnd = isGameEnd;
	}

	public boolean isGameWin() {
		return isGameWin;
	}

	public void setGameWin(boolean isGameWin) {
		this.isGameWin = isGameWin;
	}

	public Player getUser() {
		return this.user;
	}

	public Player getBot1() {
		return this.bot1;
	}

	public Player getBot2() {
		return this.bot2;
	}

	public Player getBot3() {
		return this.bot3;
	}

}