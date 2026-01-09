import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

public class LineInput extends JPanel {
    public JLabel title;
    public JTextField lineText;
    public JPanel inputContatiner;
    public JButton subButton;
    public JCheckBox otherLine;
    public JButton editButton;
    public boolean otherLineSelected;
    public RecordTest lineRecorder;
    public String filePathName;
    public Path path;
    public GridBagConstraints gbc;

    LineInput(int lineNumber, boolean islineRecording, boolean hasOtherLines, String projectName) {
        filePathName = lineNumber + projectName + "input.wav";
        path = Paths.get(filePathName);

        inputContatiner = new JPanel();
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        otherLine = new JCheckBox("Other Person's Line?", false);
        otherLine.setForeground(Color.GRAY);
        gbc.gridx = 1;
        gbc.gridy = 4;
        if (hasOtherLines == true) {
            add(otherLine, gbc);
        }
        // title
        title = new JLabel("Line Number " + (lineNumber + 1));
        title.setForeground(Color.GRAY);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(title, gbc);
        // edit button
        editButton = new JButton("Edit");
        editButton.setForeground(Color.gray);

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(editButton);
                gbc.gridx = 1;
                gbc.gridy = 2;
                add(lineText, gbc);

                if (islineRecording) {
                    gbc.gridy = 3;
                    add(lineRecorder, gbc);
                }
                gbc.gridy = 4;
                add(otherLine, gbc);

                gbc.gridy = 5;
                add(subButton, gbc);
                revalidate();
                repaint();
            }
        });

        // subButton
        subButton = new JButton("Submit");
        subButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titleSet = lineText.getText();
                if (titleSet.isEmpty() == false) {

                    title.setText(titleSet);
                    if (otherLine.isSelected()) {
                        title.setForeground(Color.blue);
                        otherLineSelected = true;
                    } else {
                        title.setForeground(Color.GRAY);
                        otherLineSelected = false;
                        if (islineRecording == true) {
                            if (Files.exists(path) == false) {
                                lineText.setText("Record the Line");
                                return;
                            }
                        }
                    }
                    if (lineRecorder.isRecording == true) {
                        lineText.setText("Finish Recording");
                        return;
                    }
                    remove(lineText);
                    remove(subButton);
                    remove(otherLine);
                    remove(lineRecorder);
                    gbc.gridx = 1;
                    gbc.gridy = 5;
                    add(editButton, gbc);
                } else {
                    lineText.setText("Enter a Line");
                }
                revalidate();
                repaint();
            }
        });
        subButton.setForeground(Color.gray);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(subButton, gbc);
        // input
        lineText = new JTextField();
        lineText.setColumns(10);
        lineText.setBackground(Color.GRAY);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(lineText, gbc);

        // line record
        lineRecorder = new RecordTest(filePathName);
        gbc.gridy = 3;
        if (islineRecording) {
            add(lineRecorder, gbc);
        }
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        this.setBackground(Color.BLACK);
        setVisible(true);
    }
}