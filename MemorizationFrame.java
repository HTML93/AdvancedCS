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

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class MemorizationFrame extends JFrame {
    public JButton finishBtn;
    public MemorizationFrame mainFrame;
    public GridBagConstraints MainFramegbc;
    public LineQuestion lineQuestion;
    public JButton nextButton;
    public int currentLine = 0;
    public JButton restartButton;
    public JLabel congaRats;
    public JButton playButton;
    public AudioInputStream audioStream;
    public File audioFile;

    MemorizationFrame() {

        setTitle("App");
        setLayout(new GridBagLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.getContentPane().setBackground(Color.BLACK);

        MainFramegbc = new GridBagConstraints();

        lineQuestion = new LineQuestion(this);
        MainFramegbc.gridx = 0;
        MainFramegbc.gridy = 0;

        // finishBtn
        finishBtn = new JButton("Finish");
        finishBtn.setForeground(Color.gray);
        finishBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linenum = 1;
                for (LineInput i : lineQuestion.lineInputContainters.values()) {
                    i.remove(i.title);
                    i.remove(i.subButton);
                    i.remove(i.lineText);
                    i.remove(i.editButton);
                    i.remove(i.otherLine);
                    i.revalidate();
                    i.repaint();
                    i.setBorder(null);
                    MainFramegbc.gridy = linenum + 1;
                    MainFramegbc.gridx = 1;
                    linenum++;
                }
                MainFramegbc.gridy++;
                add(playButton, MainFramegbc);
                MainFramegbc.gridy++;
                add(nextButton, MainFramegbc);
                MainFramegbc.gridy++;
                add(restartButton, MainFramegbc);
                MainFramegbc.gridy = -1;
                remove(finishBtn);
                revalidate();
                repaint();

            }
        });

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    String currentLineName = "input" + currentLine;
                    LineInput currentLineInput = lineQuestion.lineInputContainters.get(currentLineName);
                    if (currentLineInput == null) {
                        MainFramegbc.gridy = 0;
                        congaRats = new JLabel("CONGA RATS YOU FINISHED");
                        congaRats.setFont(new Font("Serif", Font.BOLD, 45));
                        congaRats.setForeground(Color.BLUE);
                        add(congaRats, MainFramegbc);
                        remove(nextButton);
                    } else {
                        MainFramegbc.gridy = currentLine;
                        currentLineInput.add(currentLineInput.title, MainFramegbc);

                        if (currentLineInput.otherLineSelected == true) {

                            int nextCheckedLine = currentLine + 1;
                            String nextLineName = "input" + nextCheckedLine;
                            LineInput nextLineInput = lineQuestion.lineInputContainters.get(nextLineName);
                            System.out.println(nextLineInput);
                            if (nextLineInput != null) {
                                while (nextLineInput.otherLineSelected == true) {
                                    if (nextLineInput != null) {
                                        System.out.println(nextLineName);
                                        nextLineInput.add(nextLineInput.title);
                                        nextCheckedLine++;
                                        nextLineName = "input" + nextCheckedLine;
                                        nextLineInput = lineQuestion.lineInputContainters.get(nextLineName);
                                        currentLine++;
                                    }
                                }
                            }
                        }
                    }
                }

                currentLine++;
                revalidate();
                repaint();
            }
        });

        // restart button
        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFramegbc.gridy = 1;
                for (LineInput i : lineQuestion.lineInputContainters.values()) {
                    i.remove(i.title);
                    i.revalidate();
                    i.repaint();
                    i.setBorder(null);
                    MainFramegbc.gridy++;
                }
                add(nextButton, MainFramegbc);
                currentLine = 0;
                remove(finishBtn);
                remove(congaRats);
                revalidate();
                repaint();

            }
        });

        // play button
        playButton = new JButton("Play Line");

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentAudioFile = currentLine + "input.wav";
                audioFile = new File(currentAudioFile);
                System.out.println("Audio File Title: " + currentAudioFile + " Audio File: " + audioFile
                        + " Current Line: " + currentLine);
                playFile(audioFile);

            }
        });

        add(lineQuestion);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);

    }

    public void playFile(File audioFile) {
        try {
            Clip lineAudio = AudioSystem.getClip();
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            lineAudio.open(audioStream);
            lineAudio.start();
        } catch (Exception d) {
            System.out.println(d);
        }
    }

    public void main(String args[]) {
    }

}
