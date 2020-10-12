import java.util.*;
import java.time.*;
import java.util.concurrent.TimeUnit;
/*
* class Point {
*   public int x;
*   public int y;
*   public Point(int x, int y) {
*     this.x = x;
*     this.y = y;
*   }
* }
*/

class QuickStart {
    private final static int[][] DIRS = {{1,0}, {-1,0}, {0,1}, {0, -1}};

    public static void main(String[] args) {
        // int[] input = {3,5,1,2,4,8};
        String input = "abcbcbd";
        // new LongestPalindrom().longestPalindrome(input);
        // System.out.println(new LongestPalindrom().longestPalindrome(input));
        int[][] array = {{1, 2}, {0, 4}, {5, 6}};
        int[][] r = {{0,100},{1,2},{2,3},{3,4}};
        // Arrays.sort(array, new Comparator<int[]>() {
        //     public int compare(int[] o1, int[] o2) {
        //       return o1[0] < o1[1] ? -1 : 1;
        //     }
        // });
        int[] nodes = {11,2,93,64,15,36,27,7,7,7};
        ListNode test = ListNode.build(nodes);
        // System.out.println(new ReverseAlternateNodes().removeDup(test));
        

        // t.sort(t.buildList(nodes));
     
       
        // ListNode test = ListNode.build(tester);
        // int res = ListNode.length(test);
        // System.out.println(test.scan());
        // List<Integer> test1 = Arrays.asList(4,5,-1,null,null,0,-2);
        // TreeNode root = TreeNode.build(test1);
        // TreeNode.print(root);
        Inventory b = new Inventory(ITEM.CHIPS, 5000, 15);
        // b.cost();
        // int p = 5 + (int) (Math.random() * (10 - 5 + 1));
        char[] s1 = "anagram".toCharArray();
        char[] s2 = "nagaram".toCharArray();
        Arrays.sort(s1);
        Arrays.sort(s2);

        StringBuilder curr = new StringBuilder('d');
        curr.append("  ");
        curr.append("d");
        QuickStart q = new QuickStart();
        String[] dic = {"cat", "cats", "sand", "and"};
        int[] k1 = {2,3,3,3,3,4};
        int[] k2 = {85,75,67,24,58,35,71,24,63,100,89,41,99,41,95,73,40,83,86,77,42,40,31,60,2,73,34,24,13,52,9,44,56,30,24,23,82,11,14,8,96,2,11,75,84,30,94,62,99,75,42,85,100,59,60,21,34,26,46,66,39,43,32,2,86,89,6,76,89,94,56,9,55,64,28,7,36,1,39,66,81,53,78,26,70,28,35,82,59,30,65,76,17,83,47,5,56,31,3,49,49,29,32,46,70,3,62,37,100,60,33,36,87,21,41,32,56,82,82,12};
        int[] k3 = {1,2,2,2,3,3,3,6,7};
        String[] words = {"wrt","wrf","er","ett","rftt"};
        // EnlosingBlackPixels t = new EnlosingBlackPixels();
        // int[][] test1 = {{0, 0, 1, 0}, {0, 1, 1, 0}, {0, 1, 0, 0}};
        // System.out.println(q.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));;
        
        String[] names = new String[] {"ALICE", "CHARLES", "ERIC", "SOPHIAL"};
        // System.out.println(q.expressiveWords("aaa", new String[] {"aaaa"}));
        int a = 119;
char d = (char)(a + '0');
int k = (int) (d - 'a');
String root =  "1;3,2,4#;5,6#E#E#;E#E#;";
System.out.println("/root/foo/bar/lol".split("/"));
String[] path = "/root/foo/bar/lol".split("/");
System.out.println(path.length);
// MyCircularDeque deque = new MyCircularDeque(3);
// deque.insertLast(1);
// int[][] matrix = {{-4,-5}};
// NumMatrix obj = new NumMatrix(matrix);
// obj.sumRegion(0,0,0,1);
        // IntervalW[] intervals = {new IntervalW(0,1,1), new IntervalW(2,3,2), new IntervalW(4,5,2)};
        // new Dir.Test();

        List<String> list1 = Arrays.asList("abc", "bd", "kilz");
        List<String> list2 = Arrays.asList("abc", "bd", "kild");


    }

    private int days(int cap, int[] weights) {
        
      int day = 1;
      int curr = cap;
      for (int i = 0; i < weights.length; i++) {
          if (weights[i] > cap) {
              return Integer.MAX_VALUE;
          }
          if (curr >= weights[i]) {
              curr -= weights[i];
          } else  {
              day++;
              curr = cap - weights[i];
          } 
      }
      return day;
  }
    class Node {
      public int val;
      public List<Node> children;
  
      public Node() {}
  
      public Node(int _val) {
          val = _val;
      }
  
      public Node(int _val, List<Node> _children) {
          val = _val;
          children = _children;
      }
  };

