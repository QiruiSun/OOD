package Amazon;

public class FreshPromotion {
	
	// codeList = [[apple, apple], [banana, anything, banana]] 
	// shoppingCart = [orange, apple, apple, banana, orange, banana]
	public int canWin(String[][] codes, String[] cart) {

		int i = 0;
		int j = 0;

		while (j < cart.length && i < codes.length) {
			int cartIndex = j;
			boolean match = true;
			for (int k = 0; k < codes[i].length && cartIndex < cart.length; k++) {
				if (codes[i][k] == cart[cartIndex] || codes[i][k] == "anything") {
					cartIndex++;
				} else {
					match = false;
					break;
				}
			}
			if (match) {
				i++;
				j = cartIndex;
			} else {
				j++;
			}
			
		}

		return i == codes.length ? 1 : 0;
	}
}

class CheckPromo {
	public static void main(String[] args) {
		FreshPromotion s = new FreshPromotion();
		String[][] codeList1 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
		String[] shoppingCart1 = {"orange", "apple", "apple", "banana", "orange", "banana"};
		String[][] codeList2 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
		String[] shoppingCart2 = {"banana", "orange", "banana", "apple", "apple"};
		String[][] codeList3 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
		String[] shoppingCart3 = {"apple", "banana", "apple", "banana", "orange", "banana"};
		String[][] codeList4 = { { "apple", "apple" }, { "apple", "apple", "banana" } };
		String[] shoppingCart4 = {"apple", "apple", "apple", "banana"};
		String[][] codeList5 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
		String[] shoppingCart5 = {"orange", "apple", "apple", "banana", "orange", "banana"};
		String[][] codeList6 = { { "apple", "apple" }, { "banana", "anything", "banana" }  };
		String[] shoppingCart6 = {"apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
		String[][] codeList7= { { "anything", "apple" }, { "banana", "anything", "banana" }  };
		String[] shoppingCart7 = {"orange", "grapes", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
		System.out.println(s.canWin(codeList1, shoppingCart1));
		System.out.println(s.canWin(codeList2, shoppingCart2));
		System.out.println(s.canWin(codeList3, shoppingCart3));
		System.out.println(s.canWin(codeList4, shoppingCart4));
		System.out.println(s.canWin(codeList5, shoppingCart5));
		System.out.println(s.canWin(codeList6, shoppingCart6));
		System.out.println(s.canWin(codeList7, shoppingCart7));
	}
}
