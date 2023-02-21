import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class GameSquare extends JComponent {

    private int x_pos;
    private int y_pos;
    private int side;
    private Rectangle2D area;
    private Ellipse2D circle;
    private Color borderColor;
    private Color backgroundColor;
    private int state; // 0 = empty, 1 = X, 2 = O

    public GameSquare(){
        this(0,0,50);
    }


    public GameSquare(int x, int y, int s) {
        x_pos = x;
        y_pos = y;
        side = s;
        area = new Rectangle2D.Double(x_pos, y_pos, side, side);
        circle = new Ellipse2D.Double(x_pos, y_pos, side, side);
        backgroundColor = Color.WHITE;
        borderColor = Color.BLACK;
        state = 0;
    }

    public void setState(int s) {
        state = s;
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension((int)area.getWidth(), (int)area.getHeight());
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D graphicsObj = (Graphics2D) g;

        graphicsObj.setColor(backgroundColor);
        graphicsObj.fill(area);
        graphicsObj.setColor(borderColor);
        graphicsObj.draw(area);
        switch (state) {

            case 1:
                graphicsObj.drawLine(x_pos,y_pos,x_pos + side,y_pos + side);
                graphicsObj.drawLine(x_pos,y_pos + side, x_pos + side, y_pos);
                break;

            case 2:
                graphicsObj.draw(circle);
        }

    }
}
