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

        inputContatiner = new JPanel();
        LayoutManager layout = new BoxLayout(inputContatiner, BoxLayout.PAGE_AXIS);  
        inputContatiner.setLayout(layout);
        add(title);
        add(lineText);
        add(button);
        setVisible(true);
        System.out.println("akfdljalksfjakl;sdjf");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    
}
}