    public Node deserialize(String data) {
        
      Queue<Node> queue = new ArrayDeque<>();
      int left = 0;
      while (data.charAt(left) != ';') {
          left++;
      }
      Node root = new Node(Integer.parseInt(data.substring(0, left)));

      queue.offer(root);
      left++; // skip ';'
      
      while (queue.size() > 0 && left < data.length()) {
          int size = queue.size();
          for (int i = 0; i < size; i++) {
              Node curr = queue.poll();
              
              if (data.charAt(left) == 'E') {
                  left += 2;
                  continue;
              }
              curr.children = new ArrayList<>();
              int j = left;
              while (j < data.length() && data.charAt(j) != '#') {
                  j++;
              }
              String[] children = data.substring(left, j).split(",");
              
              for (String child : children) {
                  Node childNode = new Node(Integer.parseInt(child));
                  curr.children.add(childNode);
                  queue.offer(childNode);
              }
              left = j + 1;
          }
          left++;
      }
      
      return root;
         
  }

    public String countOfAtoms(String formula) {
        
      int[] end = new int[1];
      Map<String, Integer> map = helper(formula, 0, end);
      
      StringBuilder res = new StringBuilder();
       
      for (Map.Entry<String, Integer> entry : map.entrySet()) {
          res.append(entry.getKey());
          if (entry.getValue() != 0) {
              res.append(entry.getValue());
          }
      }
      return res.toString();
  }
  
  private Map<String, Integer> helper(String str, int left, int[] end) {
      Map<String, Integer> map = new TreeMap<>();
      int len = str.length();
      
      while (left < len && str.charAt(left) != ')') {
          if (str.charAt(left) == '(') {
              for (Map.Entry<String, Integer> record : helper(str, left+1, end).entrySet()) {
                  map.put(
                      record.getKey(), 
                      map.getOrDefault(record.getKey(), 0) + record.getValue()
                  );
              }
              left = end[0];
              continue;
          }
          
          int j = 1;
          while (left + j < len && Character.isLowerCase(str.charAt(left+j))) {
              j++;
          }
          String curr = str.substring(left, left + j);
          
          int[] currCount = getNum(str, left + j);
          int plus = currCount[0] == 0 ? 1 : currCount[0];
          map.put(curr, map.getOrDefault(curr, 0) + plus);
          left = currCount[1];
      }
      left++;
      if (left >= len) {
        return map;
      }
      int[] count = getNum(str, left);
      if (count[0] != 0) {
          for (Map.Entry<String, Integer> record : map.entrySet()) {
              map.put(
                  record.getKey(), 
                  record.getValue() * count[0]
              );
          }
      }
      end[0] = count[1];
      
      return map;
  }
  
  private int[] getNum(String str, int index) {
      int j = 0;
      int count = 0;
      while (index + j < str.length() && Character.isDigit(str.charAt(index+j))) {
          count = count * 10 + (str.charAt(index+j) - '0');
          j++;
      }
      return new int[] {count, index+j};
  }


    // Time 
    // Space

    public int expressiveWords(String S, String[] words) {
      int res = 0;
      for (String w : words) {
          if (match(S, w)) {
              res++;
          }
      }
      return res;
  }
  
  private boolean match(String a, String b) {
      int one = 0;
      int two = 0;
      
      while (one < a.length() && two < b.length()) {
          if (a.charAt(one) == b.charAt(two)) {
              int len1 = repeat(a, one);
              int len2 = repeat(b, two);
              
              if (len1 < 3 && len1 != len2) {
                  return false;
              }
              if (len1 > 3 && len1 < len2) {
                  return false;
              }
              one += len1;
              two += len2;
          } else {
              return false;
          }
      }
      if (one == a.length() && two == b.length()) {
          return true;
      }

    return false;
  }
  
  private int repeat(String s, int i) {
      int count = 0;
      while (i + count < s.length() && s.charAt(i) == s.charAt(i+count)) {
          count++;
      }
      return count;
  }

    public int minBox(int a) {
      
      int[] M = new int[a+1];
      M[0] = 0;
      
      for (int i = 1; i < M.length; i++) {
        M[i] = i;
        for (int j = 1; j * j <= i; j++) {
          M[i] = Math.min(M[i], M[i - j*j] + 1);
        } 
      }
      return M[a];
    }


    














    public boolean oneDiff(int a, int b) {
      int c = a ^ b;
      
      int pos = 0;
      int count = 0;
      while (pos < 32) {
          if ((c >>> pos & 1) == 1) {
              count++;
          }
          pos++;
          if (count > 1) {
              return false;
          }
      }
      return count == 1 ? true : false;
  }



    public int minCost(int[] cuts, int length) {
      // Write your solution here
      return helper(cuts, 0, length, 0, length);
    }
  
