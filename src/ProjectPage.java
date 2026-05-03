

import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.List;

import javax.swing.*;


public class ProjectPage extends JPanel {
    public ProjectFileData projectFileCall = new ProjectFileData();
    public ProjectPage mainProjectPage;
    public GridBagConstraints ProjectPageGBC = new GridBagConstraints();
    public List<ProjectCreationPanel> projectPanels = new ArrayList<ProjectCreationPanel>();
    ArrayList<String> projects;
    frameContainer frame;
    public ProjectPage(frameContainer f) {
        frame = f;
        setLayout(new GridBagLayout());
        ProjectPageGBC.gridy = 0;
        ProjectPageGBC.gridx = 0;
        projects = ProjectFileData.retrieveFile("projects.json");
        addProjs(projects);
        setBackground(Color.BLACK);

        setVisible(true);

    }

     public void checkForNew() {
        ArrayList<String> newProjects = ProjectFileData.retrieveFile("projects.json");
        for(int i =0; i<newProjects.size(); i++){
            if(Files.exists(Paths.get(newProjects.get(i)+".json"))==false){
                newProjects.remove(i);
            }
        }
        if (projects!=newProjects){
            this.removeAll();
            addProjs(newProjects);
            frame.revalidate();
            frame.repaint();
        }
    }
    private void addProjs(ArrayList<String> project){
        ProjectPageGBC.gridx = 0;
        ProjectPageGBC.gridy = 0;
        System.out.println(project);
        for (int i = 0; i < project.size(); i++) {
            ProjectCreationPanel meaninglessVariableName = new ProjectCreationPanel(project.get(i), frame);
            add(meaninglessVariableName, ProjectPageGBC);
            if (ProjectPageGBC.gridx == 2) {
                ProjectPageGBC.gridx = 0;
                ProjectPageGBC.gridy++;
            } else {
                ProjectPageGBC.gridx++;
            }
            projectPanels.add(meaninglessVariableName);
        }
        add(new ProjectCreationPanel(frame), ProjectPageGBC);
        for (int i = 0; i < projectPanels.size(); i++) {
            projectPanels.get(i).setPreferredSize(ProjectCreationPanel.maxDimension);
        }
    }

}
