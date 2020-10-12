package qirui;
import java.util.*;

class FreqStack {

    Map<Integer, Integer> freq;
    Map<Integer, Deque<Integer>> counter;
    int max;
    int size;

    public FreqStack() {
        this.freq = new HashMap<>();
        this.counter = new HashMap<>();
        this.max = 0;
        this.size = 0;
    }

    public void push(int x) {
        Integer count = freq.get(x);
        if (count == null) {
            // new one
            freq.put(x, 1);
            if (!counter.containsKey(1)) {
                counter.put(1, new ArrayDeque<>());
            }
            counter.get(1).offerFirst(x);
            if (max == 0) {
                max++;
            }
        } else {
            freq.put(x, count + 1);
            if (!counter.containsKey(count + 1)) {
                counter.put(count + 1, new ArrayDeque<>());
            }
            counter.get(count + 1).offerFirst(x);
            if (count + 1 > max) {
                max = count + 1;
            }
        }
        size++;
    }

    public int pop() {
        if (size == 0) {
            return -1;
        }
        int res = counter.get(max).pollFirst();
        freq.put(res, max - 1);
        if (counter.get(max).size() == 0) {
            max--;
        }
        return res;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */

// can use a map to store freq -> stack
// another map to store element -> freq

// when adding a new element, check the freq then store into the stack map with freq + 1 as key
// updating maximum attribute

// remove element:
//     main a maximum freq number
//     remove from that stack, and check if stack is empty, if empty, maximum - 1