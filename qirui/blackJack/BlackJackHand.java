package qirui.blackJack;
import java.util.*;

public class BlackJackHand extends Hand {
	private static final int gamePoint = 21;

	public boolean lost() {
		return value() > 21;
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

	protected boolean shouldStand() {
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