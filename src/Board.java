import java.util.Stack;

public class Board {
    private Cell[][][] gameBoard;
    private Stack<Cell[][][]> undoStack;

    public Board(Cell[][][] gameBoard){
        this.gameBoard = gameBoard;
    }

    public Cell[][][] getGameBoard() {
        return gameBoard;
    }

    public void setCell(){

    }

    public void incrementCell(int x, int y, int z){

    }

    /*public Cell getCell(){

    }*/

    public void undoStep(){

    }

    /*public boolean checkBoardSolved(){

    }*/

    public void updateStack(){
        undoStack.push(this.gameBoard);
    }
}