    private int helper(int[] cuts, int cut, int l, int start, int end) {
      if (cut == cuts.length) {
        return 0;
      }
      if (start >= end) {
        return 0;
      }
      int min = Integer.MAX_VALUE - 2*l;
      int curr = end - start;
      for (int i = cut; i < cuts.length; i++) {
  
        swap(cuts, cut, i);
        
        if (cuts[cut] >= start && cuts[cut] <= end) {
          int cost1 = curr + helper(cuts, cut + 1, l, start, cuts[cut]);
          int cost2 = curr + helper(cuts, cut + 1, l, cuts[cut], end);
          min = Math.min(min, Math.min(cost1, cost2));
        }
        swap(cuts, cut, i);
      }
      return min;
    }
  
    private void swap(int[] array, int f, int t) {
      int temp = array[f];
      array[f] = array[t];
      array[t] = temp;
    }

    public int numIncreasingSubsequences(int[] a) {
      // Write your solution here
      int mod = (int) (Math.pow(10, 9) + 7);
      Arrays.sort(a);
      int num = 0;
  
      int[] M = new int[a.length];
      M[0] = 1;
      for (int i = 0; i < M.length; i++) {
        for (int j = 0; j < i; j++) {
          if (a[j] < a[i]) {
            M[i] += M[j];
          }
        }
        M[i] = M[i] % mod;
        num += M[i];
      }
      return num;
    }
    class Point {
      public int x;
      public int y;
      public Point(int x, int y) {
        this.x = x;
        this.y = y;
      }
    }

    public int most(Point[] points) {
      // Write your solution here.
      
      int max = 0;
  
      for (int i = 0; i < points.length; i++) {
        int most = 0;
        int sameX = 0;
        Map<Double, Integer> map = new HashMap<>();
        for (int j = 0; j < points.length; j++) {
          if (i == j) {
            continue;
          }
          int yDiff = points[i].y - points[j].y;
          int xDiff = points[i].x - points[j].x;
          if (xDiff == 0) {
            sameX++;
          } else {
            double slope = (yDiff + 0.0) / xDiff;
            Integer record = map.get(slope);
            if (record == null) {
              record = 0;
            } 
            map.put(slope, record++);
            most = Math.max(most, record);
          }
        }
        max = Math.max(max, Math.max(sameX, most) + 1);
      }
      return max;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
      //write your code here
      Set<String> set = new HashSet<>(wordList);
      Map<String, List<String>> graph = new HashMap<>();
  
      for (String word : wordList) {
        graph.put(word, new ArrayList<>());
      }
  
      for (String word : wordList) {
        char[] curr = word.toCharArray();
        for (int i = 0; i < curr.length; i++) {
          for (char c = 'a'; c <= 'z'; c++) {
            curr[i] = c;
            String s = new String(curr);
            if (set.contains(s)) {
              addToGraph(graph, word, s);
              addToGraph(graph, s, word);
            }
          }
          curr[i] = word.charAt(i); // backtrack
        }
      }
  
      // need something to keep track of steps (prev string)
      Map<String, Set<String>> memo = new HashMap<>();
      Set<String> visited = new HashSet<>();
      Queue<String> queue = new ArrayDeque<>();
      queue.offer(beginWord);
      int steps = 1;
      while (queue.size() > 0) {
        int size = queue.size();
        boolean found = false;
        for (int i = 0; i < size; i++) {
          String curr = queue.poll();
          visited.add(curr);
          for (String vetex : graph.get(curr)) {
            if (!visited.contains(vetex)) {
                queue.offer(vetex);
              Set<String> records = memo.get(vetex);
              if (records == null) {
                records = new HashSet<>();
              }
              records.add(curr);
              memo.put(vetex, records);
              if (vetex.equals(endWord)) {
                found = true;
              }
            }
          }
        }
        if (found) {
          break;
        }
        steps++;
      }
  
      List<List<String>> res = new ArrayList<>();
      List<String> curr = new ArrayList<>();
      curr.add(endWord);
      generate(memo, res, curr, endWord, steps + 1);
      return res;
    }
  
    private void generate(Map<String, Set<String>> memo, List<List<String>> res, List<String> curr, String word, int size) {
      
      Set<String> prevs = memo.get(word);
      if (prevs == null || prevs.size() == 0) {
        if (curr.size() == size) {
          List<String> ans = new ArrayList<>(curr);
          Collections.reverse(ans);
          res.add(ans);
        }
        
        return;
      }
  
      for (String p : prevs) {
        curr.add(p);
        generate(memo, res, curr, p, size);
        curr.remove(curr.size() - 1);
      }
    }
  
    private void addToGraph(Map<String, List<String>> graph, String one, String two) {
      List<String> firstRecords = graph.get(one);
      firstRecords.add(two);
      graph.put(one, firstRecords);
    }

    public boolean canWin(int[] nums) {
      // Write your solution here
      int[][] M = new int[nums.length][nums.length];
      int sum = 0;
      for (int interval = 0; interval < nums.length; interval++) {
        sum += nums[interval];
        for (int i = 0, j = interval; j < nums.length; i++, j++) {
          int nextTakei = 0;
          int nextTakej = 0;
          int allTakej = 0;
          if (i + 2 <= j) {
            nextTakei = M[i+2][j];
            nextTakej = M[i+1][j-1];
            allTakej = M[i][j-2];
          } 
          M[i][j] = Math.max(nums[i] + Math.min(nextTakei, nextTakej), nums[j] + Math.min(nextTakej, allTakej));
        }
      }
      
      return sum - M[0][nums.length - 1] <= M[0][nums.length - 1];
    }

