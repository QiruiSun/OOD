package qirui;
import java.util.*;

class SnakeGame {
    private final static Map<String, int[]> DIRS = new HashMap<>(){{
        put("U", new int[] {-1,0});
        put("L", new int[] {0,-1});
        put("R", new int[] {0,1});
        put("D", new int[] {1,0});
    }};
    Deque<int[]> body;
    Set<String> bodyDetails;
    int[] head;
    int width;
    int height;
    Queue<int[]> foodStorage;
    int[] currFood;
    int score;

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        populateFoodStorage(food);
        this.currFood = foodStorage.poll();
        this.bodyDetails = new HashSet<>();
        this.body = new ArrayDeque<>();
        this.head = new int[] {0, 0};
        bodyDetails.add(0 + "," + 0);
        body.offerLast(this.head);
    }

    private void populateFoodStorage(int[][] food) {
        this.foodStorage = new ArrayDeque<>();
        for (int[] f : food) {
            foodStorage.offer(f);
        }
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int row = this.head[0] + DIRS.get(direction)[0];
        int col = this.head[1] + DIRS.get(direction)[1];

        if (row < 0 || col < 0 || row >= height || col >= width) {
            return -1;
        }

        int[] position = new int[] {row, col};

        if (bodyDetails.contains(row + "," + col)) {
            return -1;
        }
        if (currFood != null && row == currFood[0] && col == currFood[1]) {
            eat();
        } else {
            int[] tail = body.pollLast();
            body.offerFirst(position);
            bodyDetails.remove(tail[0] + "," + tail[1]);
            bodyDetails.add(position[0] + "," + position[1]);
            head = position;
        }
        return score;
    }

    private void eat() {
        int[] position = new int[] {currFood[0], currFood[1]};
        body.offerFirst(position);
        bodyDetails.add(currFood[0] + "," + currFood[1]);
        head = position;
        currFood = foodStorage.poll();
        score++;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */

class SnakeTester {
    public static void main(String[] args) {
        SnakeGame snake = new SnakeGame(3,3, new int[][] {{2,0}, {0,0}, {0,2}, {2,2}});
        snake.move("D");
        snake.move("D");
        snake.move("R");
        snake.move("U");
        snake.move("U");
        snake.move("L");
        snake.move("D");
        snake.move("R");
        snake.move("R");;
        snake.move("U");
        snake.move("L");
        snake.move("D");
        String verison1 = "1.03.02.2";
        String[] a = verison1.split("\\.");
        System.out.println(a);

        List<Integer> list1 = Arrays.asList(1,2);
        List<Integer> list2 = Arrays.asList(1,2);

        Map<List<Integer>, Integer> map = new HashMap<>();
        map.put(list1, 1);
        System.out.println((int)(Math.random() * 53));
        Random ran = new Random();

        String test = " 6-4 / 2 ";
        System.out.println(test.replaceAll(" ", ""));
    }
}


//
//class SnakeGame {
//    private final static Map<String, int[]> DIRS = new HashMap<>(){{
//        put("U", new int[] {-1,0});
//        put("L", new int[] {0,-1});
//        put("R", new int[] {0,1});
//        put("D", new int[] {1,0});
//    }};
//    Deque<int[]> body;
//    Queue<int[]> foodStorage;
//    int width;
//    int height;
//    int score;
//
//    /** Initialize your data structure here.
//     @param width - screen width
//     @param height - screen height
//     @param food - A list of food positions
//     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
//    public SnakeGame(int width, int height, int[][] food) {
//        this.width = width;
//        this.height = height;
//        populateFoodStorage(food);
//        // this.bodyDetails = new HashSet<>();
//        this.body = new ArrayDeque<>();
//        // bodyDetails.add(0 +"," + 0);
//        body.offerLast(new int[] {0, 0});
//    }
//
//    private void populateFoodStorage(int[][] food) {
//        this.foodStorage = new ArrayDeque<>();
//        for (int[] f : food) {
//            foodStorage.offer(f);
//        }
//    }
//
//
//    /** Moves the snake.
//     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
//     @return The game's score after the move. Return -1 if game over.
//     Game over when snake crosses the screen boundary or bites its body. */
//    public int move(String direction) {
//        int[] head = body.peekFirst();
//        int row = head[0] + DIRS.get(direction)[0];
//        int col = head[1] + DIRS.get(direction)[1];
//
//        if (row < 0 || col < 0 || row >= height || col >= width) {
//            return -1;
//        }
//
//        int[] position = new int[] {row, col};
//
//        if (meetFood(row, col)) {
//            eat();
//        } else {
//            int[] tail = body.pollLast();
//            if (contains(position)) {
//                return -1;
//            }
//            body.offerFirst(position);
//            // bodyDetails.remove(tail[0] + "," + tail[1]);
//            // bodyDetails.add(position[0] + "," + position[1]);
//        }
//
//        return score;
//    }
//
//    private boolean contains(int[] pos) {
//        for (int[] point : body) {
//            if (point[0] == pos[0] && point[1] == pos[1]) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean meetFood(int x, int y) {
//        if (foodStorage.size() > 0 && x == foodStorage.peek()[0] && foodStorage.peek()[1] == y) {
//            return true;
//        }
//        return false;
//    }
//
//    private void eat() {
//        int[] curr = foodStorage.poll();
//        int[] position = new int[] {curr[0], curr[1]};
//        body.offerFirst(position);
//        // bodyDetails.add(curr[0] + "," + curr[1]);
//        score++;
//    }
//}
