package qirui.blackJack;
import java.util.*;

public class Hand {
	private static final int gamePoint = 21;
	private List<Card> cards;

	public Hand() {
		this.cards = new ArrayList<>();
	}

	public void hit(Card card) {
		this.cards.add(card);
	}

	public void addCards(Card[] toAdd) {
		for (Card c : toAdd) {
			hit(c);
		}
	}

	public boolean canHit() {
		if (shouldStand()) {
			return false;
		}
		if (lost()) {
			return false;
		}
		return true;
	}

	public boolean lost() {
		return value() > 21;
	}

	private boolean shouldStand() {
		// if potential value greater than 18, then fold
		int curr = value();
		if (curr >= 18) {
			return true;
		}
		return false;
	}

	public boolean blackJack() {
		int curr = value();
		return curr == gamePoint;
	}

	public int value() {
		List<Integer> values = possibleValues();
		int best = values.get(0);
		for (int val : values) {
			if (val <= gamePoint) {
				int diff = Math.abs(gamePoint - val);
				if (diff < Math.abs(gamePoint - best)) {
					best = val;
				}
			}
		}
		return best;
	}

	private List<Integer> possibleValues() {
		List<Integer> values = new ArrayList<>();
		helper(values, 0, 0);
		return values;
	}

	private void helper(List<Integer> values, int index, int curr) {
		if (index == values.size()) {
			values.add(curr);
			return;
		}
		Card card = cards.get(index);
		if (card.faceValue == Card.Face.ONE) {
			helper(values, index + 1, curr + 11);
			helper(values, index + 1, curr + 1);
		} else {
			helper(values, index + 1, curr + card.value());
		}
	}

}





// APIs:

// 	hit

// 	shouldStand

// 	value