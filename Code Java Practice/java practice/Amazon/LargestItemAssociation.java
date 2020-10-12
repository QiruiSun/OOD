package Amazon;

import java.util.*;

public class LargestItemAssociation {
	// https://leetcode.com/discuss/interview-question/782606/

	public List<String> solve(List<PairString> itemAssociation) {
		Map<String, List<String>> graph = new HashMap<>();

		for (PairString entry : itemAssociation) {
			if (!graph.containsKey(entry.first)) {
				graph.put(entry.first, new ArrayList<>());
			}
			if (!graph.containsKey(entry.second)) {
				graph.put(entry.second, new ArrayList<>());
			}
			graph.get(entry.first).add(entry.second);
			graph.get(entry.second).add(entry.first);
		}

		List<List<String>> res = new ArrayList<>();
		int size = 0;
		Set<String> visited = new HashSet<>();

		for (String node : graph.keySet()) {
			List<String> temp = new ArrayList<>();

			// any node in the connected cycle should be able to reach all other nodes,
			// since it's undirected graph
			dfs(visited, node, temp, graph);

			if (temp.size() > size) {
				res.clear();
				Collections.sort(temp);
				res.add(temp);
				size = temp.size();
			} else if (temp.size() == size) {
				Collections.sort(temp);
				res.add(temp);
			}
		}

		Collections.sort(res, new Comparator<List<String>>() {
			@Override
			public int compare(List<String> one, List<String> two) {
				int result = 0;
				for (int i = 0; i < one.size() && result == 0; i++) {
					result = one.get(i).compareTo(two.get(i));
				}
				return result;
			}
		});
		return res.get(0);
	}

	// dfs to go through all connected nodes
	private void dfs(Set<String> visited, String node, List<String> list, Map<String, List<String>> graph) {
		if (visited.contains(node)) {
			return;
		}

		list.add(node);
		visited.add(node);

		for (String child : graph.get(node)) {
			dfs(visited, child, list, graph);
		}
	}

}

class PairString {
	String first;
	String second;

	public PairString(String first, String second) {
		this.first = first;
		this.second = second;
	}
}

class Check {
	public static void main(String[] args) {
		LargestItemAssociation s = new LargestItemAssociation();
		/**
		 * Example 1
		 */
		List<PairString> input = Arrays.asList(new PairString[] { new PairString("item1", "item2"),
				new PairString("item3", "item4"), new PairString("item4", "item5") });
		/**
		 * Testing equal sized arraylist. 1->2->3->7 4->5->6->7
		 */
		List<PairString> input2 = Arrays.asList(new PairString[] { new PairString("item1", "item2"),
				new PairString("item2", "item3"), new PairString("item4", "item5"), new PairString("item6", "item7"),
				new PairString("item5", "item6"), new PairString("item3", "item7") });
		List<String> lst = s.solve(input);
		for (String sa : lst)
			System.out.print(" " + sa);
		System.out.println();
		List<String> lst2 = s.solve(input2);
		for (String sa : lst2)
			System.out.print(" " + sa);
		System.out.println();
		/**
		 * Testing duplicates: 1->2->3->7 , 5->6
		 */
		List<PairString> input3 = Arrays.asList(new PairString[] { new PairString("item1", "item2"),
				new PairString("item1", "item3"), new PairString("item2", "item7"), new PairString("item3", "item7"),
				new PairString("item5", "item6"), new PairString("item3", "item7") });

		List<String> lst3 = s.solve(input3);
		for (String sa : lst3)
			System.out.print(" " + sa);
	}
}
