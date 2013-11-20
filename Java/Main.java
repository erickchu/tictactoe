public class Game {
    public static char player;
    public static boolean gameOver;
    public static Board tictactoe;

    public static void main(String[] args) {
        player = 'O';
        Board tictactoe = new Board();

        intro();
        tictactoe.displayBoard();
        while (!tictactoe.gameOver) {
            // New turn; change to player making next move
            if (player == 'O')      player = 'X';
            else if (player == 'X') player = 'O';

            // Select a board to play on if it's the first move of the game or
            // if the previous move directed to a complete board
            tictactoe.selectBoard(player);

            // Make a move
            tictactoe.makeMove(player);

            // Check move
            tictactoe.checkMove(player);

            // Display board
            tictactoe.displayBoard();
        }
    }
    
    public static void intro() {
        System.out.println("\nWelcome to ULTIMATE TIC-TAC-TOE!!");
        System.out.println("=================================");

        System.out.println("- See http://mathwithbaddrawings.com/ultimate-tic-tac-toe-original-post/");
        System.out.println("  for instructions or more information about the game.");

        System.out.println("- Choose board/move using numbers from 1 to 9, where the position");
        System.out.println("  of the number is given by the following:\n");

        System.out.println(" 1 | 2 | 3 ");
        System.out.println("---+---+---");
        System.out.println(" 4 | 5 | 6 ");
        System.out.println("---+---+---");
        System.out.println(" 7 | 8 | 9 \n");

        System.out.println("Let's begin!");
    }
}
