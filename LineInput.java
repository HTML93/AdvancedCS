import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class LineInput extends JPanel implements ActionListener {
    public JButton button;
    public JLabel title;
    public JTextField lineText;
    public JPanel inputContatiner;
    LineInput(){
        title = new JLabel("Memorization Application");
       
        //button
        button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            title.setText(lineText.getText());
    }
        });

        //input
        lineText = new JTextField();
        lineText.setColumns(10);
        inputContatiner = new JPanel();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        
        gbc.gridx=1;
        gbc.gridy=1;
        add(title, gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        add(lineText, gbc);
        gbc.gridx=1;
        gbc.gridy=3;
        add(button, gbc);
        this.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        setVisible(true);
        System.out.println("akfdljalksfjakl;sdjf");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    
}
}