package com.example.busapp;

public class MinimalBus {
	private int nextStop;
	public MinimalBus(int nextStop){
		this.nextStop = nextStop;
	}
	public int nextStop(){
		return nextStop;
	}
}
