package com.mysolution.app;

import com.mysolution.app.model.Event;
import com.mysolution.app.model.InputEvent;
import com.mysolution.app.service.ProcessEventService;
import com.mysolution.app.service.ReadEventService;

import java.util.List;
import java.util.Map;

import com.mysolution.app.service.DbEventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CreditSuisseApplication {

    private static final Logger logger = LogManager.getLogger(CreditSuisseApplication.class);

    public CreditSuisseApplication() {
    }

    public static void main(String[] args) {
        logger.info("Started Event Parsing Application");
        logger.debug("Started Event Parsing Application");
        logger.error("Started Event Parsing Application");
        if (args.length == 0) {
            try {
                logger.error("Please pass the log file path as command line argument");
                throw new Exception("Please pass the log file path as command line argument");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        ReadEventService readEventService = new ReadEventService();
        List<InputEvent> inputEventList = readEventService.readEventFile(args[0]);
        ProcessEventService processEventService = new ProcessEventService();
        Map<String, List<InputEvent>> eventMap = processEventService.createEventMap(inputEventList);
        List<Event> eventList = processEventService.createEventList(eventMap);
        DbEventService dbEventService = new DbEventService();
        dbEventService.saveEvent(eventList);
        dbEventService.readEvent();

    }

}
