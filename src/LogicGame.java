import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class LogicGame implements ActionListener {
    private int gameTime;
    private int people;
    private int categories;

    private ArrayList<Clue> clueList;

    private JFrame gameFrame;
    private JPanel mainPanel;
    private JPanel rightPanel;
    private JPanel cluePanel;
    private JPanel storyPanel;

    private JTextArea clueTextArea;
    private JTextArea storyTextArea;

    private JButton newGameButton;

    private Board game;

    public LogicGame() {
        this(3,4);
    }

    public LogicGame(int peop, int cat){
        people = peop;
        categories = cat;
        startGame(people, categories);

        gameFrame = new JFrame();
        mainPanel = new LogicPanel(people, categories, game);
        rightPanel = new JPanel(new GridBagLayout());
        cluePanel = new JPanel();
        storyPanel = new JPanel();

        clueTextArea = new JTextArea();
        storyTextArea = new JTextArea();

        gameFrame.setTitle("Logic Game!");

        clueTextArea.setColumns(30);
        clueTextArea.setRows(10);
        String clueString = "";
        for (Clue c: clueList){
            clueString += c + "\n\n";
        }
        clueTextArea.setText(clueString);
        clueTextArea.setEditable(false);
        storyTextArea.setColumns(30);
        storyTextArea.setRows(10);
        storyTextArea.setText("Story goes here\n\n\n\n\nOh man so much story\n\n\n\n\nMuch Story, So Great, Wau\n\n\n\n\nYes I'm testing the scroll functionality");
        storyTextArea.setEditable(false);

        gameFrame.add(mainPanel, BorderLayout.CENTER);
        gameFrame.add(rightPanel, BorderLayout.EAST);

        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        rightPanel.add(cluePanel, layoutConstraints);
        layoutConstraints.gridy = 1;
        rightPanel.add(storyPanel, layoutConstraints);
        JScrollPane clueScrollPane = new JScrollPane(clueTextArea);
        JScrollPane storyScrollPane = new JScrollPane(storyTextArea);

        cluePanel.add(clueScrollPane);
        storyPanel.add(storyScrollPane);

        newGameButton = new JButton();
        newGameButton.setText("New Game");
        gameFrame.add(newGameButton,BorderLayout.SOUTH);
        newGameButton.addActionListener(this);


        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.pack();
        gameFrame.setVisible(true);

    }

    public void startGame(int people, int categories){
        int gridCount = (categories*(categories-1))/2;

        ArrayList<Cell[][]> grids = new ArrayList<>();
        for(int i = 0; i <gridCount; i++){
            grids.add(new Cell[people][people]);
        }
        clueList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < people; i++) {
            for (int j = 0; j < people; j++) {
                for (int k = 0; k < gridCount; k++) {
                    grids.get(k)[i][j] = new Cell(0);
                }
            }
        }
        boolean complete;
        do{
            int type = rand.nextInt(2);
            int randGrid = rand.nextInt(gridCount);
            Cell[][] grid = grids.get(randGrid);
            Clue candidate = new Clue(type, people, randGrid);
            int X1 = rand.nextInt(people);
            int X2 = rand.nextInt(people);
            int Y = rand.nextInt(people);
            candidate.setElements(new int[]{Y, X1, X2});
            boolean valid = false;
            boolean validY = false;
            switch (type){
                case 0: //Given answer
                    valid = true;
                    candidate.setElements(new int[]{Y, X1});
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
                        if(grid[X1][Y].getCorrect() != 0){
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
                    validY = false;
                    candidate.setElements(new int[]{Y, X1});
                    for(int i = 0; i < people; i++){
                        if(i != X1){
                            if(grid[i][Y].getCorrect() != 1){
                                valid = true;
                            }
                        }
                        if(i != Y){
                            if(grid[X1][i].getCorrect() != 1){
                                validY = true;
                            }
                        }
                    }
                    if(!validY){
                        valid = false;
                    }
                    if(valid && grid[X1][Y].getCorrect() == 0){
                        grid[X1][Y].setCorrect(1);
                    }else{
                        valid = false;
                    }
                    break;
                case 2: //Given non-answerS
                    validY = false;
                    int openY = 0;
                    for(int i = 0; i < people; i++){
                        if(i != X1 && i != X2){
                            if(grid[i][Y].getCorrect() == 0){
                                valid = true;
                            }
                        }
                        if(i != Y){
                            if(grid[X1][i].getCorrect() == 0 || grid[X2][i].getCorrect() == 0){
                                openY += 1;
                                if(openY > 1){
                                    validY = true;
                                }
                            }
                        }
                    }
                    if(!validY){
                        valid = false;
                    }
                    if(valid){
                        if(grid[X1][Y].getCorrect() == 0 || grid[X2][Y].getCorrect() == 0){
                            grid[X1][Y].setCorrect(1);
                            grid[X2][Y].setCorrect(1);
                        }else{
                            valid = false;
                        }

                    }
                    break;
                case 3: //Given answers (one true rest false)
                    if(grid[X1][Y].getCorrect() == 0 || grid[X2][Y].getCorrect() == 0 ){
                        valid = true;
                    }
                    if(valid) {
                        valid = false;
                        for (int i = 0; i < people; i++) {
                            if (i != X1 & i != X2) {
                                if(grid[i][Y].getCorrect() == 0){
                                    valid = true;
                                    grid[i][Y].setCorrect(1);
                                }
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("error");
            }
            if(valid){
                clueList.add(candidate);
            }
                for (int k = 0; k < gridCount; k++) { //Check every horizontal for exactly 1 empty
                    int slot = 0;
                    int[] last = new int[3];
                    for (int i = 0; i < people; i++) {
                        for (int j = 0; j < people; j++) {
                            if (grids.get(k)[i][j].getCorrect() != 1) {
                                slot += 1;
                                last = new int[]{k, i, j};
                            } else if (grids.get(k)[i][j].getCorrect() == 2) {
                                slot += 2;
                            }
                        }
                        if (slot == 1) {
                            grids.get(last[0])[last[1]][last[2]].setCorrect(2);
                            for (int l = 0; l < people; l++) {
                                if (l != last[2]) {
                                    grids.get(last[0])[last[1]][l].setCorrect(1);
                                }
                            }
                        }
                        slot = 0;
                    }
                }
                for (int k = 0; k < gridCount; k++) { //Check the verticals
                    int slot = 0;
                    int[] last = new int[3];
                    for (int j = 0; j < people; j++) {
                        for (int i = 0; i < people; i++) {
                            if (grids.get(k)[i][j].getCorrect() != 1) {
                                slot += 1;
                                last = new int[]{k, i, j};
                            } else if (grids.get(k)[i][j].getCorrect() == 2) {
                                slot += 2;
                            }
                        }
                        if (slot == 1) {
                            grids.get(last[0])[last[1]][last[2]].setCorrect(2);
                            for (int l = 0; l < people; l++) {
                                if (l != last[1]) {
                                    grids.get(last[0])[l][last[2]].setCorrect(1);
                                }
                            }
                        }
                        slot = 0;
                    }
                }
            complete = true;
            for(Cell[][] g:grids){ //Check if board is complete
                for (int i = 0; i < people; i++) {
                    for (int j = 0; j < people; j++) {
                        if(g[i][j].getCorrect() == 0){
                            complete = false;
                        }
                    }
                }
            }
        }while (!complete);
        game = new Board(grids);
    }
    public boolean checkSolution(){
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) {
            startGame(people, categories);

            gameFrame.remove(mainPanel);
            mainPanel = new LogicPanel(people, categories, game);
            gameFrame.add(mainPanel, BorderLayout.CENTER);
            String clueString = "";
            for (Clue c : clueList) {
                clueString += c + "\n\n";
            }
            clueTextArea.setText(clueString);
            storyTextArea.setText("Story goes here\n\n\n\n\nOh man so much story\n\n\n\n\nMuch Story, So Great, Wau\n\n\n\n\nYes I'm testing the scroll functionality");
        }
    }
}
