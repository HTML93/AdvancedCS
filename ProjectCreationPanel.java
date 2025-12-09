import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
        projectBox.setLayout(new GridBagLayout());
        GridBagConstraints ProjectCreationGBC = new GridBagConstraints();
        Border blackline = BorderFactory.createLineBorder(Color.white);
        // Border blankBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.setBorder(blackline);
        // this.setBorder(blankBorder);
        title = new JLabel(name);
        title.setForeground(Color.gray);
        ProjectCreationGBC.gridx = 1;
        ProjectCreationGBC.gridy = 1;
        add(title);

        openButton = new JButton("Open");

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openProject(name + ".json");
                System.out.println("name: " + name);
            }
        });
        add(openButton);
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
        projectBox.setLayout(new GridBagLayout());
        GridBagConstraints ProjectCreationGBC = new GridBagConstraints();
        Border blackline = BorderFactory.createLineBorder(Color.white);
        this.setBorder(blackline);

        title = new JLabel("Create A Project");
        title.setForeground(Color.gray);
        ProjectCreationGBC.gridx = 1;
        ProjectCreationGBC.gridy = 1;
        add(title);
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

    public void openProject(String fileName) {
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
                LineInput currentLineTitle = new LineInput(i + 1, false,(boolean) idkWhatToCallcertainVariablesanymore.get("otherLine"));
                currentLineTitle.title.setText(idkWhatToCallcertainVariablesanymore.get("line").toString());
                currentLineTitle.lineText.setText(idkWhatToCallcertainVariablesanymore.get("line").toString());
                currentLineTitle.remove(currentLineTitle.lineText);
                currentLineTitle.remove(currentLineTitle.subButton);
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

    }
}
