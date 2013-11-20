#include <iostream>
#include "Board.h"
using namespace std;

Board::Board() {
    boards = new char*[10];
    numMoves = new int[10];
    for (int i = 0; i < 10; i++) {
        boards[i] = new char[10];
        for (int j = 0; j < 10; j++) {
            boards[i][j] = ' ';
        }
        numMoves[i] = 0;
    }

    newBoard = true;
}

bool Board::completedBoard(int num) {
    if (boards[0][num] == 'D' ||
        boards[0][num] == 'X' ||
        boards[0][num] == 'O') {
        return true;
    }
    else {
        return false;
    }
}

void Board::selectBoard(char player) {
    int num;
    while ( newBoard ) {
        cout << "Player " << player << "'s turn. "
             << "Enter board to play on (1-9): ";
        
        if ( !(cin >> num) ) {
            cout << "Invalid input.\n";
            cin.clear();
            cin.ignore(80, '\n');
        }
        else if ( num < 1 || num > 9 )
            cout << "Invalid board number.\n";
        else if ( completedBoard(num) )
            cout << "Board " << num << " is already completed.\n";
        else {
            newBoard = false;
            selected = num;
        }
    }
}

void Board::makeMove(char player) {
    int pos;
    bool validMove = false;

    while ( !validMove ) {
        cout << "Player " << player << "'s turn. "
             << "Playing in board " << selected << ", enter move/position (1-9): ";
        
        if ( !(cin >> pos) ) {
            cout << "Invalid input.\n";
            cin.clear();
            cin.ignore(80, '\n');
        }
        else if ( pos < 1 || pos > 9 ) 
            cout << "Invalid move/position number.\n";
        
        else if ( boards[selected][pos] == 'X' || boards[selected][pos] == 'O' )
            cout << "There is already an '" << boards[selected][pos]
                 << "' mark at that position.\n";
        else 
            validMove = true;
    }

    boards[selected][pos] = player;
    numMoves[selected]++;
    lastMove = pos;
    
    cout << "\n-------------------------------------------------------------\n";
    cout << "\n>> '" << player << "' is marked at " 
         << "board " << selected << ", position " << pos << ".";
}

void Board::checkMove(char player, bool *over) {
    if ( checkSmallBoard(player) ) {
        checkOverallBoard(player, over);
    }
    selected = lastMove;
    newBoard = completedBoard(selected);
}

bool Board::checkSmallBoard(char p) {
    char *b = boards[selected];
    if ( ( p == b[1] && b[1] == b[2] && b[2] == b[3] ) ||
         ( p == b[4] && b[4] == b[5] && b[5] == b[6] ) ||
         ( p == b[7] && b[7] == b[8] && b[8] == b[9] ) ||
         ( p == b[1] && b[1] == b[4] && b[4] == b[7] ) ||
         ( p == b[2] && b[2] == b[5] && b[5] == b[8] ) ||
         ( p == b[3] && b[3] == b[6] && b[6] == b[9] ) ||
         ( p == b[1] && b[1] == b[5] && b[5] == b[9] ) ||
         ( p == b[3] && b[3] == b[5] && b[5] == b[7] ) ) {
        boards[0][selected] = p;
        numMoves[0]++;

        // For display purposes
        if (p == 'X') {
            b[1] = 'X'; b[2] = ' '; b[3] = 'X'; 
            b[4] = ' '; b[5] = 'X'; b[6] = ' ';
            b[7] = 'X'; b[8] = ' '; b[9] = 'X';
        }
        else if (p == 'O') {
            b[1] = 'O'; b[2] = 'O'; b[3] = 'O';
            b[4] = 'O'; b[5] = ' '; b[6] = 'O'; 
            b[7] = 'O'; b[8] = 'O'; b[9] = 'O';
        }
        cout << "\n>> Player '" << p << "' wins board " << selected << ".";
        
        return true;
    }
    else if (numMoves[selected] == 9) {
        boards[0][selected] = 'D';
        numMoves[0]++;

        b[1] = '-'; b[2] = '-'; b[3] = '-';
        b[4] = '-'; b[5] = '-'; b[6] = '-';
        b[7] = '-'; b[8] = '-'; b[9] = '-';
        cout << "\n>> Board " << selected << " is a tie.";
        return true;
    }
    else {
        return false;
    }
}

