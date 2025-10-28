import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.sound.sampled.Line;
import javax.swing.*;

public class MemorizationFrame extends JFrame {
    public JButton finishBtn;
    public GridBagConstraints MainFramegbc;
    public MemorizationFrame mainFrame;
    public LineQuestion lineQuestion;
    public JButton nextButton;
    public int currentLine=0;
    public JButton restartButton;
    public JLabel congaRats;
    MemorizationFrame(){ 

        setTitle("App");
        setLayout(new GridBagLayout());
        setSize(700, 500);
        this.getContentPane().setBackground(Color.BLACK);
        MainFramegbc = new GridBagConstraints();


        lineQuestion = new LineQuestion(this);
        MainFramegbc.gridx=0;
        MainFramegbc.gridy=0;

        //finishBtn
        finishBtn = new JButton("Finish");
        finishBtn.setForeground(Color.gray);
        finishBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linenum=1;
                for (LineInput i : lineQuestion.lineInputContainters.values()) {
                    i.remove(i.title);
                    i.remove(i.subButton);
                    i.remove(i.lineText);
                    i.remove(i.editButton);
                    i.remove(i.otherLine);
                    i.revalidate();
                    i.repaint();
                    i.setBorder(null);
                    MainFramegbc.gridy=linenum;
                    MainFramegbc.gridx=1;
                    linenum++;
                }
                MainFramegbc.gridy++;
                add(nextButton, MainFramegbc);
                MainFramegbc.gridy++;
                add(restartButton, MainFramegbc);
                MainFramegbc.gridy=-1;
                remove(finishBtn);
                revalidate();
                repaint();

            }
        } 
        );

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               { 
                String currentLineName = "input"+currentLine;
                LineInput currentLineInput = lineQuestion.lineInputContainters.get(currentLineName);
                try{currentLineInput.add(currentLineInput.title);}
                catch (Exception d){
                String lastInput = "input"+lineQuestion.lineInputContainters.size();
                if (currentLineName.equals(lastInput)){
                    MainFramegbc.gridy=-1;
                    congaRats = new JLabel("CONGA RATS YOU FINISHED");
                    congaRats.setFont(new Font("Serif", Font.BOLD, 999999));
                    congaRats.setForeground(Color.gray);
                    add(congaRats, MainFramegbc);
                    remove(nextButton);
                    
                }}
                currentLine++;
                revalidate();
                repaint();
            }
        }
            
        });

        //restart button
        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFramegbc.gridy=0;
                for (LineInput i : lineQuestion.lineInputContainters.values()) {
                    i.remove(i.title);
                    i.revalidate();
                    i.repaint();
                    i.setBorder(null);
                    MainFramegbc.gridy++;
                }
                add(nextButton, MainFramegbc);
                currentLine=0;
                remove(finishBtn);
                remove(congaRats);
                revalidate();
                repaint();

            }
        }
        );



        add(lineQuestion);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

    }
    public void main(String args[]){  
        mainFrame = new MemorizationFrame();  
        }
    
    }

