import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class ProjectCreationPanel extends JPanel{
    public JLabel title;
    public JPanel projectBox;
    public ArrayList projectFile;
    ProjectCreationPanel(String name){
        
        projectBox = new JPanel();
        this.setBackground(Color.black);
        projectBox.setLayout(new GridBagLayout());
        GridBagConstraints ProjectCreationGBC = new GridBagConstraints();
        Border blackline = BorderFactory.createLineBorder(Color.white);
        this.setBorder(blackline);

        title = new JLabel(name);
        title.setForeground(Color.gray);
        ProjectCreationGBC.gridx=1;
        ProjectCreationGBC.gridy=1;
        add(title);




        
    }

    ProjectCreationPanel(){
        projectBox = new JPanel();
        this.setBackground(Color.black);
        projectBox.setLayout(new GridBagLayout());
        GridBagConstraints ProjectCreationGBC = new GridBagConstraints();
        Border blackline = BorderFactory.createLineBorder(Color.white);
        this.setBorder(blackline);

        title = new JLabel("Create A Project");
        title.setForeground(Color.gray);
        ProjectCreationGBC.gridx=1;
        ProjectCreationGBC.gridy=1;
        add(title);
    }
    public void openProject(){

    }
    public void createProject(){

    }
}

