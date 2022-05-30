package com.mysolution.app.service;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.mysolution.app.model.InputEvent;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadEventService {

    private static final Logger logger = LogManager.getLogger(ReadEventService.class);

    public final List<InputEvent> readEventFile(String filePath) {
        logger.info("Started reading event file with path {}",filePath);
        List<InputEvent> inputEventList =  new LinkedList<>();
        try{
            FileInputStream inputStream = null;
            Scanner sc = null;
            Gson gson = new Gson();
            try {
                inputStream = new FileInputStream(filePath);
                sc = new Scanner(inputStream, "UTF-8");
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    InputEvent inputEvent = gson.fromJson(line, InputEvent.class);
                    inputEventList.add(inputEvent);
                }
                if (sc.ioException() != null) {
                    logger.error("Error occurred while reading event file with path {}",filePath);
                    throw sc.ioException();
                }
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (sc != null) {
                    sc.close();
                }
            }
        }
        catch(Exception ex){
            logger.error("Error occurred while reading event file with path {}, Exception {}",
                    filePath,ex.toString());
        }
        logger.info("Finished reading event file with path {}",filePath);
        logger.debug("Finished reading event file with path {} with data {} ",filePath, inputEventList);
        return inputEventList;
    }

}
