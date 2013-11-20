import java.util.*;

public class Board {
    private static char[][] boards;
    private static int[] numMoves;
    private static boolean newBoard;
    private static int selected;
    private static int lastMove;
    public static boolean gameOver;

    private Scanner s = new Scanner(System.in);

    public Board() {
        boards = new char[10][10];
        numMoves = new int[10];
        newBoard = true;

        for (int i = 0; i < 10; i++) {
            numMoves[i] = 0;
            for (int j = 0; j < 10; j++) {
                boards[i][j] = ' ';
            }
        }
        
        gameOver = false;
    }

    public boolean completedBoard(int num) {
        if ( boards[0][num] == 'D' || boards[0][num] == 'X' || boards[0][num] == 'O' ) {
            return true;
        }
        else {
            return false;
        }
    }

    public void selectBoard(char player) {
        int num = 0;
        while ( newBoard ) {
            System.out.print("Player " + player + "'s turn. ");
            System.out.print("Enter board to play on (1-9): ");
            newBoard = false;

            num = s.nextInt();
            if ( num < 1 || num > 9 )
                System.out.println("Invalid board number.");
            else if ( completedBoard(num) )
                System.out.println("Board " + num + " is already completed.");
            else {
                newBoard = false;
                selected = num;
            }
        }
    }
    
    public void makeMove(char player) {
        int pos = 0;
        boolean validMove = false;

        while ( !validMove ) {
            System.out.print("Player " + player + "'s turn. ");
            System.out.print("Playing in board " + selected + ", enter move/position (1-9): ");

            pos = s.nextInt();
            if ( pos < 1 || pos > 9 ) 
                System.out.println("Invalid move/position number.");
            else if ( boards[selected][pos] == 'X' || boards[selected][pos] == 'O' )
                System.out.println("There is already an '" + boards[selected][pos] + "' mark at that position.");
            else 
                validMove = true;
        }

        boards[selected][pos] = player;
        numMoves[selected]++;
        lastMove = pos;
        
        System.out.print("\n-------------------------------------------------------------\n");
        System.out.print("\n>> '" + player + "' is marked at " + "board " + selected + ", position " + pos + ".");
    }
    
    
    public void checkMove(char player) {
        if ( checkSmallBoard(player) ) {
            checkOverallBoard(player);
        }
        selected = lastMove;
        newBoard = completedBoard(selected);
    }
    