void Board::checkOverallBoard(char p, bool *over) {
    char *b = boards[0];
    if ( ( p == b[1] && b[1] == b[2] && b[2] == b[3] ) ||
         ( p == b[4] && b[4] == b[5] && b[5] == b[6] ) ||
         ( p == b[7] && b[7] == b[8] && b[8] == b[9] ) ||
         ( p == b[1] && b[1] == b[4] && b[4] == b[7] ) ||
         ( p == b[2] && b[2] == b[5] && b[5] == b[8] ) ||
         ( p == b[3] && b[3] == b[6] && b[6] == b[9] ) ||
         ( p == b[1] && b[1] == b[5] && b[5] == b[9] ) ||
         ( p == b[3] && b[3] == b[5] && b[5] == b[7] ) ) {
        *over = true;

        cout << "\n>> '" << p << "' WINS!!\n";
    }
    else if ( numMoves[0] == 9 ) {
        *over = true;
        cout << "\n>> DRAW GAME.\n";
    }
}

void Board::displayBoard() {
    char **b = boards;
    
    cout << endl << endl
         << "===========================================\n"
         << "| U L T I M A T E    T I C - T A C- T O E |\n"
         << "===========================================\n";
    
    cout << "  " << b[1][1] << " | " << b[1][2] << " | " << b[1][3] << "  ||  "
                 << b[2][1] << " | " << b[2][2] << " | " << b[2][3] << "  ||  "
                 << b[3][1] << " | " << b[3][2] << " | " << b[3][3] << "   \n";   
    cout << " ---+---+--- || ---+---+--- || ---+---+---\n";   
    cout << "  " << b[1][4] << " | " << b[1][5] << " | " << b[1][6] << "  ||  " 
                 << b[2][4] << " | " << b[2][5] << " | " << b[2][6] << "  ||  "
                 << b[3][4] << " | " << b[3][5] << " | " << b[3][6] << "   \n";
    cout << " ---+---+--- || ---+---+--- || ---+---+---\n";   
    cout << "  " << b[1][7] << " | " << b[1][8] << " | " << b[1][9] << "  ||  " 
                 << b[2][7] << " | " << b[2][8] << " | " << b[2][9] << "  ||  "
                 << b[3][7] << " | " << b[3][8] << " | " << b[3][9] << "   \n";
    
    cout << "=============++=============++=============\n";
    
    cout << "  " << b[4][1] << " | " << b[4][2] << " | " << b[4][3] << "  ||  " 
                 << b[5][1] << " | " << b[5][2] << " | " << b[5][3] << "  ||  "
                 << b[6][1] << " | " << b[6][2] << " | " << b[6][3] << "   \n";
    cout << " ---+---+--- || ---+---+--- || ---+---+---\n";   
    cout << "  " << b[4][4] << " | " << b[4][5] << " | " << b[4][6] << "  ||  " 
                 << b[5][4] << " | " << b[5][5] << " | " << b[5][6] << "  ||  "
                 << b[6][4] << " | " << b[6][5] << " | " << b[6][6] << "   \n";
    cout << " ---+---+--- || ---+---+--- || ---+---+---\n";   
    cout << "  " << b[4][7] << " | " << b[4][8] << " | " << b[4][9] << "  ||  " 
                 << b[5][7] << " | " << b[5][8] << " | " << b[5][9] << "  ||  "
                 << b[6][7] << " | " << b[6][8] << " | " << b[6][9] << "   \n";

    cout << "=============++=============++=============\n";

    cout << "  " << b[7][1] << " | " << b[7][2] << " | " << b[7][3] << "  ||  " 
                 << b[8][1] << " | " << b[8][2] << " | " << b[8][3] << "  ||  "
                 << b[9][1] << " | " << b[9][2] << " | " << b[9][3] << "   \n";
    cout << " ---+---+--- || ---+---+--- || ---+---+---\n";   
    cout << "  " << b[7][4] << " | " << b[7][5] << " | " << b[7][6] << "  ||  " 
                 << b[8][4] << " | " << b[8][5] << " | " << b[8][6] << "  ||  "
                 << b[9][4] << " | " << b[9][5] << " | " << b[9][6] << "   \n";
    cout << " ---+---+--- || ---+---+--- || ---+---+---\n";   
    cout << "  " << b[7][7] << " | " << b[7][8] << " | " << b[7][9] << "  ||  " 
                 << b[8][7] << " | " << b[8][8] << " | " << b[8][9] << "  ||  "
                 << b[9][7] << " | " << b[9][8] << " | " << b[9][9] << "   \n\n";
}
