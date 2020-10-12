package qirui.filesystem;

import java.util.*;

public class FileSystem {
	
	private Dir root;
	private Dir current;
	
	public FileSystem() {
		this.root = new Dir("/", null);
		this.current = root;
	}

	public void mkdir(String path) {
		List<Entry> entries = resolve(path);
		if (entries.get(entries.size() - 1) != null) {
			throw new IllegalArgumentException("Directory already exists: " + path);
		}
		// String[] names = path.split("/");
		String name = path.substring(path.lastIndexOf('/') + 1);
		final Dir parent = (Dir) entries.get(entries.size() - 2);
		Dir newD = new Dir(name,parent);
		parent.add(newD);
	}

	public Dir cd() {
		current = current.parent;
		return current;
	}

	public Dir cd(String path) {
		List<Entry> entries = resolve(path);
		Entry last = entries.get(entries.size() - 1);
		if (last == null || !(last instanceof Dir)) {
			throw new IllegalArgumentException("No such directory");
		}
		current = (Dir) last;
		return current;
	}

	public String currentPath() {
		StringBuilder path = new StringBuilder();
		Deque<Entry> entries = new ArrayDeque<>();

		Entry curr = current;
		while (curr != root) {
			entries.offerFirst(curr);
			curr = curr.parent;
		}

		while (entries.size() > 0) {
			path.append('/');
			path.append(entries.pollFirst().getName());
		}
		return path.toString();
	}

	public Entry[] list() {
		Entry[] entries = current.list();
		for (Entry e : entries) {
			System.out.println(e.info());
		}
		return entries;
	}
	public Entry[] list(String path) {
		List<Entry> entries = resolve(path);
		Entry last = entries.get(entries.size() - 1);
		if (last == null || !(last instanceof Dir)) {
			throw new IllegalArgumentException("No such directory");
		}
		return ((Dir) last).list();
	}

	private List<Entry> resolve(String path) {
		String[] names = path.split("/");

		if (path.charAt(0) == '/') {
			// go from root
			return search(root, names);
		}
		// go from current
		return search(current, names);
	}

	private List<Entry> search(Entry entry, String[] names) {
		List<Entry> res = new ArrayList<>();
		res.add(entry);

		for (String component : names) {
			if (entry == null || !(entry instanceof Dir)) {
				throw new IllegalArgumentException("Invalid path");
			}
			if (!component.isEmpty()) {
				entry = ((Dir) entry).get(component);
				res.add(entry);
			}
		}
	
		return res;
	}


}