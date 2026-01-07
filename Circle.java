import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.JPanel;
 class Circle extends JPanel {
    int x, y, width, height;
    Color color;
    boolean fillColor;

    Circle(int xP, int yP, int w, int h, Color colorFill, boolean fill) {
        x = xP;
        y = yP;
        width = w;
        height = h;
        color = colorFill;
        fillColor = fill;
        setPreferredSize(new Dimension(width + x, height + y));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        if (fillColor)
            g.fillOval(0, 0, width, height);
        else
            g.drawOval(0, 0, width, height);
    }
    public void circleBind(){
        this.setBounds(x, y, width, height);
    }
}
