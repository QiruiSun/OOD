


enum ITEM {
	COKE,
	CHIPS,
	WATER,
	ROOT_BEER
}



// interface Item {
	
// 	public int getPrice;
// }

class Inventory {

	private ITEM type;
	private int price;
	private int capacity;
	private int size; // current stock size

	public Inventory(ITEM type, int price, int capacity) {
		this.price = price;
		this.type = type;
		this.capacity = capacity;
	}

	public int cost(){
		return this.price;
	}

	public ITEM label() {
		return this.type;
	}

	public boolean inStock(){
		return this.size > 0;
	}

	public boolean fill(int number) {
		if (number + size > capacity) {
			return false;
		}
		size = size + number;
		return true;
	}

	public boolean sold() {
		if (!inStock()) {
			return false;
		}
		size--;
		return true;
	}

}



// public class VendingMachine {
	


// 	// take money
// 	// give back changes
// 	// give back item
// 	// Return money if no item
// 	// 
// }