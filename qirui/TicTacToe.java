package qirui;

import java.util.*;

interface GameBoard {
    Player getWinner();

    int placeMove(Move move, Player player) throws IllegalArgumentException;

    boolean isValidMove(Move move);
}

class Move {
    int x;
    int y;
}

class Player {
    private int id;
    private int score;

    public int addScore(int score) {
        this.score += score;
        return this.score;
    }
}

class TicTacToeBoard implements GameBoard {
    private final static int[] SIGN = new int[]{1, -1};
    private Map<Player, Integer> map;
    private final int[][] board;
    private int[] cols;
    private int[] rows;
    private int leftDiag;
    private int rightDiag;
    private Player winner;
    private int occupied;

    public TicTacToeBoard(Player p1, Player p2, int size) {
        this.map = new HashMap<>();
        this.map.put(p1, SIGN[0]);
        this.map.put(p2, SIGN[1]);
        this.board = new int[size][size];
        this.cols = new int[size];
        this.rows = new int[size];
    }

    @Override
    public boolean isValidMove(Move move) {
        if (move.x < 0 || move.y < 0 || move.x >= this.board.length || move.y >= this.board.length) {
            return false;
        }
        return board[move.x][move.y] != SIGN[0] && board[move.x][move.y] != SIGN[1];
    }

    @Override
    public Player getWinner() {
        return this.winner;
    }

    @Override
    public int placeMove(Move move, Player player) throws IllegalArgumentException {
        if (!isValidMove(move)) {
            throw new IllegalArgumentException("Invalid move");
        }
        this.board[move.x][move.y] = map.get(player);
        if (move.x == move.y) {
            leftDiag += map.get(player);
        }
        if (move.x + move.y == this.board.length - 1) {
            rightDiag += map.get(player);
        }
        this.cols[move.y] += map.get(player);
        this.rows[move.x] += map.get(player);
        this.occupied++;
        int sum = this.board.length;
        if (
            cols[move.y] == Math.abs(sum) ||
            rows[move.x] == Math.abs(sum) ||
            rightDiag == Math.abs(sum) ||
            leftDiag == Math.abs(sum)
        ) {
            this.winner = player;
            return 1;
        }
        if (this.occupied == sum * sum) {
            return 0; // tied
        }
        return -1;
    }
}

public class TicTacToe {
    private GameBoard board;
    private Player one;
    private Player two;
    private Map<Player, List<Move>> history;

    public TicTacToe(Player player1, Player player2, int size) {
        this.board = new TicTacToeBoard(player1, player2, size);
        this.one = player1;
        this.two = player2;
        this.history = new HashMap<>();
        this.history.put(player1, new ArrayList<>());
        this.history.put(player2, new ArrayList<>());
    }

    public boolean isValidMove(Move move) {
        return this.board.isValidMove(move);
    }
    /*
     * @return Integer 1 - someone wins, 0 - tie, -1 - no one wins yet
     */
    public int makeMove(Move move, Player player) {
        int status = this.board.placeMove(move, player);
        this.history.get(player).add(move);
        return status;
    }

    public Player getWinner() {
        return this.board.getWinner();
    }

    public boolean anyWinner() {
        return this.board.getWinner() != null;
    }

}




/*

Classes:

    Board
    User
    Move
    Game

    game - board
    user - move - game

    Game


    Board - interface get winner, placeMove

    Game - start, makeMove






 */