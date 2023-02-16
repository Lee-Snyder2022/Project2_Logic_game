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
        do{
            int type = rand.nextInt(4);
            clues.add(new Clue(type, people));
            switch (type){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("error");
            }
        }while (false);
    }
    public boolean checkSolution(){
        return true;
    }
}
