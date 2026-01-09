import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
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
import java.util.Timer;

public class frameContainer extends JFrame{
    NavBar navBar;
    GridBagConstraints Framegbc;
    ProjectPage projPage;
    MemorizationFrame memPanel;
    frameContainer() {
        setTitle("App");
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.getContentPane().setBackground(Color.BLACK);

        Framegbc = new GridBagConstraints();
        projPage = new ProjectPage(this);
        navBar = new NavBar(this);
        Framegbc.gridy=2;
        add(navBar, Framegbc);
        Framegbc.gridy=1;
        add(projPage, Framegbc);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
    void main(){

    }
}
