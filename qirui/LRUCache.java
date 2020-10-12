package qirui;
import java.util.*;

public class LRUCache {
    Map<Integer, ListNode>  map;
    ListNode head;
    ListNode tail;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new ListNode();
        this.tail = new ListNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            touch(key);
            return map.get(key).val;
        }
        return -1; // or
//        throw new IllegalArgumentException("no such element");
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).val = value;
            touch(key);
            return;
        }
        if (map.size() == capacity) {
            evict();
        }
        if (map.size() < capacity) {
            ListNode node = new ListNode(value, key);
            map.put(key, node);
            addNode(node);
        }
    }

    private void touch(int key) {
        ListNode node = map.get(key);
        removeNode(node);
        addNode(node);
    }

    private void evict() {
        int key = head.next.key;
        removeHead();
        map.remove(key);
    }

    private void removeNode(ListNode node) {
        ListNode prev = node.prev;
        prev.next = node.next;
        node.next.prev = prev;
        node.next = null;
        node.prev = null;
    }

    private void removeHead() {
        ListNode node = head.next;
        removeNode(head.next);
    }

    private void addNode(ListNode node) {
        ListNode prev = tail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = tail;
        tail.prev = node;
    }


    static class ListNode {
        ListNode next;
        ListNode prev;
        int val;
        int key;

        public ListNode(int val, int key) {
            this.val = val;
            this.key = key;
        }
        public ListNode() {
            this.val = val;
            this.key = key;
        }
    }
}


class LRUTester {
    public static void main(String[] args) {
        LRUCache a = new LRUCache(2);
        a.get(2);
        a.put(2,6);
        a.get(1);
        a.put(1,5);
        a.put(1,2);
        a.get(1);
        System.out.print(a.get(2));
    }
}