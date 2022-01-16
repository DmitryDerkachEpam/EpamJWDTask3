package com.epam.multithreading.runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.epam.multithreading.jsonreader.JsonReader;
import com.epam.multithreading.threads.Train;

public class ApplicationRunner {
	private static final String JSON_FILE_PATH = "src/main/resources/trains.json";
	
	public static void main(String[] args) {
		
		List<Train> trains = JsonReader.getTrainList(JSON_FILE_PATH);
		ExecutorService threadPool = Executors.newFixedThreadPool(trains.size());
		
		
		
		
        for (Train train : trains) {
            threadPool.submit(train);
            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        threadPool.shutdown();
		
	}
}
