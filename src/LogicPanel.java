import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LogicPanel extends JPanel implements ActionListener, MouseListener {

    private Board gameBoard;

    private int people;
    private int categories;

    private JPanel entryPanel;
    private JPanel bottomPanel;

    int grids;
    int people;

    private GameSquare[][][] gameGrid;
    private JButton clearErrorsButton;
    private JButton undoButton;
    private JButton hintButton;
    private JButton submitButton;
    private JButton clearAnswers;
    private JButton showAnswerButton;

    public void setPeople(int people) {
        this.people = people;
    }

    public void setGrids(int grids) {
        this.grids = grids;
    }

    public LogicPanel(){
        this(3,4, new Board());
    }

    public LogicPanel(int g, int p){
        grids = g;
        people = p;
        categories = c;
        gameBoard = g;

        setLayout(new GridBagLayout());

        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.insets = new Insets(0,0,0,0);

        entryPanel = new JPanel(new GridBagLayout());
        bottomPanel = new JPanel();

        add(entryPanel);

        gameGrid = new GameSquare[people][people][calcGrids(categories)];
        for (int i = 0; i < people; i++) {
            for (int j = 0; j < people; j++) {
                for (int k = 0; k < calcGrids(categories); k++) {
                    gameGrid[i][j][k] = new GameSquare();
                    layoutConstraints.gridx = i + determineXOffset(k, grids) * people;
                    layoutConstraints.gridy = j + determineYOffset(k, grids) * people;
                    entryPanel.add(gameGrid[i][j][k], layoutConstraints);
                    gameGrid[i][j][k].addMouseListener(this);
                }
            }
        }

        //add(bottomPanel, )


    }

    @Override
    public void mousePressed(MouseEvent e){
        if (e.getSource() instanceof GameSquare)
        {
            GameSquare sourceSquare = (GameSquare) e.getSource();
            sourceSquare.incrementState();
            repaint();
        }
        updateGameSquares(gameBoard.getGameBoard());
    }

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseClicked(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == clearErrorsButton) {
            gameBoard.clearErrors();
        } else if (e.getSource() == undoButton) {
            gameBoard.undoStep();
            ArrayList<Cell[][]> updatedCells = gameBoard.getGameBoard();
            updateGameSquares(updatedCells);

        } else if (e.getSource() == hintButton) {
            // gameBoard.hint();
        } else if (e.getSource() == submitButton) {
            if(gameBoard.checkBoardSolved()){
                for (int i = 0; i < people; i++) {
                    for (int j = 0; j < people; j++) {
                        for (int k = 0; k < calcGrids(categories); k++) {
                            gameGrid[i][j][k].removeMouseListener(this);
                            clearErrorsButton.removeActionListener(this);
                            undoButton.removeActionListener(this);
                            hintButton.removeActionListener(this);
                            submitButton.removeActionListener(this);
                            JOptionPane.showMessageDialog(this, "You Win!\nYour time was: \nSelect New Game to play again.");

                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "You're not done yet!");
            }
        } else if (e.getSource() == clearAnswers) {
            for (int i = 0; i < people; i++) {
                for (int j = 0; j < people; j++) {
                    for (int k = 0; k < calcGrids(categories); k++) {
                        gameGrid[i][j][k].setState(0);
                    }
                }
            }
            repaint();
        } else if (e.getSource() == showAnswerButton) {
            for (int i = 0; i < people; i++) {
                for (int j = 0; j < people; j++) {
                    for (int k = 0; k < calcGrids(categories); k++) {
                        gameGrid[i][j][k].setState(gameBoard.getCell(k, i, j).getCorrect());
                    }
                }
            }
            repaint();
        }
    }


    private int calcGrids(int categories) {
        return (categories * (categories - 1)) / 2;
    }

    private int determineXOffset(int k, int g) {
        g--;
        while (k >= g) {
            k = k - g;
            g--;
        }
        return index;
    }

    private int determineYOffset(int k, int g) {
        int loop = 0;
        g--;
        while (k >= g) {
            k = k - g;
            g--;
            loop++;
        }
        return loop;
    }

    private void updateGameSquares(ArrayList<Cell[][]> updatedCells){
        for (int k = 0; k < calcGrids(categories); k++) {
            for (int i = 0; i <  people; i++){
                for (int j = 0; j < people; j++) {
                    gameGrid[i][j][k].setState(updatedCells.get(k)[i][j].getMark());
                }
            }
        }
        repaint();
    }
}
