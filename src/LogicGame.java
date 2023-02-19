import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class LogicGame {
    private int gameTime;
    private JFrame gameFrame;
    private LogicPanel gamePanel;
    private JPanel cluePanel;
    private ArrayList<Clue> clueList;
    private JPanel storyPanel;
    public LogicGame(){

    }
    public void startGame(int people, int values){
        int gridCount = (values*(values+1))/2;
        ArrayList<Cell[][]> grids = new ArrayList<>();
        for(int i = 0; i <gridCount; i++){
            grids.add(new Cell[people][people]);
        }
        ArrayList<Clue> clues = new ArrayList<>();
        Random rand = new Random();
        for (Cell[][]g:grids) {
            for (Cell[] cs:g) {
                for (Cell c:cs) {
                    c = new Cell();
                }
            }
        }
        do{
            int type = rand.nextInt(4);
            int randGrid = rand.nextInt(gridCount);
            Cell[][] grid = grids.get(randGrid);
            Clue candidate = new Clue(type, people);
            int X1 = rand.nextInt(people);
            int X2 = rand.nextInt(people);
            int Y = rand.nextInt(people);
            candidate.setElements(new int[]{Y, X1, X2});
            boolean valid = false;
            switch (type){
                case 0: //Given answer
                    valid = true;
                    candidate.setElements(new int[]{X1, Y});
                    for (int i = 0; i < people; i++) {
                        if(i != X1){
                            if(grid[i][Y].getCorrect() == 2){ //2 = true 1 = false, 0 = undecided
                                valid = false;
                            }
                        }
                        if (i != Y) {
                            if(grid[X1][i].getCorrect() == 2){
                                valid = false;
                            }
                        }
                    }
                    if(valid){
                        if(grid[X1][Y].getCorrect() == 1){
                            valid = false;
                        }else{
                            grid[X1][Y].setCorrect(2);
                            for (int i = 0; i < people; i++) {
                                if(i != X1){
                                    grid[i][Y].setCorrect(1);
                                }
                                if (i != Y) {
                                    grid[X1][i].setCorrect(1);
                                }
                            }
                        }
                    }
                    break;
                case 1: //Given non-answer
                    for(int i = 0; i < people; i++){
                        if(i != X1){
                            if(grid[i][Y].getCorrect() != 1){
                                valid = true;
                            }
                        }
                        if(i != Y){
                            if(grid[X1][i].getCorrect() != 1){
                                valid = true;
                            }
                        }
                    }
                    if(valid){
                        grid[X1][Y].setCorrect(1);
                    }
                    break;
                case 2: //Given non-answerS
                    for(int i = 0; i < people; i++){
                        if(i != X1 && i != X2){
                            if(grid[i][Y].getCorrect() != 1){
                                valid = true;
                            }
                        }
                        if(i != Y){
                            if(grid[X1][i].getCorrect() != 1 || grid[X2][i].getCorrect() != 1){
                                valid = true;
                            }
                        }
                    }
                    if(valid){
                        grid[X1][Y].setCorrect(1);
                        grid[X2][Y].setCorrect(1);
                    }
                    break;
                case 3: //Given answers (one true rest false)
                    if(grid[X1][Y].getCorrect() != 1 || grid[X2][Y].getCorrect() != 1 ){
                        valid = true;
                    }
                    if(valid) {
                        for (int i = 0; i < people; i++) {
                            if (i != X1 & i != X2) {
                                grid[i][Y].setCorrect(1);
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("error");
            }
            if(valid){
                clues.add(candidate);
            }
            for(Cell[][] g:grids){
                int slot = 0;
                int last;
                for (int i = 0; i < people; i++) {
                    for (int j = 0; j < people; j++) {
                        if(g[i][j].getCorrect() == 0){
                            slot += 1;
                        }
                    }
                }
            }
        }while (false);
        game = new Board(grids);
    }
    public boolean checkSolution(){
        return true;
    }
}
