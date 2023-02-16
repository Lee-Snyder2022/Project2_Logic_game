import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LogicPanel extends JPanel implements MouseListener {

    private JPanel entryPanel;
    private JPanel bottomPanel;

    int categories;
    int people;

    private GameSquare[][][] gameGrid;
    private JButton clearErrorButton;
    private JButton undoButton;
    private JButton hintButton;
    private JButton submitButton;

    public void setPeople(int people) {
        this.people = people;
    }

    public void setCategories(int categories) {
        this.categories = categories;
    }

    public LogicPanel(){
        this(3,4);
    }

    public LogicPanel(int c, int p){
        categories = c;
        people = p;

        setLayout(new GridBagLayout());

        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.insets = new Insets(0,0,0,0);

        entryPanel = new JPanel(new GridBagLayout());
        bottomPanel = new JPanel();

        add(entryPanel);

        gameGrid = new GameSquare[people][people][categories];
        for (int i = 0; i < people; i++) {
            for (int j = 0; j < people; j++) {
                for (int k = 0; k < categories; k++) {
                    gameGrid[i][j][k] = new GameSquare();
                    layoutConstraints.gridx = i + determineXOffset(k, people) * 50;
                    layoutConstraints.gridy = j + determineYOffset(k, people) * 50;
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
    }

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseClicked(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e) {}

    private int determineXOffset(int k, int people) {
        while (k >= people) {
            k = k % people;
            people--;
        }
        return k;
    }

    private int determineYOffset(int k, int people) {
        while (k >= people) {
            while (k >= people)
                k = k - people;
            people--;
        }
        return k;
    }
}
