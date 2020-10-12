public class MinCut {

	public int minCuts(String str) {
	  if (str == null || str.length() <= 1) {
		return 0;  // no need to cut empty string or string with length one
	  }
	  
	  int size = str.length();
	  int[] M = new int[size + 1]; // account for empty
	  M[0] = -1; // this is a special value, because if the whole string is Palindrom, we want M[j] + 1 == 0, so set this to be -1
	  M[1] = 0; // always a palindrome
	  
	  // we also need a memo matrix to remember if string between (j-1)th and (i-1)th is palindrome, so we don't need to re-scan the string every time
	  // without this matrix, we will need to have a method to scan (from two ends to middle) every substring when we look back
	  boolean[][] isP = new boolean[size+1][size+1];
	  
	  for (int i = 2; i <= size; i++) {   // less or equal to size, because it's the n'th character not n'th index
		M[i] = size - 1; // initialize, worst case we need n -1 cuts
		for (int j = i - 1; j >= 0; j--) { // look back to check if we can get smaller cuts, use M[j] to get result
		  
		  // left part we can look at M[j], right part substring is from j + 1 to i, which is str.substring(j, i)
		  boolean isPalindrome = (j + 1 == i) || ( str.charAt(j) == str.charAt(i-1) && (j + 2 == i || isP[i-1][j+2]) );
		  if (isPalindrome) {
			isP[i][j+1] = true; // update isP matrix for later use
			M[i] = Math.min(M[i], M[j] + 1);
		  }
		}
	  }
	  return M[size];
	}
  
  }
  
  
  // Base case: M[1] = 0  isP[i][j] = true if i == j
  // M[i] represents the minimum cuts needed for the [0, i] (inclusive) substring
  // M[i] =  if substring(j+1, i) is palindrome, then M[i] = M[j] + 1
  
  
  // Time Complexity  --- O(n ^ 2)
  // Space Complexity --- O(n ^ 2), if we use a method to re-scan substring instead of isP matrix, we can reduce this down to O(n)