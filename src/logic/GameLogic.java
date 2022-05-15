package logic;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import entity.card.*;

import entity.player.*;
import gui.Updatable;
import javafx.scene.paint.Color;
import sharedObject.ColorLoader;

public class GameLogic {

	private static GameLogic instance = null;
	private static final Color[] colorArray = { ColorLoader.BLUE, ColorLoader.GREEN, ColorLoader.RED,
			ColorLoader.YELLOW };

	// all cards
	private UnitCard cardOnTable;
	private ArrayList<UnitCard> cardPile;

	// player to play
	private int playerTurn;
	private Player currentPlayer;
	private Player nextPlayer;
//	private Player beforePlayer;

	// general parameters
	private int numberState;
	private Color colorState;
	private boolean isClockwise;
	private boolean isGameEnd;

	// special parameters
	private boolean colorSelectionState;
	private boolean challengeState;
	private Color challengeColor;

	// all players
	private Player user;
	private Player botJesica;
	private Player botMagaret;
	private Player botVanda;

	private GameLogic() {
		user = new User();
		botJesica = new Bot("Jesica");
		botMagaret = new Bot("Magaret");
		botVanda = new Bot("Vanda");
		this.newGame();
	}

	public void newGame() {
		cardPile = new ArrayList<UnitCard>();
		user.setCardList(new ArrayList<UnitCard>());
		botJesica.setCardList(new ArrayList<UnitCard>());
		botMagaret.setCardList(new ArrayList<UnitCard>());
		botVanda.setCardList(new ArrayList<UnitCard>());

		// create all cards, put them in cardPile, and then deal cards
		initilizeCardPile();
		dealCard();

		// set parameters
		// player to play
		Random rand = new Random();
		setPlayerTurn(rand.nextInt(4) + 1000);
		setCurrentPlayer();
		setNextPlayer();
//		setBeforePlayer();
		// general parameters
		setNumberState(cardOnTable.getNumber());
		setColorState(cardOnTable.getColor());
		setClockwise(true);
		setGameEnd(false);
		// special parameters
		setColorSelectionState(false);
		setChallengeState(false);

	}

	public static GameLogic getInstance() {
		if (instance == null) {
			instance = new GameLogic();
		}
		return instance;
	}

	private void initilizeCardPile() {
		for (Color color : colorArray) {
			for (int i = 0; i < 20; i++) {
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
				UnitCard card = new DrawCard(color);
				cardPile.add(card);
			}
		}
		for (int i = 0; i < 4; i++) {
			UnitCard card = new ColorCard();
			cardPile.add(card);
		}
		for (int i = 0; i < 6; i++) {
			UnitCard card = new ChallengeCard();
			cardPile.add(card);
		}
	}

	private void dealCard() {
		Collections.shuffle(cardPile);
		for (int i = 0; i < 14; i++) {
			user.getCardList().add(cardPile.remove(0));
			botJesica.getCardList().add(cardPile.remove(0));
			botMagaret.getCardList().add(cardPile.remove(0));
			botVanda.getCardList().add(cardPile.remove(0));
		}
		cardOnTable = cardPile.remove(0);
	}

	public void setUpForNewTurn() {

		setNumberState(cardOnTable.getNumber());
		if ((cardOnTable.getAction() != GameAction.CHANGECOLOR) && (cardOnTable.getAction() != GameAction.CHALLENGE))
			setColorState(cardOnTable.getColor());
		move();
		setCurrentPlayer();
		setNextPlayer();
//		setBeforePlayer();

	}

	private void move() {
		if (isClockwise)
			playerTurn += 1;
		else
			playerTurn -= 1;
	}

	public void longProcessing() {
		try {
			TimeUnit.SECONDS.sleep(3); // 3
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void shortProcessing() {
		try {
			TimeUnit.SECONDS.sleep(1); // 1
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isChallengeWin() {
		Set<Color> colorSet = new HashSet<Color>();
		for (UnitCard card : getNextPlayer().getCardList()) {
			colorSet.add(card.getColor());
		}
		if (colorSet.contains(challengeColor)) {
			return false;
		}
		return true;
	}

	public void punishChallenge() {

		// sleep for seconds to read challenge result
		GameLogic.getInstance().longProcessing();
		// pick cards
		if (GameLogic.getInstance().isChallengeWin()) {
			if (GameLogic.getInstance().getNextPlayer() instanceof User) {
				GameLogic.getInstance().longProcessing();
			}
			GameLogic.getInstance().getNextPlayer().drawCard(4);
		} else {
			if (GameLogic.getInstance().getCurrentPlayer() instanceof User) {
				GameLogic.getInstance().longProcessing();
			}
			GameLogic.getInstance().getCurrentPlayer().drawCard(2);
		}
		// more time to read
		GameLogic.getInstance().longProcessing();

	}

	public String myColorToString(Color color) {
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
				nextPlayer = botVanda;
			} else if (playerTurn % 4 == 1) {
				nextPlayer = user;
			} else if (playerTurn % 4 == 2) {
				nextPlayer = botJesica;
			} else {
				nextPlayer = botMagaret;
			}
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

	public boolean isGameEnd() {
		return isGameEnd;
	}

	public void setGameEnd(boolean isGameEnd) {
		this.isGameEnd = isGameEnd;
	}

	public boolean isColorSelectionState() {
		return colorSelectionState;
	}

	public void setColorSelectionState(boolean state) {
		colorSelectionState = state;
	}

	public boolean isChallengeState() {
		return challengeState;
	}

	public void setChallengeState(boolean state) {
		challengeState = state;
	}

	public Color getChallengeColor() {
		return challengeColor;
	}

	public void setChallengeColor(Color challengeColor) {
		this.challengeColor = challengeColor;
	}

	public User getUser() {
		return (User) user;
	}

	public Bot getBotJesica() {
		return (Bot) botJesica;
	}

	public Bot getBotMagaret() {
		return (Bot) botMagaret;
	}

	public Bot getBotVanda() {
		return (Bot) botVanda;
	}


//	public Player getBeforePlayer() {
//		return beforePlayer;
//	}
//
//	public void setBeforePlayer() {
//		if (isClockwise) {
//			if (playerTurn % 4 == 0) {
//				beforePlayer = botVanda;
//			} else if (playerTurn % 4 == 1) {
//				beforePlayer = user;
//			} else if (playerTurn % 4 == 2) {
//				beforePlayer = botJesica;
//			} else {
//				beforePlayer = botMagaret;
//			}
//		} else {
//			if (playerTurn % 4 == 0) {
//				beforePlayer = botJesica;
//			} else if (playerTurn % 4 == 1) {
//				beforePlayer = botMagaret;
//			} else if (playerTurn % 4 == 2) {
//				beforePlayer = botVanda;
//			} else {
//				beforePlayer = user;
//			}
//		}
//	}

}
