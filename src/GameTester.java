import javax.swing.*;

public class GameTester {
    public static void main(String[] args) {
        //LogicGame testGame = new LogicGame();

        JFrame testFrame = new JFrame();
        LogicPanel testPanel = new LogicPanel();


        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        testFrame.setSize(800, 800);
        testFrame.add(testPanel);
        testFrame.setVisible(true);

    }
}