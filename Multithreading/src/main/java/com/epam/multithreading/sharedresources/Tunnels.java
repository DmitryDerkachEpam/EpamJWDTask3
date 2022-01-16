package com.epam.multithreading.sharedresources;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.multithreading.threads.Train;

public class Tunnels {
	
	private static final Logger LOGGER = LogManager.getLogger(Tunnels.class);
	private static final Lock STATIC_LOCK = new ReentrantLock();
	private static Tunnels instance;
	private List<Tunnel> tunnels = new ArrayList<>();
	
	private Tunnels() {
		tunnels.add(new Tunnel(1));
		tunnels.add(new Tunnel(2));
	}
	
	public static Tunnels getInstance() {
		Tunnels temporalInstance = instance;
        if (temporalInstance == null) {
            STATIC_LOCK.lock();
            temporalInstance = instance;
            if (temporalInstance == null) {
                instance = temporalInstance = new Tunnels();
            }
            STATIC_LOCK.unlock();
        }
        LOGGER.info("Instance of the Base was returned");
        return temporalInstance;
    }
	
	public void passTrain(Train train) {
		Tunnel firstTunnel = tunnels.get(0);
		Tunnel secondTunnel = tunnels.get(1);
		while (true) {	
			if	(firstTunnel.getTunnelCapacity().availablePermits() > 0) {
				try {
					firstTunnel.getTunnelCapacity().acquire();
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.println("Поезд " + train + " заехал в тоннель " + firstTunnel.getName() + Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				firstTunnel.getTunnelCapacity().release();
				System.out.println("Поезд " + train + " выехал из тоннеля №1");
				break;
			}
			
			if	(secondTunnel.getTunnelCapacity().availablePermits() > 0) {
				try {
					secondTunnel.getTunnelCapacity().acquire();
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.println("Поезд " + train + " заехал в тоннель №2 " + Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				secondTunnel.getTunnelCapacity().release();
				System.out.println("Поезд " + train + " выехал из тоннеля №2");
				break;
			}
		}//while
		
	}
	
}
