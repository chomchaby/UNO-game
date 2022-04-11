package logic;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Random;

import entity.card.*;

import entity.player.*;

import javafx.scene.paint.Color;

public class GameLogic {

	private static GameLogic instance = null;
	private static final Color[] colorArray = { Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW };

	private boolean isGameEnd;
	private boolean isGameWin;

	private int playerTurn;
	private Player currentPlayer;
	private int numberState;
	private Color colorState;
	private boolean isClockwise;
	private boolean playable;
	private boolean colorSelectionState;

	private Player beforePlayer;
	private Player nextPlayer;

	private UnitCard cardOnTable;
	private ArrayList<UnitCard> cardPile;

	private Player user;
	private Player botJesica;
	private Player botMagaret;
	private Player botVanda;

	private GameLogic() {
		this.newGame();
	}

	public void newGame() {

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

		// set parameters
		Random rand = new Random();
		setPlayerTurn(rand.nextInt(4));

		setCurrentPlayer();
		setNumberState(cardOnTable.getNumber());
		setColorState(cardOnTable.getColor());
		setClockwise(true);
		setPlayable(true);
		setColorSelectionState(true);
		setBeforePlayer();
		setNextPlayer();
		setGameEnd(false);

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
		user = new User();
		botJesica = new Bot("Jesica");
		botMagaret = new Bot("Magaret");
		botVanda = new Bot("Vanda");

		Collections.shuffle(cardPile);
		for (int i = 0; i < 4; i++) {
			user.getCardList().add(cardPile.remove(0));
			botJesica.getCardList().add(cardPile.remove(0));
			botMagaret.getCardList().add(cardPile.remove(0));
			botVanda.getCardList().add(cardPile.remove(0));
		}
		cardOnTable = cardPile.remove(0);
	}

	public void setUpForNewTurn() {
		move();
		setNumberState(cardOnTable.getNumber());
		if (cardOnTable.getNumber() != -1)
			setColorState(cardOnTable.getColor());
		setCurrentPlayer();
		setBeforePlayer();
		setNextPlayer();
		System.out.println("New Turn");
	}

	public void runTurn() {
		if (playable) {
			currentPlayer.play();
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

	// setter and getter
	public void setUserName(String name) {
		user.setName(name);
	}

	public ArrayList<UnitCard> getCardPile() {
		return cardPile;
	}

	public void setCardPile(ArrayList<UnitCard> cardPile) {
		this.cardPile = cardPile;
	}

	public UnitCard getCardOnTable() {
		return cardOnTable;
	}

	public void setCardOnTable(UnitCard cardOnTable) {
		this.cardOnTable = cardOnTable;
	}

	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer() {
		if (playerTurn % 4 == 0) {
			currentPlayer = user;
		} else if (playerTurn % 4 == 1) {
			currentPlayer = botJesica;
		} else if (playerTurn % 4 == 2) {
			currentPlayer = botMagaret;
		} else {
			currentPlayer = botVanda;
		}
	}

	public int getNumberState() {
		return numberState;
	}

	public void setNumberState(int n) {
		numberState = n;
	}

	public Color getColorState() {
		return colorState;
	}

	public void setColorState(Color color) {
		colorState = color;
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

	public boolean isColorSelectionState() {
		return colorSelectionState;
	}

	public void setColorSelectionState(boolean state) {
		colorSelectionState = state;
	}

	public Player getBeforePlayer() {
		return beforePlayer;
	}

	public void setBeforePlayer() {
		if (isClockwise) {
			if (playerTurn % 4 == 0) {
				beforePlayer = botVanda;
			} else if (playerTurn % 4 == 1) {
				beforePlayer = user;
			} else if (playerTurn % 4 == 2) {
				beforePlayer = botJesica;
			} else {
				beforePlayer = botMagaret;
			}
		} else {
			if (playerTurn % 4 == 0) {
				beforePlayer = botJesica;
			} else if (playerTurn % 4 == 1) {
				beforePlayer = botMagaret;
			} else if (playerTurn % 4 == 2) {
				beforePlayer = botVanda;
			} else {
				beforePlayer = user;
			}
		}
	}

	public Player getNextPlayer() {
		return nextPlayer;
	}

	public void setNextPlayer() {
		if (isClockwise) {
			if (playerTurn % 4 == 0) {
				nextPlayer = botJesica;
			} else if (playerTurn % 4 == 1) {
				nextPlayer = botMagaret;
			} else if (playerTurn % 4 == 2) {
				nextPlayer = botVanda;
			} else {
				nextPlayer = user;
			}
		} else {
			if (playerTurn % 4 == 0) {
				beforePlayer = botVanda;
			} else if (playerTurn % 4 == 1) {
				beforePlayer = user;
			} else if (playerTurn % 4 == 2) {
				beforePlayer = botJesica;
			} else {
				beforePlayer = botMagaret;
			}
		}
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
		return user;
	}

	public Player getBotJesica() {
		return botJesica;
	}

	public Player getBotMagaret() {
		return botMagaret;
	}

	public Player getBotVanda() {
		return botVanda;
	}

}
