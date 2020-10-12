package qirui;

import java.util.*;


class LFUCache {

    private Map<Integer, ListNode> map;
    private Map<Integer, DDL> counter;
    private int capacity;
    private int min;

    public LFUCache(int capacity) {
        this.map = new HashMap<>();
        this.counter = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            touch(key);
            return map.get(key).val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            touch(key);
            map.get(key).val = value;
        } else {
            evict();
            if (map.size() >= capacity) {
                return;
            }
            ListNode node = new ListNode(value, key);
            if (!counter.containsKey(node.freq)) {
                counter.put(node.freq, new DDL());
            }
            counter.get(node.freq).add(node);
            map.put(key, node);
            min = 1;
        }
    }

    private void touch(int key) {
        ListNode node = map.get(key);
        counter.get(node.freq).remove(node);
        if (counter.get(node.freq).size == 0 && node.freq == min ) {
            min++;
        }
        if (!counter.containsKey(node.freq + 1)) {
            counter.put(node.freq + 1, new DDL());
        }
        node.freq++;
        counter.get(node.freq).add(node);
    }

    private void evict() {
        if (counter.size() <= 0 || map.size() < capacity) {
            return;
        }
        ListNode node = counter.get(min).removeHead();
        map.remove(node.key);
    }

    static class ListNode {
        ListNode next;
        ListNode prev;
        int key;
        int val;
        int freq;

        public ListNode(int val, int key) {
            this.val = val;
            this.key = key;
            this.freq = 1;
        }
    }

    static class DDL {
        ListNode head;
        ListNode tail;
        int size;

        public ListNode remove(ListNode node) {
            if (node == null) {
                return null;
            }
            if (node == head && node == tail) {
                head = null;
                tail = null;
            } else if (node == head) {
                head = node.next;
                head.prev = null;
                node.next = null;
            } else if (node == tail) {
                tail = node.prev;
                node.prev = null;
                tail.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.prev = null;
                node.next = null;
            }
            this.size--;
            return node;
        }

        public ListNode add(ListNode node) {
            if (head == null && tail == null) {
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            this.size++;
            return node;
        }

        public ListNode removeHead() {
            ListNode node = head;
            if (this.size > 0) {
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head = head.next;
                    head.prev = null;
                }
                this.size--;
            }
            return node;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class LFUTester {
    public static void main(String[] args) {

        LFUCache cache = new LFUCache( 2 /* capacity */ );

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
        System.out.println(Long.MAX_VALUE);
        Map<int[], Integer> map = new HashMap<>();
        map.put(new int[] {1, 2}, 1);
        System.out.println(map.containsKey(new int[] {1, 2}));
    }
}