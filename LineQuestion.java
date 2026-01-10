import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.*;

public class LineQuestion extends JPanel {
    public int inputNum = 0;
    public JLabel question;
    public JTextField lineAmt;
    public JButton questionBtn;
    public JPanel questionContatiner;
    public LinkedHashMap<String, LineInput> lineInputContainers = new LinkedHashMap<String, LineInput>();
    public boolean islineRecording = false;
    public JCheckBox lineRecording;
    public JCheckBox isOtherLines;
    public boolean hasOtherLines = false;
    public int timePlay;
    public JTextField timePlayInput;
    public JTextField titleName;
    public JLabel titleLabel;
    public String title;
    public JScrollPane scrollPane;
    public JPanel scrollPanel;
    public GridLayout lineQuestionLayout;
    LineQuestion(MemorizationFrame mainFrame) {
        questionContatiner = new JPanel();
        this.setLayout(new GridBagLayout());
        GridBagConstraints lineInputGBC = new GridBagConstraints();
        question = new JLabel("How Many Lines");
        question.setForeground(Color.GRAY);
        scrollPanel= new JPanel();
        scrollPanel.setBackground(Color.black);
        scrollPane = new JScrollPane(scrollPanel);
        lineQuestionLayout = new GridLayout();

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
                            title = titleName.getText();
                            inputNum = Integer.parseInt(lineNum);
                            if (inputNum > 0) {
                                if (lineRecording.isSelected()) {
                                    islineRecording = true;
                                }
                                if (isOtherLines.isSelected()) {
                                    hasOtherLines = true;
                                }
                                int columns = 6;
                                
                               lineQuestionLayout.setColumns(columns);
                                int rows = (inputNum/columns)+1;
                                lineQuestionLayout.setRows(rows);
                                System.out.println(lineQuestionLayout.getRows() +" "+ rows);
                                scrollPanel.setLayout(lineQuestionLayout);
                                System.out.println(scrollPanel.getLayout());

                                for (int i = 0; i < inputNum; i++) {
                                    mainFrame.MainFramegbc.gridy = i + 1;
                                    mainFrame.MainFramegbc.gridx = 1;
                                    String iToString = Integer.toString(i);
                                    String inputName = "input" + iToString;
                                    LineInput classInput = new LineInput(i, islineRecording, hasOtherLines, titleName.getText() );
                                    lineInputContainers.put(inputName, classInput);
                                    scrollPanel.add(classInput);
                                }
                                mainFrame.MainFramegbc.gridy = inputNum + 1;
                                scrollPane.setBackground(Color.BLACK);
                                Dimension mainSize = mainFrame.getSize();

                                mainSize.height+=mainFrame.getHeight()*3;
                                System.out.println(mainSize);
                                scrollPane.setPreferredSize(mainSize);
                              // scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                                //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                                scrollPane.remove(scrollPane.getHorizontalScrollBar());
                                mainFrame.add(scrollPane, mainFrame.MainFramegbc);
                                mainFrame.MainFramegbc.gridy++;
                                mainFrame.add(mainFrame.finishBtn, mainFrame.MainFramegbc);
                                mainFrame.remove(mainFrame.lineQuestion);
                                mainFrame.frame.openedProject=titleName.getText();
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
