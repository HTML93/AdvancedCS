
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.JPanel;
 class square extends JPanel {
    int x, y, width, height;
    Color color;
    boolean fillColor;
    Graphics graphics;
    square(int xP, int yP, int w, int h, Color colorFill, boolean fill) {
        x = xP;
        y = yP;
        width = w;
        height = h;
        color = colorFill;
        fillColor = fill;
        setPreferredSize(new Dimension(width + x, height + y));
        setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        graphics = g;
        g.setColor(color);
        if (fillColor)
            g.fillRect(0, 0, width, height);
        else
            g.drawRect(0, 0, width, height);
    }
    public void circleBind(){
        this.setBounds(x, y, width, height);
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color c){
        color = c;
        repaint();
    }
}

