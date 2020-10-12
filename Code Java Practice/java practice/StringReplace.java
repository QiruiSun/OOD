import java.util.*;

public class StringReplace {
	public String replace(String input, String source, String target) {
	  // Write your solution here
	  char[] curr = input.toCharArray();
  
	  if (source.length() >= target.length()) {
		return replaceFromLeft(curr, source, target);
	  }
	  return "";
	}
  
	private String replaceFromLeft(char[] input, String source, String target) {
	  int slow = 0;
	  int fast = 0;
	  
	  while (fast < input.length) {
		if (input[fast] == source.charAt(0)) {
		  int j = 0;
		  while (j < source.length() && input[fast+j] == source.charAt(j)) {
			j++;
		  }
		  if (j == source.length()) {
			// match
			fast += j;
			for (int x = 0; x < target.length(); x++) {
			  input[slow++] = target.charAt(x);
			}
		  } else {
			input[slow++] = input[fast++];
		  }
		} else {
		  input[slow++] = input[fast++];
		}
	  }
	  return new String(input, 0, slow);
	}
  
	private String replaceFromRight(char[] input, String source, String target) {
	  int diff = target.length() - source.length();
	  
	  HashSet<Integer> memo = new HashSet<>();
	  for (int i=0; i<input.length; i++) {
		if (input[i] == source.charAt(0)) {
		  int j = 0;
		  while (j < source.length() && input[i+j] == source.charAt(j)) {
			j++;
		  }
		  if (j == source.length()) {
			// match
			memo.add(i+j - 1);
		  }
		}
	  }
  
	  int extra = memo.size() * diff;
	  int oldL = input.length - 1;
	  input = Arrays.copyOf(input, input.length + extra);
	  int slow = input.length - 1;
	  
	  while (oldL >= 0 ) {
		if (memo.contains(oldL)) {
		  for (int i=target.length()-1; i>=0; i--) {
			input[slow--] = target.charAt(i);
		  }
		  oldL -= source.length();
		} else {
		  input[slow--] = input[oldL--];
		}
	  }
  
	  return new String(input);
  
	}
  }