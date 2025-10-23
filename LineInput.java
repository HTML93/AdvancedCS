import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class LineInput extends JPanel {
    public JButton subButton;
    public JLabel title;
    public JTextField lineText;
    public JPanel inputContatiner;
    LineInput(){

        inputContatiner = new JPanel();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //title
        title = new JLabel("Memorization Application");
        title.setForeground(Color.GRAY);
        gbc.gridx=1;
        gbc.gridy=1;
        add(title, gbc);
       
        //button
        subButton = new JButton("Submit");
        subButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            title.setText(lineText.getText());
    }
        });
        subButton.setForeground(Color.gray);
        gbc.gridx=1;
        gbc.gridy=3;
        add(subButton, gbc);

        //input
        lineText = new JTextField();
        lineText.setColumns(10);
        lineText.setBackground(Color.GRAY);
        gbc.gridx=1;
        gbc.gridy=2;
        add(lineText, gbc);


        
        this.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        this.setBackground(Color.BLACK);
        setVisible(true);
    }
}