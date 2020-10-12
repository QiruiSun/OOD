import java.util.*;

public class ConfusingNumber {
	public ArrayList<Integer> solve(int n) {
		int[] dict = {0, 1, 6, 8, 9};
		ArrayList<Integer> res = new ArrayList<>();
		confuse(0, dict, n, res);
		return res;
	}
	
	
	private void confuse(int s, int[] dict, int n, ArrayList<Integer> res) {
		for (int i =0; i< dict.length; i++) {
			if (s == 0 && dict[i] == 0) {
				continue;
			}
			int num = s * 10 + dict[i];
			if (num > n) {
				return;
			}
			if (isConfuse(num)) {
				res.add(num);
			}
			confuse(num, dict, n, res);
		}
	}
	
	// 109
	
	private boolean isConfuse (int N) {
		Map<Integer, Integer> map = new HashMap<>();  // use hashmap to store rotating number
			map.put(6, 9);
			map.put(9, 6);
			map.put(0, 0);
			map.put(1, 1);
			map.put(8, 8);
			
			int newNum = 0;
			int tmp = N; 
			while (tmp != 0) {
				newNum = 10 * newNum + map.get(tmp % 10);
				tmp /= 10;
			}
			return N == newNum ? false : true;
	}
	
}