package src.ayravainentuuli;

import src.ayravainentuuli.util.TicTacToe;
import java.util.Scanner;

/**
 * Main class for the game Tic Tac Toe.
 *
 * @author Tuuli Ayravainen
 */

public class Main {
    public static int BOARD_SIZE;
    public static String PLAYER_MARK = " x ";
    public static int marksForWin;
    private static boolean playerTurn = true;

    /**
     * This method manages the overall progress of the game by calling methods from the class TicTacToe.
     *
     * Player's input is handled. The player and computer take turns. The program stops once winner is found or the board is full.
     *
     * @param args command line arguments, not used
     */
    public static void main(String [] args) throws InterruptedException {
        // Instructions for getting started
        // Determine board size
        System.out.println("\n___x___o___x___o___x___o___x___o___x___o___x___o___x___");
        System.out.println("\n           WELCOME TO A GAME OF TIC-TAC-TOE!");
        System.out.println("___x___o___x___o___x___o___x___o___x___o___x___o___x___");
        System.out.println("\nYou will be playing against the computer.");

        Scanner input = new Scanner(System.in);
        System.out.println("\nLet's start with the size of the board.\n(Please note: Minimum size is 3)");

        do {
            try {
                // Ask board size until given integer is >= 3
                while (BOARD_SIZE < 3) {
                    BOARD_SIZE = input.nextInt();
                        if (BOARD_SIZE < 3) {
                            System.out.println("\n------------------------------------------------------------------------------------");
                            System.out.println("\n==> WARNING: Board size should be at least 3 x 3 <==");
                            System.out.println("\n    Please, try again");
                            System.out.println("------------------------------------------------------------------------------------\n");
                    }
                }
                System.out.println("Number of marks required for a win:");
                marksForWin = input.nextInt();

                // Requirements for acceptable amount of winning marks
                // Depending on the board size
                if (BOARD_SIZE >= 10) {
                    while (marksForWin > BOARD_SIZE || marksForWin < 5) {
                    System.out.println("\n------------------------------------------------------------------------------------");
                    System.out.println("\n==> WARNING: Should be more than 4, less than or equal to the size of the board <==");
                    System.out.println("\n    Please, try again");
                    System.out.println("------------------------------------------------------------------------------------\n");
                    marksForWin = input.nextInt();
                    }
                } else if (BOARD_SIZE == 3) {
                    while (marksForWin != 3) {
                    System.out.println("\n------------------------------------------------------------------------------------");
                    System.out.println("\n==> WARNING: Should be 3, when the board size is 3 x 3 <==");
                    System.out.println("\n    Please, try again");
                    System.out.println("------------------------------------------------------------------------------------\n");
                    marksForWin = input.nextInt();
                    }
                } else {
                    while (marksForWin > BOARD_SIZE || marksForWin < 3) {
                    System.out.println("\n------------------------------------------------------------------------------------");
                    System.out.println("\n==> WARNING: Should be more than 2, less than or equal to the size of the board <==");
                    System.out.println("\n    Please, try again");
                    System.out.println("------------------------------------------------------------------------------------\n");
                    marksForWin = input.nextInt();
                    }
                }
                // Exit loop when correct data has been given
                playerTurn = false;

            } catch (Exception e) {
                    System.out.println("\n------------------------------------------------------------------------------------");
                    System.out.println("\n==> WARNING: Only integers are accepted! <==");
                    System.out.println("\n    Please, try again");
                    System.out.println("------------------------------------------------------------------------------------\n");
                    input.next();
                    continue;
                }
        } while (playerTurn);

        // Goes to constructor TicTacToe which calls the method initializeBoard()
        TicTacToe game = new TicTacToe(BOARD_SIZE);
        int turns = 0;
        int totalTurns = BOARD_SIZE*BOARD_SIZE;

        System.out.println("\n------------------------------------------------------------------------------------");
        System.out.println("\nInitializing board....");
        System.out.println("------------------------------------------------------------------------------------\n");
        Thread.sleep(2000);
        System.out.println("\nThe board is ready. Good luck!");
        
        // Do - while to continue the game until one player wins or board is full
        do {
            // Print the current board
            System.out.println("\n<Current status of the board>\nTry to win the computer\n");
            game.printBoard();
            System.out.println();

            // Player turn begins
            playerTurn = true;

            while (playerTurn) {
                try {
                    System.out.println("\nEnter <COLUMN> number:");
                    int playerX = input.nextInt()-1;
                    System.out.println("Enter <ROW> number:");
                    int playerY = input.nextInt()-1;
                    System.out.println();
                    playerTurn = game.placeMark(playerX, playerY, PLAYER_MARK);
                    game.printBoard();
                    System.out.println();

                } catch (Exception e) {
                    System.out.println("\n------------------------------------------------------------------------------------");
                    System.out.println("\n==> WARNING: Only integers are accepted! <==");
                    System.out.println("\n    Please, try again");
                    System.out.println("------------------------------------------------------------------------------------");
                    input.next();
                    continue;
                }
            }
            System.out.println("\n------------------------------------------------------------------------------------");
            // Player turn has ended
            // Check for a possible win
            if (game.checkForWin(PLAYER_MARK, marksForWin)) {
                System.out.println("\n___x___o___x___o___x___o___x___o___x___o___x___o___x___");
                System.out.println("\n" + "You won!\n");
                game.printBoard();
                System.out.println();
                break;
            }
            // Next turn
            // Change player mark
            PLAYER_MARK = game.changePlayerMark(PLAYER_MARK);
            turns++;

            // Check if board is full
            if (turns >= totalTurns) {
                System.out.println("\n___x___o___x___o___x___o___x___o___x___o___x___o___x___");
                System.out.println("\nIt was a tie!\n");
                game.printBoard();
                System.out.println();
                break;
            }
            // Computer turn begins
            game.computerMove(PLAYER_MARK);

            // Computer turn has ended
            // Check for a possible win
            if (game.checkForWin(PLAYER_MARK, marksForWin)) {
                System.out.println("\n___x___o___x___o___x___o___x___o___x___o___x___o___x___");
                System.out.println("\n" + "You lost!\n");
                game.printBoard();
                System.out.println();
                break;
            }
            // Next turn
            // Change player mark
            PLAYER_MARK = game.changePlayerMark(PLAYER_MARK);
            turns++;

            // Check if board is full
            if (turns >= totalTurns) {
                System.out.println("\n___x___o___x___o___x___o___x___o___x___o___x___o___x___");
                System.out.println("\nIt was a tie!\n");
                game.printBoard();
                System.out.println();
                break;
            }
        } while (true);
    }
}