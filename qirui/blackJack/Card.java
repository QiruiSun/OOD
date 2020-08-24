package qirui.blackJack;

public class Card {

	public Face faceValue;
	public Suit suit;

	public Card(Face faceValue, Suit suit) {
		this.faceValue = faceValue;
		this.suit = suit;
	}

	public int value() {
		return this.faceValue.val();
	}

	static enum Face {
		ONE(1),
		TWO(2),
		THREE(2);

		int value;

		private Face(int value) {
			this.value = value;
		}

		public int val() {
			return this.value;
		}
	}

	static enum Suit {
		DIAMOND,
		HEART,
		SPADE,
		CLUB;
	}
}

