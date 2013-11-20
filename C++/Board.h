#ifndef BOARD_H
#define BOARD_H

class Board {

private:
    char **boards;      // The boards that make up the overall board; boards[0]
                        // represents the overall board
    int *numMoves;      // The number of moves made on each small board
    bool newBoard;      // Whether a new board should be selected for next turn
    int selected;       // The selected board
    int lastMove;       // The position of the last move on a small board

public:
    Board();
    bool completedBoard(int num);
    void selectBoard(char player);
    void makeMove(char player);
    void checkMove(char player, bool *over);
    bool checkSmallBoard(char p);
    void checkOverallBoard(char p, bool *over);
    void displayBoard();

};

#endif
