package qirui.filesystem;
import java.util.*;


public class Dir extends Entry {
	private Map<String, Entry> content;

	public Dir(String name, Dir parent) {
		super(name, parent);
		content = new HashMap<>();
	}

	public Entry[] list() {
		Entry[] entries = new Entry[content.size()];
	
		int i = 0;
		for (Entry e : content.values()) {
			entries[i++] = e;
		}
		return entries;
	}

	public void add(Entry newEntry) {
		content.put(newEntry.name, newEntry);
	}

	public Entry get(String name) {
		return this.content.get(name);
	}

	public int numberOfFiles() {
		int count = 0;
		for (Entry e: content.values()) {
			if (e instanceof Dir) {
				count += ((Dir) e).numberOfFiles();
			} else {
				count++;
			}
		}
		return count;
	}

	@Override
	public boolean isFile() {
		return false;
	}
}