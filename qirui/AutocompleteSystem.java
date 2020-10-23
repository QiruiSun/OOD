package qirui;
import java.util.*;

class AutocompleteSystem {
    private final static int NUM = 3;
    private StringBuilder currSb;
    private TrieNode currNode;
    private TrieNode root;
    private Map<String, Word> dict;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.currSb = new StringBuilder();
        this.root = new TrieNode();
        this.currNode = this.root;
        this.dict = new HashMap<>();
        takeHistory(sentences, times);
    }

    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if (c == '#') {
            // sentence finished
            Word word = dict.get(currSb.toString());
            if (word != null) {
                word.count++;
            } else {
                addWord(currSb.toString(), 1);
            }

            currSb = new StringBuilder();
            currNode = root;
        } else {
            currSb.append(c);
            if (currNode != null) {
                getSuggestions(result, c);
            }
        }
        return result;
    }

    private void getSuggestions(List<String> result, char c) {
        int index = c == ' ' ? 26 : c - 'a';
        currNode = currNode.next[index];
        if (currNode == null) {
            return;
        }
        Collections.sort(currNode.words);
        for (int i = 0; i < NUM && i < currNode.words.size(); i++) {
            result.add(currNode.words.get(i).val);
        }
    }

    private void takeHistory(String[] sentences, int[] times) {
        for (int i = 0; i < times.length; i++) {
            addWord(sentences[i], times[i]);
        }
    }

    private void addWord(String sentence, int freq) {
        Word word = new Word(sentence);
        word.count = freq;
        dict.put(sentence, word);

        addToTrie(sentence);
    }

    private void addToTrie(String sentence) {
        Word word = dict.get(sentence);
        if (word == null) {
            return;
        }
        TrieNode curr = root;

        for (int i = 0; i < sentence.length(); i++) {
            int index = sentence.charAt(i) == ' ' ? 26 : sentence.charAt(i) - 'a';
            TrieNode next = curr.next[index];
            if (next == null) {
                next = new TrieNode();
                curr.next[index] = next;
            }
            next.words.add(word);
            curr = next;
        }
    }

    static class TrieNode {
        TrieNode[] next;
        List<Word> words;

        public TrieNode() {
            this.next = new TrieNode[27];
            this.words = new ArrayList<>();
        }
    }

    static class Word implements Comparable<Word>{
        String val;
        int count;

        public Word(String val) {
            this.val = val;
        }

        @Override
        public int compareTo(Word another) {
            if (another.count == this.count) {
                return this.val.compareTo(another.val);
            }
            return Integer.compare(another.count, this.count);
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

class autoTester {
    public static void main(String[] args) {
        AutocompleteSystem sys = new AutocompleteSystem(new String[]{"i love you","island","iroman","i love leetcode"}, new int[]{5,3,2,2});
        sys.input('i');
        sys.input(' ');
    }
}

