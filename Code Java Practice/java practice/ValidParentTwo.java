import java.util.*;

public class ValidParentTwo {
	public List<String> validParentheses(int l, int m, int n) {
		// Write your solution here
		Pair a = new Pair('(', ')', l * 2);
		Pair b = new Pair('<', '>', m * 2);
		Pair c = new Pair('{', '}', n * 2);
	
		List<String> res = new ArrayList<>();
		List<Pair> pairs = new ArrayList<>();
		pairs.add(a);
		pairs.add(b);
		pairs.add(c); 
			
		StringBuilder curr = new StringBuilder();
		int max = (l + m + n) * 2;
		helper(pairs, res, curr, max, 0);
	
		return res;
	  }
	
	  private void helper(List<Pair> pairs, List<String> res, StringBuilder curr, int max, int size) {
		if (size == max) {
		  res.add(curr.toString());
		  return;
		}
	
		for (Pair p : pairs) {
		  if (p.leftCount > p.rightCount) {
			char last = curr.charAt(curr.length() -1);
			if ( (last == '(' || last == '{' || last == '<') && p.left != last) {
			continue;
			}
			curr.append(p.right);
			p.rightCount++;
			helper(pairs, res, curr, max, size + 1);
			p.rightCount--;
			curr.deleteCharAt(curr.length() -1);
		  } else {
			if (p.max > (p.leftCount + p.rightCount)) {
			  curr.append(p.left);
			  p.leftCount++;
			  helper(pairs, res, curr, max, size + 1);
			  p.leftCount--;
			  curr.deleteCharAt(curr.length() -1);
			}
		  }
		}
	  }
	
	  class Pair {
		int leftCount;
		int rightCount;
		char left;
		char right;
		int max;
	
		public Pair(char l, char r, int max) {
		  left = l;
		  right = r;
		  this.max = max;
		}
	  }
}