    public int deleteAndEarn(int[] nums) {
      // Write your solution here
      Arrays.sort(nums);
  
      int take = nums[0];
      int noTake = 0;
  
      for (int i = 1; i < nums.length; i++) {
        if (nums[i-1] + 1 == nums[i]) {
          take = noTake + nums[i];
          noTake = take;
        } else if (nums[i-1]== nums[i]) {
          take = take + nums[i];
          noTake = noTake;
        } else {
          take = take + nums[i];
          noTake = take;
        }
      }
      return Math.max(take, noTake);
    }

    public String alienOrder(String[] words) {
      // Write your solution here
      Map<Character, Set<Character>> graph = new HashMap<>();
      int[] incoming = new int[26];
  
      for (int i = 0; i < words.length - 1; i++) {
        String w1 = words[i];
        String w2 = words[i+1];

        int j = 0;
        while (j < w1.length() && j < w2.length() && w1.charAt(j) == w2.charAt(j)) {
          j++;
        }
        if (j < w1.length() && j < w2.length()) {
          char curr = w1.charAt(j);
          char next = w2.charAt(j);
          Set<Character> nodes = graph.get(curr);
          if (nodes == null) {
            nodes = new HashSet<>();
          }
          nodes.add(next);
          graph.put(curr, nodes);
          incoming[next -'a']++;

          if (graph.get(next) == null) {
            graph.put(next, new HashSet<>());
          }
        }
      }
  
      char[] order = new char[graph.size()];
      Queue<Character> queue = new ArrayDeque<>();
  
      for (Character node : graph.keySet()) {
        if (incoming[node - 'a'] == 0) {
          queue.offer(node);
        }
      }
      int numofNodesProcessed = 0;
  
      while (queue.size() > 0) {
        char curr = queue.poll();
        order[numofNodesProcessed++] = curr;
        Set<Character> nodes = graph.get(curr);
        for (Character node : nodes) {
          incoming[node - 'a']--;
          if (incoming[node -'a'] == 0) {
            queue.offer(node);
          }
        }
      }
      
      return numofNodesProcessed == order.length ? new String(order) : "";
    }

    public List<Integer> closest(int[] a, int[] b, int[] c, int k) {
      // Write your solution here
      PriorityQueue<Cell> pq = new PriorityQueue<>();
  
      Cell start = new Cell(0, 0, 0, distance(a[0], b[0], c[0]));
      pq.offer(start);
      Cell curr = null;
      Map<String, Boolean> visited = new HashMap<>();
      visited.put(start.toS(), true);
      while (pq.size() > 0 && k > 0) {
        curr = pq.poll();
  
        if (curr.x + 1 < a.length) {
          Cell xPlus = new Cell(curr.x + 1, curr.y, curr.z, distance(a[curr.x + 1], b[curr.y], c[curr.z]));
          
          if (!visited.containsKey(xPlus.toS())) {
            pq.offer(xPlus);
            visited.put(xPlus.toS(), true);
          }
        }
        if (curr.y + 1 < b.length) {
          Cell yPlus = new Cell(curr.x, curr.y+ 1, curr.z, distance(a[curr.x], b[curr.y+ 1], c[curr.z]));
          if (!visited.containsKey(yPlus.toS())) {
            pq.offer(yPlus);
            visited.put(yPlus.toS(), true);
          }
        }
        if (curr.z + 1 < c.length) {
          Cell zPlus = new Cell(curr.x, curr.y, curr.z + 1, distance(a[curr.x], b[curr.y], c[curr.z + 1]));
          if (!visited.containsKey(zPlus.toS())) {
            pq.offer(zPlus);
            visited.put(zPlus.toS(), true);
          }
        }
        k--;
      }
      return Arrays.asList(curr.x, curr.y, curr.z);
    }
  
    private int distance(int x, int y, int z) {
      return (int) Math.sqrt(x * x + y * y + z * z);
    }
  
    static class Cell implements Comparable<Cell>{
      int x;
      int y;
      int z;
      int val;
  
      public Cell(int x, int y, int z, int val) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.val = val;
      }
  
