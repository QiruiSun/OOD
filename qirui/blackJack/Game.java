package qirui.blackJack;
import java.util.*;

public class Game {
	private List<Hand> hands;
	private Deck currDeck;

	public Game(int playerNumber) {
		List<Hand> hands = new ArrayList<>();
		while (playerNumber > 0) {
			hands.add(new Hand());
			playerNumber--;
		}
		this.hands = hands;
	}

	public void init() {
		currDeck = new Deck();
		currDeck.shuffle();
	}

	public boolean addHand(Hand hand) {
		return this.hands.add(hand);
	}

	public void start() {
		boolean success = prepareHands();
		if (!success) {
			System.out.println("Out of cards");
			return;
		}
		List<Hand> winners = anyBlackJack();
		if (winners != null) {
			printWinners(winners);
			return;
		}
		boolean gameOn = true;
		while (gameOn) {
			int liveHand = 0;
			for (Hand hand : hands) {
				if (hand.canHit()) {
					liveHand++;
					if (currDeck.isEmpty()) {
						System.out.println("Out of cards");
						return;
					}
					hand.hit(currDeck.deal());
				}
			}
			if (liveHand == 0) {
				gameOn = false;
			}
		}
		winners = findWinners();
		printWinners(winners);
	}

	private void printWinners(List<Hand> winners) {
		if (winners == null || winners.size() == 0) {
			System.out.println("No winners");
		} else {
			System.out.println("Winners: ");
			for (Hand w : winners) {
				System.out.println(w + "");
			}
		}
	}

	public boolean prepareHands() {
		for (Hand hand : hands) {
			Card[] cards = currDeck.dealHand(2);
			if (cards == null) {
				return false;
			}
			hand.addCards(cards);
		}
		return true;
	}

	private List<Hand> findWinners() {
		List<Hand> winners = new ArrayList<>();
		int max = 0;

		for (Hand hand : hands) {
			if (!hand.lost()) {
				int curr = hand.value();
				if (curr > max) {
					max = curr;
					winners.clear()
					winners.add(hand);
				} else if (curr == max) {
					winners.add(hand);
				}
			}
		}
		return winners;
	}

	private List<Hand> anyBlackJack() {
		List<Hand> winners = new ArrayList<>();
		for (Hand hand : hands) {
			if (hand.blackJack()) {
				winners.add(hand);
			}
		}
		return winners;
	}

}


// APIS:

