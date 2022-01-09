package src.ayravainentuuli.util;

/**
 * The class TicTacToe contains methods to execute the required functions for the game.
 */
public class TicTacToe {
    private static String [][] board;

/**
 * Constructor for class TicTacToe.
 *
 * @param BOARD_SIZE for determining the size of the array
 */
    public TicTacToe(int BOARD_SIZE) {

        board = new String [BOARD_SIZE][BOARD_SIZE];
        initializeBoard(BOARD_SIZE);
    }
    // the board layout at the beginning
    private void initializeBoard(int BOARD_SIZE) {
        for (int i=0; i<BOARD_SIZE; i++) {
            for (int j=0; j<BOARD_SIZE; j++) {
                board [i][j] = "   ";
            }
        }
    }
    /**
     * Prints current board status.
     */
    public void printBoard() {

        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
                if (j != board.length - 1) {
                System.out.print(board[i][j] + "|");
                } else {
                    System.out.print(board[i][j]);
                }
            }
            if (i != board.length - 1) {
                String strboard = "---+";
                String strboardPlus = "";

                for (int h=0; h<board.length; h++) {
                    strboardPlus += strboard;
                }
                System.out.print("\n" + strboardPlus +"\n");
            } 
        }
    }
    /**
     * Returns boolean to determine whether the game is over.
     *
     * Calls different methods to check for a win on the x-axis, y-axis and both diagonals.
     * The game ends if the method returns true.
     *
     * @param currentPlayerMark mark that is searched on the board
     * @param marksForWin the amount of marks needed for a win
     * @return boolean value
     */
    public boolean checkForWin(String currentPlayerMark, int marksForWin) {
        boolean win = false;

        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
                // if a player mark is found, check for more
                if (board[i][j] == currentPlayerMark) {
                    if (checkRowsForWin(i, j, currentPlayerMark, marksForWin)) {
                        win = true;
                    } else if (checkColumnsForWin(i, j, currentPlayerMark, marksForWin)) {
                        win = true;
                    } else if (checkDiagonalForWin(i, j, currentPlayerMark, marksForWin)) {
                        win = true;
                    } else if (checkAntiDiagonalForWin(i, j, currentPlayerMark, marksForWin)) {
                        win = true;
                    }
                }
            }
        
        }
        return win;
    }
    // Game ends if method returns true
    private boolean checkRowsForWin(int start, int j, String currentPlayerMark, int marksForWin) {
        boolean win = false;
        int markCount = 0;

        for (int i=start; i<board.length; i++) {
            // If two same marks are found next to each other on the same row
            // the amount of marks found increases
            if (board[i][j] == currentPlayerMark) {
                markCount++;
                // If the amount of same marks is equal to or greater than the required amount for a win
                // the method returns true and the game ends
                if (markCount >= marksForWin) {
                    win = true;
                }
            } else {
                return win;
            }
        }
        return win;
    }

    // Game ends if method returns true
    private boolean checkColumnsForWin(int i, int start, String currentPlayerMark, int marksForWin) {
        
        boolean win = false;
        int markCount = 0;

        for (int j=start; j<board.length; j++) {
            // If two same marks are found next to each other on the same column
            // the amount of marks found increases
            if (board[i][j] == currentPlayerMark) {
                markCount++;
                // If the amount of same marks is equal to or greater than the required amount for a win
                // the method returns true and the game ends
                if (markCount >= marksForWin) {
                    win = true;
                    break;
                }
            } else {
                return win;
            }
        }
        return win; 
    }
    // Game ends if method returns true
    private boolean checkDiagonalForWin(int i, int j, String currentPlayerMark, int marksForWin) {
        
        boolean win = false;
        int markCount = 0;
        int offset = 0;

        // Check diagonal for marks from top to bottom and left to right
        while (true) {
            if (board[i+offset][j+offset] == currentPlayerMark) {
                markCount++;
                // If the amount of same marks is equal to or greater than the required amount for a win
                // the method returns true and the game ends
                if (markCount >= marksForWin) {
                    win = true;
                    break;
                }
            } else {
                break;
            }
            // Break loop if index i or j goes over the edge of the board
            offset++;
            if (i + offset >= board.length || j + offset >= board.length) {
                break;
            }
        } 
        return win;
    }
    // Game ends if method returns true
    private boolean checkAntiDiagonalForWin(int i, int j, String currentPlayerMark, int marksForWin) {
        
        boolean win = false;
        int markCount = 0;
        int offset = 0;

        // Check antidiagonal for marks from bottom to top and left to right
        while (true) {
            if (board[i-offset][j+offset] == currentPlayerMark) {
                markCount++;
                // If the amount of same marks is equal to or greater than the required amount for a win
                // the method returns true and the game ends
                if (markCount >= marksForWin) {
                    win = true;
                    break;
                }
            } else {
                break;
            }
            // Break loop if index i or j goes over the edge of the board
            offset++;
            if (i - offset < 0 || j + offset >= board.length) {
                break;
            }
        }
        return win;
    }
    /**
     * Generates a random computer move using Math.random().
     *
     * Randomizes new values for x and y coordinates until an empty spot on the board is found.
     *
     * @param computerMark computer's mark which place on the board is to be determined
     */
    public void computerMove(String computerMark) {

        int cMoveX = (int)(Math.random()* board.length);
        int cMoveY = (int)(Math.random()* board.length);

        // Search for an empty spot on the board
        while(board[cMoveY][cMoveX] != "   ") {
            cMoveX = (int)(Math.random()* board.length);
            cMoveY = (int)(Math.random()* board.length);
        }

        placeMark(cMoveX, cMoveY, computerMark);
    }
    /**
     * Returns changed String argument.
     *
     * If current player mark is " x " return " o ", otherwise return " x ".
     *
     * @param currentPlayerMark is to be changed
     * @return changed String
     */
    public String changePlayerMark(String currentPlayerMark) {
        if (currentPlayerMark == " x ") {
            currentPlayerMark = " o ";
            return currentPlayerMark;
        } else {
            return (currentPlayerMark = " x ");
        }
    }
    /**
     * Returns boolean value, depending on two arguments' int values.
     * 
     * Checks if String can be placed on the given x- and y-coordinates. 
     * If the values represent an empty spot on the board, currentMark can be placed 
     * there and method returns false, otherwise true.
     *
     * @param x x-coordinate value
     * @param y y-coordinate value
     * @param currentMark player's or computer's mark to be placed on the board
     * @return boolean value
     */
    public boolean placeMark(int x, int y, String currentMark) {
        try {
            if (board[y][x] == "   ") {
                board[y][x] = currentMark;
                return false;
            } else {
                System.out.println("\n------------------------------------------------------------------------------------");
                System.out.println("\n==> WARNING: You can't replace an existing mark <==");
                System.out.println("\n    Please, try again");
                System.out.println("------------------------------------------------------------------------------------\n");
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\n------------------------------------------------------------------------------------");
            System.out.println("\n==> WARNING: You can't go outside of the board! <==");
            System.out.println("\n    Please, try again");
            System.out.println("------------------------------------------------------------------------------------\n");
        }
        return true;
    }
}