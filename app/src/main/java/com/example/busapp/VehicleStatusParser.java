package com.example.busapp;
import com.example.busapp.MinimalBus;

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

    // this method takes a string which is the entire JSON object
    // and populates the ArrayList instance variable.
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

    // getter method for the list of VehicleStatuses
    public ArrayList<MinimalBus> getVsList(){
        return vsList;
    }

    // print out a list of VehicleStatus
    private static void printOutMinimalBuses(ArrayList<MinimalBus> vsList) {
        for (MinimalBus minBus : vsList){
          System.out.println("Next Bus Stop:");
          System.out.println(minBus.nextStop());
          System.out.println("");
        }
    }

    // this main method serves as an example of how to use the BusStopParser
    public static void main(String[] args) throws Exception
    { 
        // See how I use VehicleStatusParser to return an ArrayList from a string.
        String filePath = "vehicle_statuses_test.json";
        String content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        //particularly this line
        VehicleStatusParser vsp = new VehicleStatusParser(content);
        ArrayList<MinimalBus> minBusList = vsp.getVsList();
        printOutMinimalBuses(minBusList);
    } 
} 