      public String toS() {
        return "" + this.x + this.y + this.z;
      }
      @Override
      public int compareTo(Cell other) {
        if (this.val == other.val) {
          return 0;
        }
        return this.val < other.val ? -1 : 1;
      }
  
  
    }

 

    public int cuts(String input, String[] dict) {
      int[] M = new int[input.length() + 1];
      
      Set<String> set = new HashSet<>();
      
      for (String w : dict) {
        set.add(w);
      }
      
      for (int i = 0; i < input.length(); i++) {
        for (int j = i; j >= 0; j--) {
          String subWord = input.substring(j, i + 1);
          if (set.contains(subWord)) {
            if (j == 0) {
              M[i+1] += 1;
            } else {
              M[i+1] += M[j];
            }
          }
        } 
      }
      return M[input.length()];
    }

    public List<List<Integer>> combinations(int target) {
      // Write your solution here
      List<Integer> curr = new ArrayList<>();
      List<List<Integer>> res = new ArrayList<>();
      
      if (target <= 3) {
        return res;
      }
      helper(curr, res, target, 2);
  
      return res;
    }
  
    private void helper(List<Integer> curr, List<List<Integer>> res, int target, int index) {
      if (target == 1) {
        res.add(new ArrayList<>(curr));
        return;
      }
      if (index > target) {
        return;
      }
      if (target % index != 0) {
        helper(curr, res, target, index + 1);
        return;
      }
  
      // not using current index
      helper(curr, res, target, index + 1);

      while (target > index) {
        if (target % index != 0) {
          break;
        }
        target /= index;
        curr.add(index);
        helper(curr, res, target, index + 1);
      }
    }
    
    
    public int[] dedup(int[] array) {
      // Write your solution here
      int slow = 0;
      int fast = 0;
  
      while (fast < array.length) {
        if (slow > 0 && fast < array.length && array[fast] == array[slow-1]) {
          
          while (fast < array.length && array[fast] == array[slow-1]) {
            fast++;
          }
          slow--;
          continue;
        }
        int j = fast;
        while (j < array.length && array[j] == array[fast]) {
          j++;
        }
        int len = j - fast;
        if (len == 1) {
          array[slow++] = array[fast];
        }
        fast = j;
       
      }
      return Arrays.copyOf(array, slow);
    }

    public int[] countArray(int[] array) {
      // Write your solution here
      Map<Integer, Integer> memo = new HashMap<>();
      for (int i : array) {
        memo.put(i, 0);
      }
  
      helper(array, memo, 0, array.length - 1);
  
      int[] res = new int[array.length];
  
      for (int i = 0; i< array.length; i++) {
        int curr = array[i];
        int count = memo.get(curr);
        res[i] = count;
      }
      return res;
    }
  
    private int[] helper(int[] array, Map<Integer, Integer> memo, int left, int right) {
      if (left >= right) {
        return new int[] {array[left]};
      }
      int mid = left + (right - left) / 2;
  
      int[] leftArray = helper(array, memo, left, mid);
      int[] rightArray = helper(array, memo, mid+1, right);
  
      return merge(leftArray, rightArray, memo);
    }
  
    private int[] merge(int[] a, int[] b, Map<Integer, Integer> memo) {
      int[] temp = new int[a.length + b.length];
  
      int one = 0;
      int two = 0;
      int index = 0;
  
      while (one < a.length && two < b.length) {
        if (a[one] < b[two]) {
          temp[index] = a[one];
          int count = memo.get(a[one]);
          memo.put(a[one], count + index - one);
          index++;
          one++;
        } else {
          temp[index++] = b[two++];
        }
      }

      while (one < a.length) {
        temp[index] = a[one];
        int count = memo.get(a[one]);
        memo.put(a[one], count + index - one);
        index++;
        one++;
      }
      while (two < b.length) {
        temp[index++] = b[two++];
      }
      return temp;
    }

    public int[] keepDistance(int k) {
      // Write your solution here.
      int[] res = new int[k * 2];
  
      boolean found = helper(res, k, 1);
  
      if (found) {
        return res;
      } 
      return null;
    }
  
    public boolean helper(int[] res, int k, int num) {
      if (num == k + 1) {
        return true;
      }
  
      for (int i = 0; i < res.length; i++) {
        int next = i + num + 1;
        if (res[i] == 0 && next < res.length && res[next] == 0) {
          res[i] = num;
          res[next] = num;
          if (helper(res, k, num + 1)) {
            return true;
          } else {
            res[i] = 0;
            res[next] = 0;
          }
        }
      }
      return false;
    }

  
    private static final char[] PS = {'(', ')', '<', '>', '{', '}'};
    public List<String> validParenthesesIII(int l, int m, int n) {
      // Write your solution here
      Deque<Integer> stack = new ArrayDeque<>();
      StringBuilder curr = new StringBuilder();
      List<String> res = new ArrayList<>();
      int[] count = new int[] {l, l, m, m, n, n};
      int total = (l + m + n) * 2;
      helper(res, curr, stack, count, total);
  
      return res;
    }
  
    private void helper(List<String> res, StringBuilder curr, Deque<Integer> stack, int[] count, int total) {
      if (curr.length() == total) {
        res.add(curr.toString());
        return;
      }

      for (int i = 0; i < count.length; i++) {
        if (i % 2 == 0) { // opening type
          // stack.peekFirst() greater means has higher priority  '{ - 4' > '( - 0'
          if (count[i] > 0 && (stack.size() == 0 || stack.peekFirst() > i)) {
            curr.append(PS[i]);
            stack.offerFirst(i);
            count[i]--;
            helper(res, curr, stack, count, total * 2);
            count[i]++;
            stack.pollFirst();
            curr.deleteCharAt(curr.length() -1);
          }
        } else {
          if ( stack.size() > 0 && stack.peekFirst() + 1 == i) {
            curr.append(PS[i]);
            stack.pollFirst();
            count[i]--;
            helper(res, curr, stack, count, total * 2);
            stack.offerFirst(i-1);
            count[i]++;
            curr.deleteCharAt(curr.length() -1);
          }
        }
      }
    }

