// package Amazon;
// import java.util.*;
// // public class OA1 {
	
// // }

// public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {
// 	List<Integer> res = new ArrayList<>();
// 	TreeMap<Integer, Integer> counter = new TreeMap<>();
// 	int sum = 0;
// 	boolean open = false;
// 	for (int i = 0; i < s.length(); i++) {
// 		char sign = s.charAt(i);
// 		if (sign == '*') {
// 			if (open) {
// 				sum++;
// 			}
// 		} else if (sign == '|') {
// 			open = true;
// 			counter.put(i + 1, sum); // startIndices and endIndices are not from 0
// 		}
// 	}

// 	for (int i = 0; i < startIndices.size(); i++) {
// 		int start = startIndices.get(i);
// 		int end = endIndices.get(i);
// 		res.add(itemNumbers(start, end, counter));
// 	}
	
// 	return res;
// }

// private static int itemNumbers(int left, int right, TreeMap<Integer, Integer> counter) {
// 	int leftBorder = counter.ceilingKey(left);
// 	int rightBorder = counter.floorKey(right);
// 	int diff = counter.get(rightBorder) - counter.get(leftBorder);

// 	return Math.max(diff, 0); // when something like |********************|, and start, end is in between, this diff would be negative, so we need to do this.
// }