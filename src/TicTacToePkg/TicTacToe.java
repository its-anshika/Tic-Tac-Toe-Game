package TicTacToePkg;

import TicTacToePkg.entities.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    Deque<Player> players;
    Board board;

    public TicTacToe() {
        players = new LinkedList<>();
        board = new Board(3);
    }

    public String playGame() {
        PieceX cross = new PieceX();
        Player player1 = new Player("player1", cross);
        PieceO round = new PieceO();
        Player player2 = new Player("player2", round);
        players.add(player1);
        players.add(player2);

        boolean noWin = true;
        while (noWin){
            board.printBoard();
            Player currentPlayer = players.removeFirst();
            List<Pair<Integer,Integer>> freeCells = board.getFreeCells();
            if(freeCells.isEmpty()){
                noWin = false;
                continue;
            }

            //read the user input
            System.out.print("Player:" + currentPlayer.name + " Enter row,column: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int i = Integer.valueOf(values[0]);
            int j = Integer.valueOf(values[1]);

            boolean placed = board.addPiece(currentPlayer.piece,i,j);
            if(!placed){
                System.out.println("Incorrect position!");
                players.addFirst(currentPlayer);
                continue;
            }
            players.addLast(currentPlayer);//Round Robin for chance

            //whoWon
            boolean winner = isThereWinner(i, j, currentPlayer.piece.pieceType);
            if(winner) {
                return currentPlayer.name;
            }

        }
        return "tie";//default
    }

    public boolean isThereWinner(int row, int column, PieceType pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;
//need to check in row
        for(int i=0;i<board.size;i++) {

            if(board.board[row][i] == null || board.board[row][i].pieceType != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i=0;i<board.size;i++) {

            if(board.board[i][column] == null || board.board[i][column].pieceType != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i<board.size;i++,j++) {
            if (board.board[i][j] == null || board.board[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=board.size-1; i<board.size;i++,j--) {
            if (board.board[i][j] == null || board.board[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;


    }

    }
