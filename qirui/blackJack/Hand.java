package qirui.blackJack;
import java.util.*;

public abstract class Hand {
	
	protected List<Card> cards;

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

	public abstract boolean canHit();

	public abstract boolean lost();

	protected abstract boolean shouldStand();

	public abstract int value();



}





// APIs:

// 	hit

// 	shouldStand

// 	value