package qirui.unix_find_function;

import java.util.*;

public class UnixFindFunc {
    Entry root;

    public UnixFindFunc() {
        root = new Entry("root");
    }

    public UnixFindFunc(Entry root) {
        this.root = root;
    }

    public List<Entry> findFilesWithFilter(Entry dir, Filter filter) {
        if (dir == null) {
            dir = root;
        }
        List<Entry> res = new ArrayList<>();
        helper(dir, res, filter);
        return res;
    }

    private void helper(Entry dir, List<Entry> list, Filter filter) {
        if (dir == null) {return;}
        if (dir.isFile) {
            if (filter.match(dir)) {
                list.add(dir);
            }
            return;
        }
        for (Entry child : dir.entries) {
            helper(child, list, filter);
        }
    }
}


interface Filter {
    boolean match(Entry file);
}

class SizeFilter implements Filter {
    Operation ops;
    int size;

    public SizeFilter(int size, Operation ops) {
        this.ops = ops;
        this.size = size;
    }

    @Override
    public boolean match(Entry file) {
        switch (ops) {
            case LT:
                return file.size < size;
            case LTE:
                return file.size <= size;
            case GT:
                return file.size > size;
            case GTE:
                return file.size >= size;
            case E:
                return file.size == size;
        }
        return false;
    }


    enum Operation {
        LT,
        GT,
        LTE,
        GTE,
        E;
    }
}

// Object composition
class AndFilterCombo implements Filter {
    List<Filter> filters;

    public AndFilterCombo(Filter[] filters) {
        this.filters = new ArrayList<>();
        for (Filter f : filters) {
            this.filters.add(f);
        }
    }
    @Override
    public boolean match(Entry file) {
        for (Filter f : filters) {
            if (!f.match(file)) {
                return false;
            }
        }
        return true;
    }
}


class Entry {
    Entry[] entries;
    String name;
    int size;
    boolean isFile;

    public Entry(String name) {
        entries = new Entry[20];
        this.name = name;
    }
}

class FindTestRun {
    public static void main(String[] args) {
        Entry dir = new Entry("root");
        int bound = 50;

        for (int i = 0; i < 20; i++) {
            Entry child = new Entry("entry" + i);
            int size = (int)(Math.random() * bound);
            child.size = size;
            boolean isFile = Math.random() > 0.5 ? true : false;
            child.isFile = isFile;
            dir.entries[i] = child;
        }

        Filter lessThan40 = new SizeFilter(40, SizeFilter.Operation.LT);
        Filter greaterThan15 = new SizeFilter(15, SizeFilter.Operation.GT);
        Filter combo = new AndFilterCombo(new Filter[] {lessThan40, greaterThan15});
//        UnixFindFunc tester = new UnixFindFunc(dir);
//        List<Entry> result = tester.findFilesWithFilter(null, combo);
//
//        for (Entry node: result) {
//            System.out.println(node.name);
//        }

        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }
}







class LFUCache {
    TreeMap<int[], Integer> counter;
    Map<Integer, Integer> map;
    Map<Integer, int[]> freq;
    int capacity;
    int time = 0;

    public LFUCache(int capacity) {
        counter = new TreeMap<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(a[0], b[0]);
            }
        });
        map = new HashMap<>();
        freq = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        time++;
        if (map.containsKey(key)) {
            update(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        time++;
        if (map.containsKey(key)) {
            update(key);
        } else {
            if (map.size() == capacity) {
                evict();
            }
            map.put(key, value);
            int[] entry = new int[] {1, time};
            freq.put(key, entry);
            counter.put(entry, key);
        }
    }

    private void update(int key) {
        counter.remove(freq.get(key));
        freq.get(key)[0]++;
        freq.get(key)[1] = time;
        // freq.put(key, freq.get(key) + 1);
         counter.put(freq.get(key), key);
    }

    private void evict() {
        boolean found = false;
        while (!found) {
            int[] first = counter.firstKey();
            int key = counter.get(first);
            if (freq.get(key)[0] != first[0]) {
                counter.pollFirstEntry();
            } else {
                found = true;
            }
        }

        int keyToGo = counter.get(counter.firstKey());
        freq.remove(keyToGo);
        map.remove(keyToGo);
        counter.pollFirstEntry();
    }
}