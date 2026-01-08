import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class navBar extends JPanel {
    Circle containerCircle;
    JLayeredPane navContainer;
    JButton homeButton;
    JButton editButton;
    JButton reciteButton;
    Circle homeCircle;
    Circle editCircle;
    Circle reciteCircle;
    navBar() {
        navContainer = new JLayeredPane();
        navContainer.setPreferredSize(new Dimension(150, 75));

        containerCircle = new Circle(0, 0, 150, 75, Color.pink, false);
        containerCircle.circleBind();
        navContainer.add(containerCircle, JLayeredPane.DEFAULT_LAYER);

        homeCircle = new Circle(90, 20, 25, 25, Color.blue, true);
        editCircle = new Circle(60, 20, 25, 25, Color.green, true);
        reciteCircle = new Circle(30, 20, 25, 25, Color.black, true);
        reciteCircle.circleBind();
        editCircle.circleBind();
        homeCircle.circleBind();
        navContainer.add(homeCircle, JLayeredPane.PALETTE_LAYER);
        navContainer.add(editCircle, JLayeredPane.PALETTE_LAYER);
        navContainer.add(reciteCircle, JLayeredPane.PALETTE_LAYER);

        //homeButton.setIcon(null);
      //  homeButton.setBounds(90, 20, 20, 20);
        navContainer.add(homeButton, JLayeredPane.MODAL_LAYER);
        add(navContainer);

        for (int i = 0; i < navContainer.getComponents().length; i++) {
            System.out.println(navContainer.getComponents()[i].getLocation());
        }
        
    }
}