// Time Complexity
// Space Complexity

public void validIf(int n) {
  char[] curr = new char[n * 2];
  
  dfs(n, curr, 0, 0, 0);
}

private void dfs(int n, char[] curr, int left, int right, int index) {
    
  if (left == right && left == n) {
    printBlock(curr);
    return;
  }
  
  if (left > right) {
    curr[index] = ')';
    dfs(n, curr, left, right + 1, index+1);
  }
  
  // adding new if block
  if (left < n) {
    curr[index] = '(';
    dfs(n, curr, left + 1, right, index+1);
  }
}

private void printBlock(char[] array) {
  int space = 0;
  for (int i = 0; i < array.length; ++i) {
    if (array[i] == '(') {
      printSpace(space);
      System.out.println("if {");
      space += 2;
    } else {
      space -= 2;
      printSpace(space);
      System.out.println("}");
    }
  }
}

private void printSpace(int n) {
  while (n > 0) {
    System.out.print(" ");
    n--;
  }
}



  public int maxWeightSum(IntervalW[] intervals) {
    // Write your solution here.
    Arrays.sort(intervals, new Comparator<IntervalW>() {
      @Override
      public int compare(IntervalW a, IntervalW b) {
        if (a.start == b.start) {
          if (a.end == b.end) {
            return 0;
          }
          return a.end < b.end ? -1 : 1;
        }
        return a.start < b.start ? -1 : 1;
      }
    });

    int[] M = new int[intervals.length];
    M[0] = intervals[0].weight;

    for (int i = 1; i < M.length; i++) {
      if (intervals[i].start >= intervals[i-1].end) {
        // no overlap
        M[i] += intervals[i].weight; 
      } else {
        M[i] = Math.max(M[i-1] - intervals[i-1].weight + intervals[i].weight, M[i-1]);
      }
    }

    // sort intervals
    return M[M.length-1];
  }


    public String deDup(String input) {
      // Write your solution here
      int slow = 0;
      int fast = 0;
      char[] curr = input.toCharArray();
  
      while (fast < curr.length) {
        int j = 0;
        while (fast + 1 + j < curr.length && curr[fast + j] == curr[fast + 1 + j]) {
          j++;
        }
        
  
        if (j == 0) {
          swap(curr, fast++, slow++);
        } else {
          fast += j + 1;
        }
      }
      return new String(curr, 0, slow);
    }

    private void swap(char[] nums, int f, int t) {
      char temp = nums[f];
      nums[f] = nums[t];
      nums[t] = temp;
    }
    

  public String removeDuplicates(String input, int k) {
    int slow = -1;
    int fast = 0;
    char[] inputArray = input.toCharArray();
    while (fast < inputArray.length) {
      char curr = inputArray[fast];
      if (slow < 0) {
        slow++;
        inputArray[slow] = curr;
      } else {
        if (curr != inputArray[slow]) {
          slow++;
        inputArray[slow] = curr;
        } else {
          int num = 0;
          while (slow - num >= 0 && inputArray[fast] == inputArray[slow - num]) {
            num++;    
          }
          int j = 0;
          while (fast + j < inputArray.length && inputArray[fast + j] == inputArray[slow] && j + num < k) {
            j++;
          }
          if (j + num == k) {
            fast += j;
            slow -= num;
            continue;
          }
          
          slow++;
          inputArray[slow] = curr;
        }
      }
      fast++;
    }

    return new String(inputArray, 0, slow + 1);
  }
    
    private void reverse(int[] array, int left, int right) {
      while (left < right) {
        int temp = array[right];
        array[right] = array[left];
        array[left] = temp;
        right--;
        left++;
      }
    }










    public void sort(LinkedList<Integer> s1) {
      LinkedList<Integer> s2 = new LinkedList<Integer>();
      // Write your solution here.   
      
      while (s1.size() > 0) {
        int min = Integer.MAX_VALUE;
        int count = 0;
        while (s1.size() > 0) {
          int curr = s1.pop();
          min = Math.min(min, curr);
          s2.push(curr);
        }
        
        while (s2.size() > 0 && s2.peek() >= min) {
          int ele = s2.pop();
          if (ele != min) {
            s1.push(ele);
          }        
        }
        while (count > 0) {
          s2.push(min);
          count--;
        }
      }
  
      while (s2.size() > 0) {
        s1.push(s2.pop());
      }
    }

    public TreeNode reconstruct(int[] pre) {
      // Write your solution here
      int[] memo = {1};
      TreeNode root = new TreeNode(pre[0]);
  
      addNode(root, pre, memo, Integer.MIN_VALUE, Integer.MAX_VALUE);
  
      return root;
    }
  
    private TreeNode addNode(TreeNode root, int[] pre, int[] memo, int min, int max) {
      if (memo[0] >= pre.length) {
        return null;
      }
  
      int val = pre[memo[0]];
      if (val > max || val < min) {
        return null;
      }
  
      if (root == null) {
        root = new TreeNode(val);
        memo[0]++;
      }

      root.left = addNode(root.left, pre, memo, min, root.val);
      root.right = addNode(root.right, pre, memo, root.val, max);
  
      return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
        // Write your solution here.
        TreeNode[] ans = new TreeNode[1];
    
        find(root, one, two, ans);
        return ans[0];
      }
    
      private boolean find(TreeNode root, TreeNode one, TreeNode two, TreeNode[] ans) {
        if (root == null) {
          return false;
        }
    
        boolean curr = (root == one || root == two);
    
        boolean left = find(root.left, one, two, ans);
        boolean right = find(root.left, one, two, ans);
    
        if (left && curr || right && curr || left && right) {
          ans[0] = root;
        }
        return curr;
      }
    

    public String[] expand(String S) {
        Deque<String> stack = new ArrayDeque<>();
        List<List<String>> options = new ArrayList<>();
        List<String> res = new ArrayList<>();
        
        int i = 0;
        
        while (i < S.length()) {
            char c = S.charAt(i);
            
            if (c == '{') {
                i++;
                int size = res.size();
                while (i < S.length() && S.charAt(i) != '}') {
                    if (S.charAt(i) != ',') {
                        if (size > 0) {
                           for (int j = 0; j< res.size(); j ++) {
                                res.set(j, res.get(j) + S.charAt(i));
                            } 
                        }
                        else {
                            res.add("" + S.charAt(i));
                        }
                    }
                   
                    
                    i++;
                }
            }
            else {
                if (res.size() > 0) {
                    for (int k = 0; k< res.size(); k ++) {
                        res.set(k, res.get(k) + c);
                    }
                }
                else {
                    res.add("" + c);    
                }
            }
            i++;
        }
        
        return res.toArray(new String[0]);
    }

    public String decodeString(String s) {
        Deque<String> stack = new ArrayDeque<>();
        
        // StringBuilder sb = new StringBuilder();
        
        for (int j = 0; j < s.length(); j++) {
            // System.out.println(stack);
            char c = s.charAt(j);
            if (c == ']') {
                // try to find the correspoding [
                String temp = "";
                // System.out.println(stack);
                // System.out.println(stack.peekFirst() == "a");
                String curr = stack.peekFirst();
                while (stack.size() > 0 && !curr.equals("[")) {
                    temp += stack.pollFirst();
                    curr = stack.peekFirst();
                }
                stack.pollFirst(); // remove '['
                // System.out.println(temp);
                int times = Integer.parseInt(stack.pollFirst());
                String output = "";
                for (int i=1; i<= times; i++) {
                    output += temp;
                }
                stack.offerFirst(output);
            }
            else {
                stack.offerFirst(String.valueOf(c));
            }
        }
        
        String result = "";
        
        while (stack.size() > 0) {
            result = stack.pollFirst() + result;
        }
        
        return result;
    }

    public int maxTrapped(int[] array) {
        // Write your solution here
    
        int[] left = new int[array.length];
        int[] right = new int[array.length];
    
        int leftMost = 0;
        for (int i=0; i<array.length; i++) {
          left[i] = leftMost;
          leftMost = Math.max(leftMost, array[i]);
        }
    
        int rightMost = 0;
        for (int i=array.length-1; i>=0; i--) {
          right[i] = rightMost;
          rightMost = Math.max(rightMost, array[i]);
        }
    
        int maxWater = 0;
    
        for (int i=0; i<array.length; i++) {
          int height = Math.min(left[i], right[i]);
          if (height > array[i]) {
            maxWater += height - array[i];
          }
        }
    
        return maxWater;
    } 

    public int trapWater(int[] A) {
        // Write your solution here
        int left = 0;
        int right = A.length-1;
        int leftMax = 0;
        int rightMax = 0;
        int max = 0;
    
        for (int i=0; i<A.length; i++) {
            leftMax = Math.max(A[left], A[i]);
            rightMax = Math.max(A[right], A[i]);
    
          if (left >= right) {
            max += right - A[i];
            right--;
          }
          else {
            max += left - A[i];
            left++;
          }
        }
        return max;
    }

    public ListNode merge(List<ListNode> listOfLists) {
        // Write your solution here/.
    
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
          public int compare(ListNode n1, ListNode n2) {
            return n1.val - n2.val;
          }
        });
    
        for (ListNode node : listOfLists) {
          heap.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        
        while (!heap.isEmpty()) {
            ListNode curr = heap.poll();
            if (curr.next != null) {
              heap.offer(curr.next);
            }
            head.next = curr;
            head = head.next;
        }
        
    
        return dummy.next;
    }

    // public void sort(LinkedList<Integer> s1) {
    //     LinkedList<Integer> s2 = new LinkedList<Integer>();
    //     // Write your solution here.
        
    //     int currMin = Integer.MAX_VALUE;
    //     int minCount = 0;
    
    //     while (s1.size() > 0) {
    //       while (s1.size() > 0) {
    //         int curr = s1.pop();
    
    //         if (curr < currMin) {
    //           currMin = curr;
    //           minCount = 1;
    //         }
    //         else if (curr == currMin) {
    //           minCount++;
    //         }
    //         s2.push(curr);
    //       }
    
    //       while (s2.size() > 0 && s2.peek() >= currMin) {
    //         int value = s2.pop();
    //         if (value > currMin) {
    //           s1.push(value);
    //         }
    //       } 
    
    //       for (int i = 0; i < minCount; i++) {
    //         s2.push(currMin);
    //       }
    //       currMin = Integer.MAX_VALUE;
    //     }
    //   }

    public LinkedList<Integer> buildList(int[] array) {
        LinkedList<Integer> linkedlist = new LinkedList<>();
        for (int i : array) {
            linkedlist.add(i);
        }
        return linkedlist;
    }

}


