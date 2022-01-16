package com.epam.multithreading.jsonreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.multithreading.threads.Train;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
    private static final Logger LOGGER = LogManager.getLogger(JsonReader.class);

    private JsonReader() {
    }

    private static String readJson (String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader (new FileReader(path))) {
            int ch;
            while ((ch = bufferedReader.read()) != -1) {
                builder.append((char)ch);
            }
        }
        return builder.toString();
    }

// Version without throwing of exceptions
    public static List<Train> getTrainList(String path) {
        ObjectMapper mapper = new ObjectMapper();
        Train[] trains = new Train[0];
        try {
        	trains = mapper.readValue(readJson(path), Train[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(Arrays.asList(trains));
    }
}
