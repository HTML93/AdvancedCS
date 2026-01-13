package src;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.TimerTask;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.Timer;

public class frameContainer extends JFrame{
    NavBar navBar;
    GridBagConstraints Framegbc;
    ProjectPage projPage;
    MemorizationFrame memPanel;
    String openedProject;
    JLabel title;
    MemorizationFrame openedFrame;
    frameContainer() {
        setTitle("App");
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.getContentPane().setBackground(Color.BLACK);
        title = new JLabel("Memorizo");
        title.setFont(new Font("Serif", Font.BOLD, 45));
        title.setForeground(Color.BLUE);
        title.setBackground(Color.BLACK);
        Framegbc = new GridBagConstraints();
        projPage = new ProjectPage(this);
        navBar = new NavBar(this);
        System.out.println(Framegbc.weighty);
        Framegbc.gridy=0;
        Framegbc.weighty=0;
        title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        add(title, Framegbc);
        Framegbc.weighty=1;
        Framegbc.gridy=1;
        add(projPage, Framegbc);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
    public static void main(String[] args) {
        new frameContainer();
    }
}
