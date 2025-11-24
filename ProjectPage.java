import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;
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


public class ProjectPage extends JFrame {
    public ProjectPage mainProjectPage;
    public GridBagConstraints ProjectPageGBC;
    ProjectPage(){
        setTitle("Project Selection");
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.getContentPane().setBackground(Color.BLACK);

        ProjectPageGBC = new GridBagConstraints();

        


    }
}
