public class LongestCommonListNode {
	public int longestCommon(ListNode node1, ListNode node2) {
		String one = buildString(node1);
		String two = buildString(node2);

		int[][] dp = new int[one.length() + 1][two.length() + 1];  // 
		int max = 0;
		for (int i = 1; i <= one.length(); i++) {
			for (int j = 1; j <= one.length(); j++) {
				if (one.charAt(i-1) == two.charAt(j-1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				max = Math.max(max, dp[i][j]);
			}
		}
		return max;
	}

	private String buildString(ListNode node) {
		StringBuilder curr = new StringBuilder();
		while (node != null) {
			curr.append(node.val);
			node = node.next;
		}
		return curr.toString();
	}

}

class StringCheck {
	public static void main(String[] args) {
		LongestCommonListNode s = new LongestCommonListNode();
		ListNode one = ListNode.buildStringNodes(new char[] {'a', 'b','c'});
		ListNode two = ListNode.buildStringNodes(new char[] {'z', 'a','b', 'c'});

		System.out.println(s.longestCommon(one, two));
	}
}
