import java.util.ArrayList;
import java.util.Stack;

public class Board {
    private ArrayList<Cell[][]> gameBoard;
    private Stack<ArrayList<Cell[][]>> undoStack;

    public Board(ArrayList<Cell[][]> gameBoard){
        this.gameBoard = gameBoard;
        undoStack = new Stack<>();
    }

    public Board(){
        this(new ArrayList<Cell[][]>());
    }

    public ArrayList<Cell[][]> getGameBoard() {
        return gameBoard;
    }

    //Sets the cell at the given coordinates to be equal to the given cell.
    public void setCell(Cell cell, int square, int x, int y){
        Cell[][] temp;
        temp = gameBoard.get(square);
        temp[x][y] = cell;
        gameBoard.set(square, temp);
    }

    //Cycles the cell at the given location
    public boolean incrementCell(int x, int y, int square){
        Cell[][] temp;
        temp = gameBoard.get(square);
        for(int i = 0; i < temp.length; i++){
            if((i != x && temp[i][y].getMark() == 2) || (i != y && temp[x][i].getMark() == 2)){
                return false;
            }
        }
        temp[x][y].increment();
        //if the cell was set to O cycles all squares in row and column to X
        if(temp[x][y].getMark() == 2){
            for(int i = 0; i < temp.length; i++){
                if(i != y && temp[x][i].getMark() != 1){
                    temp[x][i].increment();
                    temp[x][i].setAutofilled(true);
                }
                if(i != x && temp[i][y].getMark() != 1){
                    temp[i][y].increment();
                    temp[i][y].setAutofilled(true);
                }
            }
        }
        else if(temp[x][y].getMark() == 0){
            for(int i = 0; i < temp.length; i++){
                if(i != y && temp[x][i].isAutofilled()){
                    temp[x][i].setMark(0);
                }
                if(i != x && temp[i][y].isAutofilled()){
                    temp[i][y].setMark(0);
                }
            }
        }
        gameBoard.set(square, temp);
        updateStack();
        return true;
    }

    public Cell getCell(int square, int x, int y){
        Cell[][] temp = gameBoard.get(square);
        return temp[x][y];
    }

    public void undoStep(){
        undoStack.pop();
    }

    //Checks if the board has been solved.
    public boolean checkBoardSolved(){
        boolean solved = true;
        for(Cell[][] i : gameBoard){
            for(int j = 0; j < i.length; j++){
                for(int k = 0; k < i.length; k++){
                    if(!i[j][k].isCorrect()){
                        solved = false;
                    }
                }
            }
        }
        updateStack();
        return solved;
    }

    public void clearErrors(){
        for(Cell[][] i : gameBoard){
            for(int j = 0; j < i.length; j++){
                for(int k = 0; k < i.length; k++){
                    i[j][k].isCorrect();
                }
            }
        }
        updateStack();
    }

    //public


    public void updateStack(){
        undoStack.push(this.gameBoard);
    }
}
