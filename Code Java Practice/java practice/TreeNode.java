import java.util.*;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }

	public static TreeNode build(List<Integer> nodes) {

		int curr = 1;
		Deque<TreeNode> queue = new ArrayDeque<>();
		
		TreeNode root = new TreeNode(nodes.get(0));
		queue.offer(root);
		while (queue.size() > 0 && curr < nodes.size()) {
			TreeNode node = queue.poll();
			if (nodes.get(curr) != null) {
				node.left = new TreeNode(nodes.get(curr));
				queue.offer(node.left);
			}
			curr++;

			if (nodes.get(curr) != null) {
				node.right = new TreeNode(nodes.get(curr));
				queue.offer(node.right);
			}
			curr++;
		}

		return root;
	}

	// level order print
	public static List<Integer> print(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
		return res;
		}
		Deque<TreeNode> queue = new ArrayDeque<>();

		queue.offer(root);

		while (queue.size() > 0) {
		TreeNode curr = queue.poll();
		res.add(curr.val);
		if (curr.left != null) {
			queue.offer(curr.left);
		}
		if (curr.right != null) {
			queue.offer(curr.right);
		}
		}
		
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}

		return res;
	}
}



