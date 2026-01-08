import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
        navContainer.setPreferredSize(new Dimension(250, 75));
        
        containerCircle = new Circle(0, 12, 250, 40, Color.lightGray, true);
        containerCircle.circleBind();
        navContainer.add(containerCircle, JLayeredPane.DEFAULT_LAYER);

        reciteCircle = new Circle(140, 20, 25, 25, Color.black, true);
        editCircle = new Circle(110, 20, 25, 25, Color.black, true);
        homeCircle = new Circle(80, 20, 25, 25, Color.black, true);
        reciteCircle.setBackground(Color.lightGray);
        homeCircle.setBackground(Color.lightGray);
        editCircle.setBackground(Color.lightGray);
        reciteCircle.circleBind();
        editCircle.circleBind();
        homeCircle.circleBind();
        navContainer.add(homeCircle, JLayeredPane.PALETTE_LAYER);
        navContainer.add(editCircle, JLayeredPane.PALETTE_LAYER);
        navContainer.add(reciteCircle, JLayeredPane.PALETTE_LAYER);

        //ImageIcon homeicon = new ImageIcon("2137554-200.png");
        homeButton = new JButton("🏠");
        homeButton.setOpaque(true);
        homeButton.setBorder(BorderFactory.createEmptyBorder());
        homeButton.setForeground(Color.white);
        homeButton.setBackground(homeCircle.getColor());
        homeButton.setBounds(85, 25, 15, 15);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    System.out.println("home");
                }
            }});
        System.out.println(homeButton.getText());
        navContainer.add(homeButton, JLayeredPane.MODAL_LAYER);

        editButton = new JButton("🖊️");
        editButton.setOpaque(true);
        editButton.setBorder(BorderFactory.createEmptyBorder());
        editButton.setForeground(Color.white);
        editButton.setBackground(editCircle.getColor());
        editButton.setBounds(115, 25, 15, 15);
        System.out.println(editButton.getText());
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    System.out.println("edit");
                }
            }});
        navContainer.add(editButton, JLayeredPane.MODAL_LAYER);
        
        reciteButton = new JButton("🎤");
        reciteButton.setOpaque(true);
        reciteButton.setBorder(BorderFactory.createEmptyBorder());
        reciteButton.setForeground(Color.white);
        reciteButton.setBackground(reciteCircle.getColor());
        reciteButton.setBounds(145, 25, 15, 15);
        System.out.println(reciteButton.getText());
        reciteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    System.out.println("recite");
                }
            }});
        navContainer.add(reciteButton, JLayeredPane.MODAL_LAYER);

        add(navContainer);
        for (int i = 0; i < navContainer.getComponents().length; i++) {
            System.out.println(navContainer.getComponents()[i].getLocation());
        }

    }
}