package logic;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import entity.card.*;

import entity.player.*;
import javafx.scene.paint.Color;
import sharedObject.ColorLoader;

public class GameLogic {

	private static GameLogic instance = null;
	private Color[] colorArray = { ColorLoader.BLUE, ColorLoader.GREEN, ColorLoader.RED, ColorLoader.YELLOW };

	// cards in game
	private ArrayList<UnitCard> cardPile;
	private UnitCard cardOnTable;

	// player to play
	private int playerTurn;
	private Player currentPlayer;
	private Player nextPlayer;
	private Player beforePlayer;

	// general parameters
	private int numberState;
	private Color colorState;
	private boolean isClockwise;
	private boolean isRoundEnd;

	// special parameters
	private boolean colorSelectionState;
	private boolean challengeState;
	private Color challengeColor;
	private boolean isScoreShown;

	// all players
	private Player user;
	private Player botJesica;
	private Player botMagaret;
	private Player botVanda;

	// other
	private int deckSize;

	public static GameLogic getInstance() {
		if (instance == null) {
			instance = new GameLogic();
		}
		return instance;
	}

	private GameLogic() {
		// set all players only on first GameLogic call...
		user = new User();
		botJesica = new Bot("Jesica");
		botMagaret = new Bot("Magaret");
		botVanda = new Bot("Vanda");
	}

	public void start(String name) {
		// new user start new game...
		user.setName(name);
		user.setScore(0);
		botJesica.setScore(0);
		botMagaret.setScore(0);
		botVanda.setScore(0);
		newGame();
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

		// general parameters
		setNumberState(cardOnTable.getNumber());
		setColorState(cardOnTable.getColor());
		setClockwise(true);
		setRoundEnd(false);
		// special parameters
		setColorSelectionState(false);
		setChallengeState(false);
		setScoreShown(false);

		// player to play
		Random rand = new Random();
		setPlayerTurn(rand.nextInt(4) + 1000);
		setCurrentPlayer();
		setNextPlayer();
		setBeforePlayer();

		// reset player playable state
		user.setPlayable(true);
		botJesica.setPlayable(true);
		botMagaret.setPlayable(true);
		botVanda.setPlayable(true);

		// print state
		System.err.println("...NEW GAME BEGINS...");
	}

	private void initilizeCardPile() {
		deckSize = 0;
		for (Color color : colorArray) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 10 ; j++) {
					UnitCard card = new NormalCard(j, color);
					cardPile.add(card);
					deckSize += 1;
				}
			}
			for (int i = 0; i < 2; i++) {
				UnitCard card = new SkipCard(color);
				cardPile.add(card);
				deckSize += 1;
			}
			for (int i = 0; i < 2; i++) {
				UnitCard card = new ReverseCard(color);
				cardPile.add(card);
				deckSize += 1;
			}
			for (int i = 0; i < 2; i++) {
				UnitCard card = new DrawCard(color);
				cardPile.add(card);
				deckSize += 1;
			}
		}
		for (int i = 0; i < 4; i++) {
			UnitCard card = new ColorCard();
			cardPile.add(card);
			deckSize += 1;
		}
		for (int i = 0; i < 6; i++) {
			UnitCard card = new ChallengeCard();
			cardPile.add(card);
			deckSize += 1;
		}
		System.out.println(deckSize);
	}

	private void dealCard() {
		Collections.shuffle(cardPile);
		for (int i = 0; i < 5; i++) {
			user.getCardList().add(cardPile.remove(0));
			botJesica.getCardList().add(cardPile.remove(0));
			botMagaret.getCardList().add(cardPile.remove(0));
			botVanda.getCardList().add(cardPile.remove(0));
		}
		cardOnTable = cardPile.remove(0);
	}

	public void setUpForNewTurn() {

		setNumberState(cardOnTable.getNumber());
		if (!cardOnTable.getColor().equals(Color.BLACK)) {
			setColorState(cardOnTable.getColor());
		}
		move();
		setCurrentPlayer();
		setNextPlayer();
		setBeforePlayer();

	}

	private void move() {
		if (isClockwise)
			playerTurn += 1;
		else
			playerTurn -= 1;
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
		GameLogic.getInstance().sleepTwo();
		// pick cards
		if (GameLogic.getInstance().isChallengeWin()) {
			if (GameLogic.getInstance().getNextPlayer() instanceof User) {
				GameLogic.getInstance().sleepTwo();
			}
			GameLogic.getInstance().getNextPlayer().drawCard(4);
		} else {
			if (GameLogic.getInstance().getCurrentPlayer() instanceof User) {
				GameLogic.getInstance().sleepTwo();
			}
			GameLogic.getInstance().getCurrentPlayer().drawCard(2);
		}
		// more time to read
		GameLogic.getInstance().sleepOne();
	}

	public Player getWinner() {
		int goal = 24;
		if (currentPlayer.getScore() >= goal) {
			return currentPlayer;
		}
		return null;
	}

	public void sleepOne() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sleepTwo() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sleepThree() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// setter and getter
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

	public boolean isRoundEnd() {
		return isRoundEnd;
	}

	public void setRoundEnd(boolean isRoundEnd) {
		this.isRoundEnd = isRoundEnd;
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
	
	public boolean isScoreShown() {
		return isScoreShown;
	}

	public void setScoreShown(boolean isScoreShown) {
		this.isScoreShown= isScoreShown;
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

	public int getDeckSize() {
		return deckSize;
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

}