class Solution {

    private int size;
    LinkedList<Integer> stack;
    LinkedList<Integer> queue;
    
    public Solution() {
      // Write your solution here.
      stack = new LinkedList<>();
      queue = new LinkedList<>();
    }
    
    public void offerFirst(int element) {
      // add to queue-stack
      queue.push(element);
      size++;
    }
    
    public void offerLast(int element) {
      stack.push(element);
      size++;
    }
    
    public Integer pollFirst() {
      if (isEmpty()) {
        return null;
      }
      if (queue.size() == 0) {
        shuffle(stack, queue);
      }
      int val = queue.pop();
      size--;
      return val;
    }
    
    public Integer pollLast() {
      if (isEmpty()) {
        return null;
      }
      if (stack.size() == 0) {
        shuffle(queue, stack);
      }
      int val = stack.pop();
      size--;
      return val;
    }
    
    public Integer peekFirst() {
      if (isEmpty()) {
        return null;
      }
      if (queue.size() == 0) {
        shuffle(stack, queue);
      }
      return queue.peek();
    }
    
    public Integer peekLast() {
      if (isEmpty()) {
        return null;
      }
      if (stack.size() == 0) {
        shuffle(queue, stack);
      }
         
      return stack.peek();
    }
    
    public int size() {
      return size;
    }
    
