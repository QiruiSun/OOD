package qirui.blackJack;
import java.util.*;

public class Deck {

	private List<Card> cards;

	public Deck() {
		List<Card>  cards = new ArrayList<>();
		
		for (Card.Suit suit : Card.Suit.values()) {
			for (Card.Face faceVal : Card.Face.values()) {
				Card curr = new Card(faceVal, suit);
				cards.add(curr);
			}
		}
		this.cards = cards;
	}

	public Card[] dealHand(int number) {
		Card[] toDeal = new Card[number];
		for (int i = 0; i < number; i++) {
			if (isEmpty()) {
				return null;
			}
			toDeal[i] = deal();
		}
		return toDeal;
	}

	public Card deal() {
		int index = cards.size() - 1;
		Card curr = cards.get(index);
		cards.remove(index);
		return curr;
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public boolean isEmpty() {
		return cards.size() == 0;
	}



}


// APIs:
//  deal
//  shuffle