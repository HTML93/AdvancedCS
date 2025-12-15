import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class ProjectCreationPanel extends JPanel {
    public JLabel title;
    public JPanel projectBox;
    public ArrayList projectFile;
    public static Dimension maxDimension = new Dimension();
    static {
        maxDimension.height = 0;
        maxDimension.width = 0;
    }
    public JButton openButton;

    ProjectCreationPanel(String name) {
        projectBox = new JPanel();

        this.setBackground(Color.black);
        this.setLayout(new GridBagLayout());
        GridBagConstraints ProjectOpenGBC = new GridBagConstraints();
        Border blackline = BorderFactory.createLineBorder(Color.white);
        Border blankBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.setBorder(blackline);
        title = new JLabel(name);
        title.setForeground(Color.gray);
        ProjectOpenGBC.gridx = 1;
        ProjectOpenGBC.gridy = 1;
        System.out.println("title: "+ProjectOpenGBC.gridy);
        title.setBorder(blankBorder);
        add(title, ProjectOpenGBC);

        openButton = new JButton("Open");

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openProject(name + ".json", name);
                System.out.println("name: " + name);
            }
        });
        ProjectOpenGBC.gridy++;
        System.out.println("open: "+ProjectOpenGBC.gridy);
        add(openButton, ProjectOpenGBC);
        if (this.getPreferredSize().height > maxDimension.height) {
            maxDimension.height = this.getPreferredSize().height;
        }
        if (this.getPreferredSize().width > maxDimension.width) {
            maxDimension.width = this.getPreferredSize().width;
        }
    }

    ProjectCreationPanel() {
        projectBox = new JPanel();
        this.setBackground(Color.black);
        this.setLayout(new GridBagLayout());
        GridBagConstraints ProjectCreationGBC = new GridBagConstraints();
        Border blackline = BorderFactory.createLineBorder(Color.white);
        this.setBorder(blackline);
        Border blankBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        title = new JLabel("Create A Project");
        title.setBorder(blankBorder);
        title.setForeground(Color.gray);
        ProjectCreationGBC.gridx = 1;
        ProjectCreationGBC.gridy = 1;
        System.out.println("Create Title: "+ ProjectCreationGBC.gridy);
        add(title, ProjectCreationGBC);
        //Create button
        openButton = new JButton("Create");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createProject();
            }
        });
        ProjectCreationGBC.gridy++;
                System.out.println("createbtn: "+ProjectCreationGBC.gridy);

        add(openButton, ProjectCreationGBC);
        if (this.getPreferredSize().height > maxDimension.height) {
            maxDimension.height = this.getPreferredSize().height;
        }
        if (this.getPreferredSize().width > maxDimension.width) {
            maxDimension.width = this.getPreferredSize().width;
        }
        maxDimension.height += 15;
        maxDimension.width += 2;
        setPreferredSize(maxDimension);
    }

    public void openProject(String fileName, String name) {
        ArrayList<Object> project = new ArrayList<Object>();

        try {
            File projectFile = new File(fileName);

            if (projectFile.exists() && projectFile.length() > 0) {
                System.out.println("exists");
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(new FileReader(projectFile));
                org.json.simple.JSONArray listObj = (org.json.simple.JSONArray) obj;
                System.out.println(listObj);
                for (int i = 0; i < listObj.size(); i++) {
                    project.add(listObj.get(i));
                }
            }

            System.out.println("project: " + project);

        } catch (Exception e) {
            System.out.println(e);
        }
        MemorizationFrame randomlyGeneratedVariableName = new MemorizationFrame();
        randomlyGeneratedVariableName.title = name; 
        randomlyGeneratedVariableName.remove(randomlyGeneratedVariableName.lineQuestion);
        randomlyGeneratedVariableName.MainFramegbc.gridy = 0;
        int linenum=0;
        for (int i = 0; i < project.size(); i++) {
            org.json.simple.JSONObject currentLine = (org.json.simple.JSONObject) project.get(i);
            System.out.println(currentLine);
            randomlyGeneratedVariableName.MainFramegbc.gridx=1;
            linenum++;
            for (Object key : currentLine.keySet()) {
                org.json.simple.JSONObject idkWhatToCallcertainVariablesanymore = (org.json.simple.JSONObject) currentLine.get(key.toString());
                System.out.println("current line: " + linenum + " line: " + idkWhatToCallcertainVariablesanymore.get("line"));
                randomlyGeneratedVariableName.MainFramegbc.gridy=linenum;
                LineInput currentLineTitle = new LineInput(i + 1, false,(boolean) idkWhatToCallcertainVariablesanymore.get("otherLine"), name);
                currentLineTitle.title.setText(idkWhatToCallcertainVariablesanymore.get("line").toString());
                currentLineTitle.lineText.setText(idkWhatToCallcertainVariablesanymore.get("line").toString());
                if ((boolean)idkWhatToCallcertainVariablesanymore.get("otherLine")==true){
                    currentLineTitle.title.setForeground(Color.blue);
                    currentLineTitle.otherLineSelected=true;
                }
                randomlyGeneratedVariableName.lineQuestion.timePlay = (int)(long)idkWhatToCallcertainVariablesanymore.get("recordLength"); 
                randomlyGeneratedVariableName.lineQuestion.islineRecording = (boolean)idkWhatToCallcertainVariablesanymore.get("islineRecording"); 
                currentLineTitle.remove(currentLineTitle.lineText);
                currentLineTitle.remove(currentLineTitle.subButton);
                currentLineTitle.remove(currentLineTitle.otherLine);
                currentLineTitle.gbc.gridy=2;
                currentLineTitle.add(currentLineTitle.editButton, currentLineTitle.gbc);
                randomlyGeneratedVariableName.add(currentLineTitle, randomlyGeneratedVariableName.MainFramegbc);
                currentLineTitle.revalidate();
                currentLineTitle.repaint();
                currentLineTitle.setBorder(null);

                randomlyGeneratedVariableName.lineQuestion.lineInputContainers.put("input"+Integer.toString(linenum-1), currentLineTitle);
                System.out.println(fileName);
            }
           randomlyGeneratedVariableName.MainFramegbc.gridy = linenum + 1;
                randomlyGeneratedVariableName.MainFramegbc.gridy++;
                randomlyGeneratedVariableName.add(randomlyGeneratedVariableName.finishBtn, randomlyGeneratedVariableName.MainFramegbc);
                /*if (lineQuestion.islineRecording) {
                    add(randomlyGeneratedVariableName.playButton, randomlyGeneratedVariableName.MainFramegbc);
                }*/
               System.out.println(randomlyGeneratedVariableName.lineQuestion.lineInputContainers);
        }

    }

    public void createProject() {
        MemorizationFrame creationFrame = new MemorizationFrame();

    }
}
