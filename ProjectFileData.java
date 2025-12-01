import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.io.*;

public class ProjectFileData {
    private String projectName;
    @SuppressWarnings("rawtypes")
    public HashMap<String, List> linesList = new HashMap<>();
    public void addToLinesList(String title, int lineNum, String line, String audioFile, boolean otherLine){
        List<Object> listOfStuff = new ArrayList<Object>();
        listOfStuff.add(line);
        listOfStuff.add(audioFile);
        listOfStuff.add(otherLine);
        linesList.put(title, listOfStuff);
    }
    public HashMap<String, List> outLines(){
        JSONArray jsonArray = new JSONArray();
        String title = null;
        for (String i : linesList.keySet()) {
            JSONObject obj = new JSONObject();
            JSONObject objItem =  new JSONObject();
            List<Object> listofstuff = linesList.get(i);
            objItem.put("line",  listofstuff.get(1));
            objItem.put("audioFile",  listofstuff.get(2));
            objItem.put("otherLine",  listofstuff.get(3));
            String lineNumber = linesList.get(i).get(0).toString();
            obj.put( lineNumber, objItem);
            jsonArray.put(obj);
            title = i;
        }

        try (FileWriter file = new FileWriter(title+".json")) {
            file.write(jsonArray.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jsonArray);
        } catch(Exception e){
            System.out.println(e);

        }
        return linesList;
    }
    public void Main() {

    }

}
