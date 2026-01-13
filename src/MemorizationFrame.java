package src;

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

public class MemorizationFrame extends JPanel {
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
    public ProjectFileData projectData;
    public String title;
    public frameContainer frame;
    public JPanel scrollPanel;

    MemorizationFrame(frameContainer f, JPanel sp) {
        projectData = new ProjectFileData();
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        frame = f;
        scrollPanel = sp;
        MainFramegbc = new GridBagConstraints();
        MemorizationFrame memframe = this;
        MainFramegbc.weightx = 1.0;
        MainFramegbc.anchor = GridBagConstraints.CENTER;

        lineQuestion = new LineQuestion(this);
        MainFramegbc.gridx = 0;
        MainFramegbc.gridy = 0;

        // finishBtn
        finishBtn = new JButton("Finish");
        finishBtn.setForeground(Color.gray);
        finishBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("afsdfasdf");
                int linenum = 1;

                scrollPanel.setLayout(new GridLayout(lineQuestion.lineInputContainers.size(), 1));
                frame.navBar.reciteCircle.setColor(Color.DARK_GRAY);
                frame.navBar.reciteButton.setBackground(Color.DARK_GRAY);
                frame.navBar.editButton.setBackground(Color.black);
                frame.navBar.editCircle.setColor(Color.black);
                frame.navBar.homeButton.setBackground(Color.black);
                frame.navBar.homeCircle.setColor(Color.black);
                for (LineInput i : lineQuestion.lineInputContainers.values()) {

                    i.title.setVisible(false);
                    ;
                    i.subButton.setVisible(false);
                    ;
                    i.lineText.setVisible(false);
                    i.editButton.setVisible(false);
                    i.otherLine.setVisible(false);
                    if (lineQuestion.title != null) {
                        String line = i.title.getText();
                        projectData.addToLinesList(lineQuestion.title, linenum, line, lineQuestion.islineRecording,
                                i.otherLineSelected, lineQuestion.timePlay);
                    }
                    i.revalidate();
                    i.repaint();
                    i.setBorder(null);

                    MainFramegbc.gridy = linenum + 1;
                    MainFramegbc.gridx = 1;
                    linenum++;
                }
                MainFramegbc.gridy++;
                lineQuestion.scrollPane.setBackground(Color.black);
                add(lineQuestion.scrollPane, MainFramegbc);
                if (lineQuestion.title != null) {
                    projectData.outLines();
                }
                MainFramegbc.gridy++;
                if (lineQuestion.islineRecording) {
                    add(playButton, MainFramegbc);
                }
                MainFramegbc.gridy++;
                add(nextButton, MainFramegbc);
                MainFramegbc.gridy++;
                add(restartButton, MainFramegbc);
                MainFramegbc.gridy = -1;
                lineQuestion.scrollPanel.setLayout(new GridLayout(linenum, 1));
                lineQuestion.scrollPanel.revalidate();
                lineQuestion.scrollPanel.repaint();
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

                    LineInput currentLineInput = lineQuestion.lineInputContainers.get(currentLineName);
                    if (currentLineInput == null) {
                        MainFramegbc.gridy = 0;
                        congaRats = new JLabel("Congrats You Memorized the Whole Thing");
                        congaRats.setFont(new Font("Serif", Font.BOLD, 20));
                        congaRats.setForeground(Color.white);
                        add(congaRats, MainFramegbc);
                        nextButton.setVisible(false);
                    } else {
                        MainFramegbc.gridy = currentLine;
                        currentLineInput.title.setName(currentLineInput.title.getText());
                        currentLineInput.title.setVisible(true);
                        for (int i = 0; i < currentLineInput.getComponents().length; i++) {
                            System.out.println(currentLineInput.getComponents()[i].getName());
                        }
                        currentLineInput.revalidate();
                        currentLineInput.repaint();
                        if (currentLineInput.otherLineSelected == true) {
                            int nextCheckedLine = currentLine + 1;
                            String nextLineName = "input" + nextCheckedLine;

                            LineInput nextLineInput = lineQuestion.lineInputContainers.get(nextLineName);
                            System.out.println(nextLineInput);
                            if (nextLineInput != null) {
                                while (nextLineInput != null&&nextLineInput.otherLineSelected == true) {
                                    if (nextLineInput != null) {
                                        nextLineInput.title.setVisible(true);
                                        System.out.println(nextLineInput.title);
                                        nextCheckedLine++;
                                        nextLineName = "input" + nextCheckedLine;
                                        nextLineInput = lineQuestion.lineInputContainers.get(nextLineName);
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
                for (LineInput i : lineQuestion.lineInputContainers.values()) {
                    i.title.setVisible(false);
                    i.revalidate();
                    i.repaint();
                    i.setBorder(null);
                    MainFramegbc.gridy++;
                }
                MainFramegbc.gridy++;
                MainFramegbc.gridy++;
                nextButton.setVisible(true);
                System.out.println(MainFramegbc.gridx + " "+ MainFramegbc.weighty);

                currentLine = 0;
                try {
                    remove(congaRats);
                } catch (Exception e2) {
                }
                revalidate();
                repaint();

            }
        });

        // play button
        playButton = new JButton("Play Line");

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (title == null) {
                    title = lineQuestion.title;
                }
                String currentAudioFile = currentLine + title + "input.wav";
                audioFile = new File(currentAudioFile);

                playFile(audioFile, lineQuestion.timePlay);

            }
        });
        //if i broke smth its these lines
        MainFramegbc.gridx=0;
        MainFramegbc.anchor=GridBagConstraints.CENTER;
        MainFramegbc.gridy=0;
        add(lineQuestion, MainFramegbc);
        MainFramegbc.weightx = 1.0;

       // MainFramegbc.fill=GridBagConstraints.BOTH;
        setVisible(true);

    }

    public void playFile(File audioFile1, int playTime) {

        try {
            Clip lineAudio = AudioSystem.getClip();
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            lineAudio.open(audioStream);
            lineAudio.start();
            playTime *= 1000;
            TimerTask stopPlay = new TimerTask() {

                @Override
                public void run() {
                    lineAudio.stop();
                    lineAudio.close();
                }

            };
            Timer timer = new Timer();
            timer.schedule(stopPlay, playTime);
        } catch (Exception d) {
            System.out.println(d);
        }
        setBackground(Color.BLACK);
    }

    public Boolean checkFinish(LineInput lineinput) {
        if (lineinput.title.getText() != null) {
            return true;
        } else {
            return false;
        }
    }
}