    public boolean checkSmallBoard(char p) {
        if ( ( p == boards[selected][1] && boards[selected][1] == boards[selected][2] && boards[selected][2] == boards[selected][3] ) ||
             ( p == boards[selected][4] && boards[selected][4] == boards[selected][5] && boards[selected][5] == boards[selected][6] ) ||
             ( p == boards[selected][7] && boards[selected][7] == boards[selected][8] && boards[selected][8] == boards[selected][9] ) ||
             ( p == boards[selected][1] && boards[selected][1] == boards[selected][4] && boards[selected][4] == boards[selected][7] ) ||
             ( p == boards[selected][2] && boards[selected][2] == boards[selected][5] && boards[selected][5] == boards[selected][8] ) ||
             ( p == boards[selected][3] && boards[selected][3] == boards[selected][6] && boards[selected][6] == boards[selected][9] ) ||
             ( p == boards[selected][1] && boards[selected][1] == boards[selected][5] && boards[selected][5] == boards[selected][9] ) ||
             ( p == boards[selected][3] && boards[selected][3] == boards[selected][5] && boards[selected][5] == boards[selected][7] ) ) {
            boards[0][selected] = p;
            numMoves[0]++;

            // For display purposes
            if (p == 'X') {
                boards[selected][1] = 'X'; boards[selected][2] = ' '; boards[selected][3] = 'X'; 
                boards[selected][4] = ' '; boards[selected][5] = 'X'; boards[selected][6] = ' ';
                boards[selected][7] = 'X'; boards[selected][8] = ' '; boards[selected][9] = 'X';
            }
            else if (p == 'O') {
                boards[selected][1] = 'O'; boards[selected][2] = 'O'; boards[selected][3] = 'O';
                boards[selected][4] = 'O'; boards[selected][5] = ' '; boards[selected][6] = 'O'; 
                boards[selected][7] = 'O'; boards[selected][8] = 'O'; boards[selected][9] = 'O';
            }
            System.out.print("\n>> Player '" + p + "' wins board " + selected + ".");
            
            return true;
        }
        else if (numMoves[selected] == 9) {
            boards[0][selected] = 'D';
            numMoves[0]++;

            boards[selected][1] = '-'; boards[selected][2] = '-'; boards[selected][3] = '-';
            boards[selected][4] = '-'; boards[selected][5] = '-'; boards[selected][6] = '-';
            boards[selected][7] = '-'; boards[selected][8] = '-'; boards[selected][9] = '-';
            
            System.out.print("\n>> Board " + selected + " is a tie.");
            return true;
        }
        else {
            return false;
        }
    }
    
    
    public void checkOverallBoard(char p) {
        if ( ( p == boards[0][1] && boards[0][1] == boards[0][2] && boards[0][2] == boards[0][3] ) ||
             ( p == boards[0][4] && boards[0][4] == boards[0][5] && boards[0][5] == boards[0][6] ) ||
             ( p == boards[0][7] && boards[0][7] == boards[0][8] && boards[0][8] == boards[0][9] ) ||
             ( p == boards[0][1] && boards[0][1] == boards[0][4] && boards[0][4] == boards[0][7] ) ||
             ( p == boards[0][2] && boards[0][2] == boards[0][5] && boards[0][5] == boards[0][8] ) ||
             ( p == boards[0][3] && boards[0][3] == boards[0][6] && boards[0][6] == boards[0][9] ) ||
             ( p == boards[0][1] && boards[0][1] == boards[0][5] && boards[0][5] == boards[0][9] ) ||
             ( p == boards[0][3] && boards[0][3] == boards[0][5] && boards[0][5] == boards[0][7] ) ) {
            gameOver = true;
            System.out.println("\n>> '" + p + "' WINS!!");
        }
        else if ( numMoves[0] == 9 ) {
            gameOver = true;
            System.out.println("\n>> DRAW GAME.");
        }
    }

    
    public void displayBoard() {
        System.out.println("\n");
        System.out.println("===========================================");
        System.out.println("| U L T I M A T E    T I C - T A C- T O E |");
        System.out.println("===========================================");
        
        System.out.println("  " + boards[1][1] + " | " + boards[1][2] + " | " + boards[1][3] + "  ||  "
                                + boards[2][1] + " | " + boards[2][2] + " | " + boards[2][3] + "  ||  "
                                + boards[3][1] + " | " + boards[3][2] + " | " + boards[3][3] + "   ");
        System.out.println(" ---+---+--- || ---+---+--- || ---+---+---");   
        System.out.println("  " + boards[1][4] + " | " + boards[1][5] + " | " + boards[1][6] + "  ||  " 
                                + boards[2][4] + " | " + boards[2][5] + " | " + boards[2][6] + "  ||  "
                                + boards[3][4] + " | " + boards[3][5] + " | " + boards[3][6] + "   ");
        System.out.println(" ---+---+--- || ---+---+--- || ---+---+---");   
        System.out.println("  " + boards[1][7] + " | " + boards[1][8] + " | " + boards[1][9] + "  ||  " 
                                + boards[2][7] + " | " + boards[2][8] + " | " + boards[2][9] + "  ||  "
                                + boards[3][7] + " | " + boards[3][8] + " | " + boards[3][9] + "   ");
        System.out.println("=============++=============++=============");
        System.out.println("  " + boards[4][1] + " | " + boards[4][2] + " | " + boards[4][3] + "  ||  " 
                                + boards[5][1] + " | " + boards[5][2] + " | " + boards[5][3] + "  ||  "
                                + boards[6][1] + " | " + boards[6][2] + " | " + boards[6][3] + "   ");
        System.out.println(" ---+---+--- || ---+---+--- || ---+---+---");   
        System.out.println("  " + boards[4][4] + " | " + boards[4][5] + " | " + boards[4][6] + "  ||  " 
                                + boards[5][4] + " | " + boards[5][5] + " | " + boards[5][6] + "  ||  "
                                + boards[6][4] + " | " + boards[6][5] + " | " + boards[6][6] + "   ");
        System.out.println(" ---+---+--- || ---+---+--- || ---+---+---");   
        System.out.println("  " + boards[4][7] + " | " + boards[4][8] + " | " + boards[4][9] + "  ||  " 
                                + boards[5][7] + " | " + boards[5][8] + " | " + boards[5][9] + "  ||  "
                                + boards[6][7] + " | " + boards[6][8] + " | " + boards[6][9] + "   ");
        System.out.println("=============++=============++=============");
        System.out.println("  " + boards[7][1] + " | " + boards[7][2] + " | " + boards[7][3] + "  ||  " 
                                + boards[8][1] + " | " + boards[8][2] + " | " + boards[8][3] + "  ||  "
                                + boards[9][1] + " | " + boards[9][2] + " | " + boards[9][3] + "   ");
        System.out.println(" ---+---+--- || ---+---+--- || ---+---+---");   
        System.out.println("  " + boards[7][4] + " | " + boards[7][5] + " | " + boards[7][6] + "  ||  " 
                                + boards[8][4] + " | " + boards[8][5] + " | " + boards[8][6] + "  ||  "
                                + boards[9][4] + " | " + boards[9][5] + " | " + boards[9][6] + "   ");
        System.out.println(" ---+---+--- || ---+---+--- || ---+---+---");   
        System.out.println("  " + boards[7][7] + " | " + boards[7][8] + " | " + boards[7][9] + "  ||  " 
                                + boards[8][7] + " | " + boards[8][8] + " | " + boards[8][9] + "  ||  "
                                + boards[9][7] + " | " + boards[9][8] + " | " + boards[9][9] + "   ");
        System.out.println();
    }

}
