import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.simple.parser.JSONParser;

public class NavBar extends JPanel {
    Circle containerCircle;
    JLayeredPane navContainer;
    JButton homeButton;
    JButton editButton;
    JButton reciteButton;
    Circle homeCircle;
    public Circle editCircle;
    public Circle reciteCircle;
    MemorizationFrame randomlyGeneratedVariableName;
    public JScrollPane scrollPane;
    public JPanel scrollPanel;
    NavBar(frameContainer frame) {
        navContainer = new JLayeredPane();
        navContainer.setPreferredSize(new Dimension(250, 75));

        containerCircle = new Circle(0, 12, 250, 40, Color.gray, true);
        containerCircle.circleBind();
        navContainer.add(containerCircle, JLayeredPane.DEFAULT_LAYER);

        reciteCircle = new Circle(150, 20, 25, 25, Color.black, true);
        editCircle = new Circle(110, 20, 25, 25, Color.black, true);
        homeCircle = new Circle(70, 20, 25, 25, Color.black, true);
        reciteCircle.setBackground(Color.gray);
        homeCircle.setBackground(Color.gray);
        editCircle.setBackground(Color.gray);
        reciteCircle.circleBind();
        editCircle.circleBind();
        homeCircle.circleBind();
        navContainer.add(homeCircle, JLayeredPane.PALETTE_LAYER);
        navContainer.add(editCircle, JLayeredPane.PALETTE_LAYER);
        navContainer.add(reciteCircle, JLayeredPane.PALETTE_LAYER);

        homeButton = new JButton("🏠");
        homeButton.setOpaque(true);
        homeButton.setBorder(BorderFactory.createEmptyBorder());
        homeButton.setForeground(Color.white);
        homeButton.setBackground(homeCircle.getColor());
        homeButton.setBounds(75, 25, 15, 15);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    reciteCircle.setColor(Color.black);
                    reciteButton.setBackground(Color.black);
                    editButton.setBackground(Color.black);
                    editCircle.setColor(Color.black);
                    homeButton.setBackground(Color.DARK_GRAY);
                    homeCircle.setColor(Color.DARK_GRAY);
                    System.out.println("home");
                    goHome(frame);
                }
            }
        });
        System.out.println(homeButton.getText());
        navContainer.add(homeButton, JLayeredPane.MODAL_LAYER);

        editButton = new JButton("🖊️");
        editButton.setOpaque(true);
        editButton.setBorder(BorderFactory.createEmptyBorder());
        editButton.setForeground(Color.white);
        editButton.setBackground(editCircle.getColor());
        editButton.setBounds(115, 25, 15, 15);
        System.out.println(editButton.getText());
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    reciteCircle.setColor(Color.black);
                    reciteButton.setBackground(Color.black);
                    editButton.setBackground(Color.DARK_GRAY);
                    editCircle.setColor(Color.DARK_GRAY);
                    homeButton.setBackground(Color.black);
                    homeCircle.setColor(Color.black);
                    System.out.println("edit");
                    goEdit(frame);
                }
            }
        });
        navContainer.add(editButton, JLayeredPane.MODAL_LAYER);

        reciteButton = new JButton("🎤");
        reciteButton.setOpaque(true);
        reciteButton.setBorder(BorderFactory.createEmptyBorder());
        reciteButton.setForeground(Color.white);
        reciteButton.setBackground(reciteCircle.getColor());
        reciteButton.setBounds(155, 25, 15, 15);
        System.out.println(reciteButton.getText());
        reciteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    reciteCircle.setColor(Color.DARK_GRAY);
                    reciteButton.setBackground(Color.DARK_GRAY);
                    editButton.setBackground(Color.black);
                    editCircle.setColor(Color.black);
                    homeButton.setBackground(Color.black);
                    homeCircle.setColor(Color.black);
                    goRecite(frame);
                    System.out.println("recite");
                }
            }
        });
        navContainer.add(reciteButton, JLayeredPane.MODAL_LAYER);
        setBorder(BorderFactory.createEmptyBorder());
        navContainer.setBackground(Color.BLACK);
        setBackground(Color.BLACK);
        navContainer.setOpaque(true);
        add(navContainer);

    }

    public void goEdit(frameContainer frame) {
        frame.getContentPane().removeAll();
        openProject(frame.openedProject + ".json", frame.openedProject, frame);
        frame.Framegbc.gridy = 100;
        frame.add(frame.navBar, frame.Framegbc);
        frame.revalidate();
        frame.repaint();
    }

    public void goHome(frameContainer frame) {
        try {
            frame.getContentPane().removeAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        frame.projPage.checkForNew();
        frame.Framegbc.gridy = 2;
        frame.add(frame.projPage, frame.Framegbc);
        frame.revalidate();
        frame.repaint();
    }

    public void goRecite(frameContainer frame) {
        try {
            frame.getContentPane().removeAll();
        } catch (Exception e) {
            System.out.println(e);
        }

        openProject(frame.openedProject + ".json", frame.openedProject, frame);
        randomlyGeneratedVariableName.finishBtn.doClick();
        ;
        frame.Framegbc.gridy = 100;
        frame.add(frame.navBar, frame.Framegbc);

        frame.revalidate();
        frame.repaint();
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
        randomlyGeneratedVariableName = new MemorizationFrame(frame, scrollPanel);
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
}