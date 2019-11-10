package com.example.busapp;

// this is a very simple Java class that represents a bus stop
public class BusStop
{
	private String name;
	private int stopID;

	private BusStop() {
	}

	public BusStop(String name, int id){
		this.name = name;
		this.stopID = id;
	}

	public int getID(){
		return stopID;
	}

	public String getName(){
		return name;
	}
}
