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
    public List<List> linesList = new ArrayList<>();
    public void addToLinesList(int lineNum, String line, String audioFile, boolean otherLine){
        List<Object> listOfStuff = new ArrayList<Object>();
        listOfStuff.add(lineNum);
        listOfStuff.add(line);
        listOfStuff.add(audioFile);
        listOfStuff.add(otherLine);
        linesList.add(listOfStuff);
    }
    public List<List> outLines(){
        JSONArray jsonArray = new JSONArray();
        for (int i = 0;i < linesList.size() ; i++) {
            JSONObject obj = new JSONObject();
            JSONObject objItem =  new JSONObject();
            objItem.put("line",  linesList.get(i).get(1));
            objItem.put("audioFile",  linesList.get(i).get(2));
            objItem.put("otherLine",  linesList.get(i).get(3));
            String lineNumber = linesList.get(i).get(0).toString();
            obj.put( lineNumber, objItem);
            jsonArray.put(obj);
        }

        try (FileWriter file = new FileWriter("Data.json")) {
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
