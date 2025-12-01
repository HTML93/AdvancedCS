import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

public class LineQuestion extends JPanel {
    public int inputNum = 0;
    public JLabel question;
    public JTextField lineAmt;
    public JButton questionBtn;
    public JPanel questionContatiner;
    public HashMap<String, LineInput> lineInputContainers = new HashMap<String, LineInput>();
    public boolean islineRecording = false;
    public JCheckBox lineRecording;
    public JCheckBox isOtherLines;
    public boolean hasOtherLines = false;
    public int timePlay;
    public JTextField timePlayInput;
    public JTextField titleName;
    public JLabel titleLabel;
    public String Title;
    LineQuestion(MemorizationFrame mainFrame) {
        questionContatiner = new JPanel();
        this.setLayout(new GridBagLayout());
        GridBagConstraints lineInputGBC = new GridBagConstraints();
        question = new JLabel("How Many Lines");
        question.setForeground(Color.GRAY);

        lineAmt = new JTextField();
        lineAmt.setColumns(15);

        questionBtn = new JButton("Submit");
        questionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String lineNum = lineAmt.getText();
                String playTime = timePlayInput.getText();
                if (checkInt(playTime)) {
                    timePlay = Integer.parseInt(playTime);
                    if (timePlay < 0) {
                        timePlayInput.setText("Choose a positive number");
                    } else {
                        if (checkInt(lineNum)) {
                            inputNum = Integer.parseInt(lineNum);
                            if (inputNum > 0) {
                                if (lineRecording.isSelected()) {
                                    islineRecording = true;
                                }
                                if (isOtherLines.isSelected()) {
                                    hasOtherLines = true;
                                }
                                for (int i = 0; i < inputNum; i++) {
                                    mainFrame.MainFramegbc.gridy = i + 1;
                                    mainFrame.MainFramegbc.gridx = 1;
                                    String iToString = Integer.toString(i);
                                    String inputName = "input" + iToString;
                                    LineInput classInput = new LineInput(i, islineRecording, hasOtherLines);
                                    lineInputContainers.put(inputName, classInput);
                                    mainFrame.add(classInput, mainFrame.MainFramegbc);
                                }
                                mainFrame.MainFramegbc.gridy = inputNum + 1;
                                mainFrame.add(mainFrame.finishBtn, mainFrame.MainFramegbc);
                                mainFrame.remove(mainFrame.lineQuestion);
                                mainFrame.revalidate();
                                mainFrame.repaint();

                            } else {
                                lineAmt.setText("Enter a Number More than 0");
                            }
                        } else {
                            lineAmt.setText("Enter a Number");
                        }
                    }
                }
            }

        });
        // timePlay
        timePlayInput = new JTextField();
        timePlayInput.setColumns(15);
        JLabel timePlayInputLabel = new JLabel("How Long Do You Want Your Recorded Line to Play For");
        timePlayInputLabel.setForeground(Color.GRAY);
        lineRecording = new JCheckBox("Do You Want to Record Your Lines");
        isOtherLines = new JCheckBox("Do You Want to Input Lines for Other People");
        isOtherLines.setForeground(Color.GRAY);
        lineRecording.setForeground(Color.GRAY);
        //title
        titleName = new JTextField();
        titleLabel = new JLabel("Title");
        titleLabel.setForeground(Color.gray);
        titleName.setColumns(15);


        lineInputGBC.gridx = 2;        
        lineInputGBC.gridy= 0;
        add(titleLabel, lineInputGBC);
        lineInputGBC.gridy=1;
        add(titleName, lineInputGBC);
        lineInputGBC.gridy = 2;
        add(question, lineInputGBC);
        lineInputGBC.gridy = 3;
        add(lineAmt, lineInputGBC);
        lineInputGBC.gridy = 4;
        add(timePlayInputLabel, lineInputGBC);
        lineInputGBC.gridy = 5;
        add(timePlayInput, lineInputGBC);
        lineInputGBC.gridy = 6;
        lineInputGBC.gridx=1;
        add(isOtherLines, lineInputGBC);
        lineInputGBC.gridy = 6;
        lineInputGBC.gridx=3;
        add(lineRecording, lineInputGBC);
        lineInputGBC.gridx = 2;
        lineInputGBC.gridy = 7;
        add(questionBtn, lineInputGBC);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }

    public boolean checkInt(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
