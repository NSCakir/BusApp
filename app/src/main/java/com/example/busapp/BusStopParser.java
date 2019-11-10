package com.example.busapp;
import com.example.busapp.BusStop;

import org.json.simple.parser.ParseException;
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
import java.util.HashMap;

public class BusStopParser
{ 
    private static ArrayList<BusStop> bsList;
    private static HashMap<String, Integer> stopNameMap;

    // this method takes a string which is the entire JSON object
    // and populates the ArrayList instance variable.
    public BusStopParser(String sss){
        JSONParser jsp;
        jsp = new JSONParser();
        Object obj;
        bsList = new ArrayList<BusStop>();
        stopNameMap = new HashMap<String, Integer>();
        try {
          obj = jsp.parse(sss);
          JSONObject jo = (JSONObject) obj;

          JSONArray ja = (JSONArray) jo.get("stops");

          Iterator itr1 = ja.iterator();

          BusStop bs;
          String name;
          int busStopID;
          while (itr1.hasNext())
          {
            JSONObject busStop = (JSONObject) itr1.next();
            name = (String) busStop.get("name");
            busStopID = (int) (long) busStop.get("id");

            bs = new BusStop(name, busStopID);
            this.bsList.add(bs);
            stopNameMap.put(name, busStopID);
          }
     } catch (Exception e) {
        e.printStackTrace();
     }
    }

    // getter method for the list of BusStop
    public ArrayList<BusStop> getBsList(){
        return bsList;
    }
  
    // print out a list of BusStop
    private static void printOutBusStops(ArrayList<BusStop> bsList) {
        for (BusStop b : bsList){
          System.out.println("Bus Stop:");
          System.out.println(b.getName());
          System.out.println(b.getID());
          System.out.println("");
        }
    }

    public static int getIDForStopName(String name){
        return (int) stopNameMap.get(name);
    }

    // this main method serves as an example of how to use the BusStopParser
    public static void main(String[] args) throws Exception
    { 
        // See how I use BusStopParser to return an ArrayList from a string.
        String filePath = "stops_test.json";
        String content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        //particularly this line
        BusStopParser bsp = new BusStopParser(content);
        ArrayList<BusStop> bsl = bsp.getBsList();
        printOutBusStops(bsp.getBsList());
        // demonstration of getIDForStopName
        System.out.println("The Princeton Junction stop has ID#");
        System.out.println(bsp.getIDForStopName("Princeton Junction"));
    } 
}