    public boolean isEmpty() {
      return size == 0;
    }
  
    private void shuffle(LinkedList<Integer> from, LinkedList<Integer> to) {
      if (to.size() > 0) {
        return;
      }
      LinkedList<Integer> buffer = new LinkedList<>();

      for (int i =0; i<from.size() / 2; i++) {
        buffer.push(from.pop());  
      }
      while (from.size() > 0) {
        to.push(from.pop());
      }
      while (buffer.size() > 0) {
        from.push(buffer.pop());
      }
    }
  }
  
  
  
  
  //      queue   |    stack
  //     head              tail
  //  need a temp stack to move half elements from stack-stack to queue-stack, or Vice Versa  
  // we need to do this, when one of the stack is empty when polling or poping
class StackWithMin {
    private LinkedList<Element> minStack;
    private LinkedList<Integer> stack;
  
    public StackWithMin() {
      // write your solution here
      minStack = new LinkedList<>();
      stack = new LinkedList<>();
    }
    
    public int pop() {
      if (stack.size() == 0) {
        return -1;
      }
  
      int curr = stack.peek();
  
      if (curr == minStack.peek().val && minStack.peek().index == stack.size()) {
        minStack.pop();
      }
  
      return stack.pop();
    }
    
    public void push(int element) {
      int currSize = stack.size();
      Element newVal = new Element(element, currSize + 1);
      if (minStack.size() == 0 || minStack.peek().val > element) {
        minStack.push(newVal);
      }
  
      stack.push(element);
    }
    
    public int top() {
      if (stack.size() == 0) {
        return -1;
      }
      
      return stack.peek();
    }
    
    public int min() {
      if (stack.size() == 0) {
        return -1;
      }
  
      return minStack.peek().val;
    }
  }

class Element {
    public int val;
    public int index;
  
    public Element(int val, int index) {
      this.val = val;
      this.index = index;
    }
  }

enum Day {
  MON, TUES
}



class IntervalW {
  public int start;
  public int end;
  public int weight;
  public IntervalW(int start, int end, int weight) {
    this.start = start;
    this.end = end;
    this.weight = weight;
  }
}



  // public int sqrt(int x) {
  //   // Write your solution here
  //   int res = x;
  //   while (res * res > x) {
  //     res = res / 2;
  //   }
  //   return res;
  // }







