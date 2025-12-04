import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.io.*;
import java.lang.reflect.Array;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class ProjectFileData {
    public HashMap<String, List> linesList = new HashMap<>();
    public String projectTitle;

    public void addToLinesList(String title, int lineNum, String line, String audioFile, boolean otherLine) {
        List<Object> listOfStuff = new ArrayList<Object>();
        listOfStuff.add(line);
        listOfStuff.add(audioFile);
        listOfStuff.add(otherLine);
        listOfStuff.add(lineNum);
        String lineNumber = String.valueOf(lineNum);
        projectTitle = title;
        linesList.put(lineNumber, listOfStuff);
    }

    public void outLines() {
        System.out.println(linesList);
        JSONArray jsonArray = new JSONArray();
        for (String i : linesList.keySet()) {
            JSONObject obj = new JSONObject();
            JSONObject objItem = new JSONObject();
            List<Object> listofstuff = linesList.get(i);
            objItem.put("lineNum", listofstuff.get(3));
            objItem.put("audioFile", listofstuff.get(1));
            objItem.put("otherLine", listofstuff.get(2));
            String lineNumber = linesList.get(i).get(0).toString();
            obj.put(lineNumber, objItem);
            jsonArray.put(obj);
            System.out.println("hi");
        }
        try (FileWriter file = new FileWriter(projectTitle + ".json")) {
            file.write(jsonArray.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jsonArray);
        } catch (Exception e) {
            System.out.println(e);

        }

        try {
            File fileGo = new File("projects.json");
            ArrayList<String> projectsToAdd = retrieveProjects();
            JSONArray projects = new JSONArray();
            projects.put(projectsToAdd);
            projects.put(projectTitle);
            FileWriter file = new FileWriter(fileGo);

            file.write(projects.toString());
            file.flush();
            file.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ArrayList<String> retrieveProjects() {
        
        try {
            File projectFile = new File("projects.json");
            ArrayList<String> projects = new ArrayList<String>();

            if (projectFile.exists() && projectFile.length() > 0) {

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(new FileReader(projectFile));
                ArrayList<String> listObj = (ArrayList<String>) obj;
                for (int i = 0; i < listObj.size(); i++) {
                    projects.add(listObj.get(i));
                }
            }
            return projects;

        } catch (Exception e) {
            return null;
        }
    }

    public void Main() {

    }

}
