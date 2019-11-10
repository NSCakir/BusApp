package com.example.busapp;


import java.io.FileReader;
import java.util.Iterator;
import java.util.Map; 
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 
import java.util.ArrayList;

// this Java class's main() function reads in a JSON file using FileReader,
// then parses it for the stop ID and names, putting each into a BusStop object.
// Then it prints out the BusStop object's IDs and names.
public class makeBusStopList
{ 
    private static void printOutBusStops(ArrayList<BusStop> bsList) {
        for (BusStop b : bsList){
          System.out.println("Bus Stop:");
          System.out.println(b.getName());
          System.out.println(b.getID());
          System.out.println("");
        }
    }

    public static void main(String[] args) throws Exception
    { 
        Object obj = new JSONParser().parse(new FileReader("stops_test.json"));
        JSONObject jo = (JSONObject) obj;

        JSONArray ja = (JSONArray) jo.get("stops");

        Iterator itr1 = ja.iterator();

        BusStop bs;

        ArrayList<BusStop> bsList = new ArrayList<BusStop>();

        String name;
        int busStopID;
        long numberOfStops = 0;
        while (itr1.hasNext())
        {
          numberOfStops++;
          JSONObject busStop = (JSONObject) itr1.next();
          name = (String) busStop.get("name");
          busStopID = (int) (long) busStop.get("id");

          bs = new BusStop(name, busStopID);
          bsList.add(bs);
        }
        System.out.println("Total stops " + numberOfStops);
        printOutBusStops(bsList);
    } 
} 
