import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.sound.sampled.Line;
import javax.swing.*;

public class MemorizationFrame extends JFrame {
    public JButton button;
    public JLabel title;
    public JTextField lineText;
    public GridBagConstraints MainFramegbc;
    public MemorizationFrame mainFrame;
    public LineQuestion lineQuestion;
    MemorizationFrame(){ 


        setTitle("App");
        setLayout(new GridBagLayout());
        setSize(700, 350);
        this.getContentPane().setBackground(Color.BLACK);
        MainFramegbc = new GridBagConstraints();


        lineQuestion = new LineQuestion(this);
        MainFramegbc.gridx=0;
        MainFramegbc.gridy=0;
        add(lineQuestion);
        setVisible(true);

    }
    public void main(String args[]){  
        mainFrame = new MemorizationFrame();  
        }
    
    }

