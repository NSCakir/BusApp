import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map; 
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 
import java.util.ArrayList;

public class VehicleStatusParser
{ 
    private static ArrayList<MinimalBus> vsList;

    public VehicleStatusParser(String sss){
        JSONParser jsp;
        jsp = new JSONParser();
        Object obj;
        vsList = new ArrayList<MinimalBus>();
        try {
          obj = jsp.parse(sss);
          JSONObject jo = (JSONObject) obj;

          JSONArray ja = (JSONArray) jo.get("vehicles");

          Iterator itr1 = ja.iterator();

          MinimalBus minBus;

          String name;
          int nextStopID;
          while (itr1.hasNext())
          {
            JSONObject vehicle = (JSONObject) itr1.next();
            nextStopID = (int) (long) vehicle.get("next_stop");

            minBus = new MinimalBus(nextStopID);
            this.vsList.add(minBus);
          }
     } catch (Exception e) {
        e.printStackTrace();
     }
    }

    public ArrayList<MinimalBus> getVsList(){
        return vsList;
    }

    private static void printOutMinimalBuses(ArrayList<MinimalBus> vsList) {
        for (MinimalBus minBus : vsList){
          System.out.println("Next Bus Stop:");
          System.out.println(minBus.nextStop());
          System.out.println("");
        }
    }

    public static void main(String[] args) throws Exception
    { 
        String filePath = "vehicle_statuses_test.json";
        String content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        VehicleStatusParser vsp = new VehicleStatusParser(content);
        ArrayList<MinimalBus> minBusList = vsp.getVsList();
        printOutMinimalBuses(minBusList);
    } 
} 
