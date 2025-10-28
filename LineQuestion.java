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
    public HashMap<String, LineInput> lineInputContainters = new HashMap<String, LineInput>();
    LineQuestion(MemorizationFrame mainFrame){  
        questionContatiner = new JPanel();
        this.setLayout(new GridLayout(3,1));
        
        question = new JLabel("How Many Lines");
        question.setForeground(Color.GRAY);
        
        lineAmt = new JTextField();
        lineAmt.setColumns(15);

        
        questionBtn = new JButton("Submit");
        questionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String lineNum = lineAmt.getText();
                if (checkInt(lineNum)){
                    inputNum = Integer.parseInt(lineNum);
                    if (inputNum > 0){
                        for (int i = 0; i < inputNum; i++ ){
                            mainFrame.MainFramegbc.gridy=i;
                            mainFrame.MainFramegbc.gridx=1;
                            String iToString = Integer.toString(i);
                            String inputName= "input" + iToString;
                            LineInput classInput = new LineInput(i);
                            lineInputContainters.put(inputName, classInput);
                            mainFrame.add(classInput, mainFrame.MainFramegbc);
                        }
                        mainFrame.MainFramegbc.gridy=inputNum+1;
                        mainFrame.add(mainFrame.finishBtn, mainFrame.MainFramegbc);
                        mainFrame.remove(mainFrame.lineQuestion);
                        mainFrame.revalidate();
                        mainFrame.repaint();
                    }
                    else{
                        lineAmt.setText("Enter a Number More than 0");
                }
            }
                else{
                    lineAmt.setText("Enter a Number");
                }
            }
        });
        

        add(question);
        add(lineAmt);
        add(questionBtn);     
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }

    public boolean checkInt(String num){
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
