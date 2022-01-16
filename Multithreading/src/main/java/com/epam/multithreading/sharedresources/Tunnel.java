package com.epam.multithreading.sharedresources;

import java.util.concurrent.Semaphore;

public class Tunnel {
	
	private Semaphore tunnelCapacity = new Semaphore(2);
	private int name;
	
	public Tunnel(int name) {
		this.name = name;
	}

	public Semaphore getTunnelCapacity() {
		return tunnelCapacity;
	}

	public int getName() {
		return name;
	}

	public void setTunnelCapacity(Semaphore tunnelCapacity) {
		this.tunnelCapacity = tunnelCapacity;
	}

	public void setName(int name) {
		this.name = name;
	}
	
}
