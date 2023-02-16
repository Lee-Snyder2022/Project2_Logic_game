import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LogicPanel extends JPanel implements MouseListener {

    private JPanel entryPanel;
    private JPanel bottomPanel;

    int grids;
    int people;

    private GameSquare[][][] gameGrid;
    private JButton clearErrorButton;
    private JButton undoButton;
    private JButton hintButton;
    private JButton submitButton;

    public void setPeople(int people) {
        this.people = people;
    }

    public void setGrids(int grids) {
        this.grids = grids;
    }

    public LogicPanel(){
        this(3,4);
    }

    public LogicPanel(int g, int p){
        grids = g;
        people = p;

        setLayout(new GridBagLayout());

        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.insets = new Insets(0,0,0,0);

        entryPanel = new JPanel(new GridBagLayout());
        bottomPanel = new JPanel();

        add(entryPanel);

        gameGrid = new GameSquare[people][people][grids];
        for (int i = 0; i < people; i++) {
            for (int j = 0; j < people; j++) {
                for (int k = 0; k < grids; k++) {
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
    }

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseClicked(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e) {}

    private int determineXOffset(int k, int g) {
        g--;
        while (k >= g) {
            k = k - g;
            g--;
        }
        return k;
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
}
