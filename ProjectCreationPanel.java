import java.awt.Button;
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
    public JScrollPane scrollPane;
    public JPanel scrollPanel;
    public JButton delete;
    public static Dimension maxDimension = new Dimension();
    static {
        maxDimension.height = 0;
        maxDimension.width = 0;
    }
    public JButton openButton;

    ProjectCreationPanel(String name, frameContainer frame) {
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
    
        title.setBorder(blankBorder);
        add(title, ProjectOpenGBC);
        delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ProjectFileData.deleteProj(name);
                frame.projPage.checkForNew();
            }
        });
        openButton = new JButton("Open");

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openProject(name + ".json", name, frame); 
            }
        });
        ProjectOpenGBC.gridy++;

        add(openButton, ProjectOpenGBC);
        ProjectOpenGBC.gridy++;
        add(delete, ProjectOpenGBC);
        if (this.getPreferredSize().height > maxDimension.height) {
            maxDimension.height = this.getPreferredSize().height;
        }
        if (this.getPreferredSize().width > maxDimension.width) {
            maxDimension.width = this.getPreferredSize().width;
        }
    }

    ProjectCreationPanel(frameContainer frame) {
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

        add(title, ProjectCreationGBC);
        // Create button
        openButton = new JButton("Create");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createProject(frame);
            }
        });
        ProjectCreationGBC.gridy++;

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

    public void openProject(String fileName, String name, frameContainer frame) {
        ArrayList<Object> project = new ArrayList<Object>();
        frame.openedProject = name;
        frame.Framegbc.gridy = 100;

        frame.navBar.reciteCircle.setColor(Color.black);
        frame.navBar.reciteButton.setBackground(Color.black);
        frame.navBar.editButton.setBackground(Color.DARK_GRAY);
        frame.navBar.editCircle.setColor(Color.DARK_GRAY);
        frame.navBar.homeButton.setBackground(Color.black);
        frame.navBar.homeCircle.setColor(Color.black);
        frame.add(frame.navBar, frame.Framegbc);
        scrollPanel = new JPanel();
        scrollPanel.setBackground(Color.black);
        scrollPane = new JScrollPane(scrollPanel);
        
        try {
            File projectFile = new File(fileName);

            if (projectFile.exists() && projectFile.length() > 0) {

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(new FileReader(projectFile));
                org.json.simple.JSONArray listObj = (org.json.simple.JSONArray) obj;

                for (int i = 0; i < listObj.size(); i++) {
                    project.add(listObj.get(i));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        GridLayout editLayout = new GridLayout();
        editLayout.setColumns(6);
        scrollPanel.setLayout(editLayout);
        scrollPanel.setName("scroll");
        MemorizationFrame randomlyGeneratedVariableName = new MemorizationFrame(frame, scrollPanel);
        randomlyGeneratedVariableName.title = name;
        randomlyGeneratedVariableName.remove(randomlyGeneratedVariableName.lineQuestion);
        randomlyGeneratedVariableName.MainFramegbc.gridy = 0;
        
        int linenum = 0;
        for (int i = 0; i < project.size(); i++) {
            org.json.simple.JSONObject currentLine = (org.json.simple.JSONObject) project.get(i);

            randomlyGeneratedVariableName.MainFramegbc.gridx = 1;
            linenum++;
            for (Object key : currentLine.keySet()) {
                org.json.simple.JSONObject idkWhatToCallcertainVariablesanymore = (org.json.simple.JSONObject) currentLine.get(key.toString());
                randomlyGeneratedVariableName.lineQuestion.timePlay = (int) (long) idkWhatToCallcertainVariablesanymore.get("recordLength");
                randomlyGeneratedVariableName.lineQuestion.islineRecording = (boolean) idkWhatToCallcertainVariablesanymore.get("islineRecording");

                LineInput currentLineTitle = new LineInput(i + 1, false, (boolean) idkWhatToCallcertainVariablesanymore.get("otherLine"), name);
                currentLineTitle.title.setText(idkWhatToCallcertainVariablesanymore.get("line").toString());
                currentLineTitle.lineText.setText(idkWhatToCallcertainVariablesanymore.get("line").toString());
                if ((boolean) idkWhatToCallcertainVariablesanymore.get("otherLine") == true) {
                    currentLineTitle.title.setForeground(Color.blue);
                    currentLineTitle.otherLineSelected = true;
                }
                /*
                 * if ((boolean) idkWhatToCallcertainVariablesanymore.get("islineRecording") ==
                 * true) {
                 * currentLineTitle.title.setForeground(Color.blue);
                 * currentLineTitle.otherLineSelected = true;
                 * }
                 */
                
                currentLineTitle.remove(currentLineTitle.lineText);
                currentLineTitle.remove(currentLineTitle.subButton);
                currentLineTitle.remove(currentLineTitle.otherLine);
                currentLineTitle.gbc.gridy = 2;
                currentLineTitle.add(currentLineTitle.editButton, currentLineTitle.gbc);
                scrollPanel.add(currentLineTitle);
                currentLineTitle.revalidate();
                currentLineTitle.repaint();
                currentLineTitle.setBorder(null);

                randomlyGeneratedVariableName.lineQuestion.lineInputContainers.put("input" + Integer.toString(linenum - 1), currentLineTitle);

            }
            randomlyGeneratedVariableName.MainFramegbc.gridy = linenum + 1;
            scrollPane.setBackground(Color.BLACK);
            Dimension mainSize = randomlyGeneratedVariableName.getSize();
            mainSize.height += 500;
            mainSize.width += 500;
            System.out.println(mainSize);
            scrollPane.setPreferredSize(mainSize);
            // scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            // scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.remove(scrollPane.getHorizontalScrollBar());
            randomlyGeneratedVariableName.MainFramegbc.gridy++;

            randomlyGeneratedVariableName.add(scrollPane, randomlyGeneratedVariableName.MainFramegbc);
            randomlyGeneratedVariableName.MainFramegbc.gridy++;
            randomlyGeneratedVariableName.add(randomlyGeneratedVariableName.finishBtn, randomlyGeneratedVariableName.MainFramegbc);
            if (randomlyGeneratedVariableName.lineQuestion.islineRecording) {
                add(randomlyGeneratedVariableName.playButton,
                        randomlyGeneratedVariableName.MainFramegbc);
            }

        }

        frame.add(randomlyGeneratedVariableName);
        frame.remove(frame.projPage);
        frame.repaint();
        frame.revalidate();

    }

    public void createProject(frameContainer frame) {
        MemorizationFrame creationFrame = new MemorizationFrame(frame, null);
        frame.add(creationFrame);
        frame.Framegbc.gridy = 100;
        File file = new File("projects.json");
        if (file.exists() == false || ProjectFileData.retrieveFile("projects.json").size() == 0) {
            frame.navBar.editButton.setEnabled(false);
            frame.navBar.reciteButton.setEnabled(false);
        }

        frame.add(frame.navBar, frame.Framegbc);
        frame.remove(frame.projPage);
        frame.repaint();
        frame.revalidate();

    }
}
