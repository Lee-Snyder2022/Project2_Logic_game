import javax.swing.*;
import java.util.ArrayList;

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
        ArrayList<Integer[][]> grids = new ArrayList<>();
        for(int i = 0; i <gridCount; i++){
            grids.add(new Integer[people][people]);
        }

    }
    public boolean checkSolution(){
        return true;
    }
}
