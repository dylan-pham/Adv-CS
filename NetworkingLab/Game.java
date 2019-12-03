import java.io.Serializable;

public class Game implements Serializable {
    private int[][] board;
    private int turn;
    private boolean endGame;
  
    public Game() {
        board = new int[3][3];
        turn = 1; // 1 will be player 1, 2 will be player 2
        endGame = false;
    }
  
    public void insertXO(int row, int column) {
        if (!endGame) {
            if (turn == 1 && board[row][column] == 0) {
                board[row][column] = 1;
                turn = 2;
            } else if (turn == 2 && board[row][column] == 0) {
                board[row][column] = 2;
                turn = 1;
            }
        }
    }

    public boolean getEndGame() {
        return endGame;
    }

    public void reset() {
        turn = 1;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++ ) {
                board[r][c] = 0;
            }
        }

        endGame = false;
    }
  
    public boolean checkFull() {
        boolean full = false;
        int countZero =0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == 0){
                    countZero ++;
                }
            }
        }
        if (countZero == 0) {
            endGame = true;
            full = true;
        }
        return full;
    }

    public int checkTicTacToe() {
        int s1 = board[0][0];
        int s2 = board[0][1];
        int s3 = board[0][2];
        int s4 = board[1][0];
        int s5 = board[1][1];
        int s6 = board[1][2];
        int s7 = board[2][0];
        int s8 = board[2][1];
        int s9 = board[2][2];

        if (s1 == s2 && s2 == s3 && s1 != 0) {
            if (s1 == 1) {
                endGame = true;
                return 1;
            } else {
                endGame = true;
                return 2;
            }
        } else if (s4 == s5 && s5 == s6 && s4 != 0) {
            if (s4 == 1) {
                endGame = true;
                return 1;
            } else {
                endGame = true;
                return 2;
            }
        } else if (s7 == s8 && s8 == s9 && s7 != 0) {
            if (s7 == 1) {
                endGame = true;
                return 1;
            } else {
                endGame = true;
                return 2;
            }
        } else if (s1 == s4 && s4 == s7 && s1 != 0) {
            if (s1 == 1) {
                endGame = true;
                return 1;
            } else {
                endGame = true;
                return 2;
            }
        } else if (s2 == s5 && s5 == s8 && s2 != 0) {
            if (s2 == 1) {
                endGame = true;
                return 1;
            } else {
                endGame = true;
                return 2;
            }
        } else if (s3 == s6 && s6 == s9 && s3 != 0) {
            if (s3 == 1) {
                endGame = true;
                return 1;
            } else {
                endGame = true;
                return 2;
            }
        } else if (s1 == s5 && s5 == s9 && s1 != 0) {
            if (s1 == 1) {
                endGame = true;
                return 1;
            } else {
                endGame = true;
                return 2;
            }
        } else if (s3 == s5 && s5 == s7 && s3 != 0) {
            if (s3 == 1) {
                endGame = true;
                return 1;
            } else {
                endGame = true;
                return 2;
            }
        }

        return 0;
    }

    public int getTurn() {
        return turn;
    }

    public int getSpot(int row, int column) {
        if (board[row][column] == 1) {
            return 1;
        } else if (board[row][column] == 2) {
            return 2;
        } else {
            return 0;
        }
    }

    public int[][] getBoard() {
        return board;
    }
}