import java.util.Scanner;

public class TicTacToe {
    static char[] board = new char[9];
    static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        printBoard();

        while (true) {
            int move = getMove();
            makeMove(move);
            printBoard();

            if (checkWin()) {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            } else if (checkDraw()) {
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = '-';
        }
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i * 3 + j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static int getMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player " + currentPlayer + ", enter your move (1-9): ");
        int move = scanner.nextInt();
        while (move < 1 || move > 9 || board[move - 1] != '-') {
            System.out.println("Invalid move. Please try again.");
            System.out.print("Player " + currentPlayer + ", enter your move (1-9): ");
            move = scanner.nextInt();
        }
        return move - 1;
    }

    private static void makeMove(int move) {
        board[move] = currentPlayer;
    }

    private static boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i * 3] == board[i * 3 + 1] && board[i * 3 + 1] == board[i * 3 + 2] && board[i * 3] != '-') {
                return true;
            }
            if (board[i] == board[i + 3] && board[i + 3] == board[i + 6] && board[i] != '-') {
                return true;
            }
        }
        if (board[0] == board[4] && board[4] == board[8] && board[0] != '-') {
            return true;
        }
        if (board[2] == board[4] && board[4] == board[6] && board[2] != '-') {
            return true;
        }
        return false;
    }

    private static boolean checkDraw() {
        for (char c : board) {
            if (c == '-') {
                return false;
            }
        }
        return true;
    }
}
