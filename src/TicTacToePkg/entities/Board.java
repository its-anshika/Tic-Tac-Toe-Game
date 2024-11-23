package TicTacToePkg.entities;

import TicTacToePkg.entities.PlayingPiece;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.Pair;

public class Board {

    public int size;
    public PlayingPiece[][] board;

    public Board(int size) {
        this.size = size;
        board = new PlayingPiece[size][size];
    }
    public boolean addPiece(PlayingPiece piece,int i, int j) {
        if(board[i][j] != null)
            return false;
        board[i][j] = piece;
        return true;
    }

    public List<Pair<Integer, Integer>> getFreeCells(){
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    Pair<Integer, Integer> rowColumn = new Pair<>(i, j);
                    freeCells.add(rowColumn);
                }
            }
        }
        return freeCells;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(board[i][j] != null) {
                    System.out.print(board[i][j].pieceType.name() + " ");
                }
                else
                    System.out.print(" ");
                System.out.print("|");
            }
            System.out.println(); //newline
        }
    }

    
}
