import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class LineInput extends JPanel {
    public JLabel title;
    public JTextField lineText;
    public JPanel inputContatiner;
    public JButton subButton;
    public JCheckBox otherLine;
    public JButton editButton;
    public boolean otherLineSelected;

    LineInput(int lineNumber) {

        inputContatiner = new JPanel();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        otherLine = new JCheckBox("Other Person's Line?", false);
        otherLine.setForeground(Color.GRAY);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(otherLine, gbc);

        // title
        title = new JLabel("Line Number " + (lineNumber+1));
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
                gbc.gridx = 1;
                gbc.gridy = 3;
                add(otherLine, gbc);
                gbc.gridx = 1;
                gbc.gridy = 4;
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
                if (titleSet.isEmpty()==false) {

                    title.setText(titleSet);
                    if (otherLine.isSelected()) {
                        title.setForeground(Color.blue);
                        otherLineSelected = true;
                    } else {
                        title.setForeground(Color.GRAY);
                        otherLineSelected = false;
                    }
                    remove(lineText);
                    remove(subButton);
                    remove(otherLine);
                    gbc.gridx = 1;
                    gbc.gridy = 4;
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
        gbc.gridy = 4;
        add(subButton, gbc);

        // input
        lineText = new JTextField();
        lineText.setColumns(10);
        lineText.setBackground(Color.GRAY);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(lineText, gbc);

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        this.setBackground(Color.BLACK);
        setVisible(true);
    }
}