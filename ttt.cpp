#include <iostream>
#include "Board.h"
using namespace std;

void intro();

int main(int argc, char **argv) {
    Board tictactoe;
    char player = 'O';
    bool gameOver = false;

    intro();
    tictactoe.displayBoard();
    while (!gameOver) {
        // New turn; change to player making next move
        if (player == 'O')      player = 'X';
        else if (player == 'X') player = 'O';

        // Select a board to play on if it's the first move of the game or if
        // the previous move directed to a complete board
        tictactoe.selectBoard(player);

        // Make a move
        tictactoe.makeMove(player);

        // Check move
        tictactoe.checkMove(player, &gameOver);

        // Display board
        tictactoe.displayBoard();
    }

    return 0;
}

void intro() {
    cout << "\nWelcome to ULTIMATE TIC-TAC-TOE!!\n"
         << "=================================\n";

    cout << "- See "
         << "http://mathwithbaddrawings.com/ultimate-tic-tac-toe-original-post/\n"
         << "  for instructions or more information about the game.\n";

    cout << "- Choose board/move using numbers from 1 to 9, where the position\n"
         << "  of the number is given by the following:\n\n";

    cout << " 1 | 2 | 3 \n"
         << "---+---+---\n"
         << " 4 | 5 | 6 \n"
         << "---+---+---\n"
         << " 7 | 8 | 9 \n\n";

    cout << "Let's begin!\n";
}
