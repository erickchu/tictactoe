all: ttt

ttt: ttt.o Board.o
	g++ -Wall -g ttt.o Board.o -o ttt

ttt.o: ttt.cpp Board.h
	g++ -Wall -g -c ttt.cpp -o ttt.o

Board.o: Board.cpp Board.h
	g++ -Wall -g -c Board.cpp -o Board.o

clean: 
	rm -f *.o
