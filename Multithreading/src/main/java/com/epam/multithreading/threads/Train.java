package com.epam.multithreading.threads;

import com.epam.multithreading.sharedresources.Tunnels;

public class Train implements Runnable {

	private int number;
	
	public Train(int number) {
		this.number = number;
	}
	
	public Train() {
		
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public void run() {
		Tunnels tunnels = Tunnels.getInstance();
		tunnels.passTrain(this);
		
	}

	@Override
	public String toString() {
		return "Train [number=" + number + ']';
	}
	
	
	
	

	
}
