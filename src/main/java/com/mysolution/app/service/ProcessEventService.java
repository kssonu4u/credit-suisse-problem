package com.mysolution.app.service;

import com.mysolution.app.model.InputEvent;
import com.mysolution.app.model.EventCalculation;
import com.mysolution.app.model.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ProcessEventService {

    private static final long flaggedDuration = 4;

    private static final String started = "STARTED";

    private static final String finished = "FINISHED";

    private static final Logger logger = LogManager.getLogger(ProcessEventService.class);

    public final Map<String,List<InputEvent>> createEventMap(List<InputEvent> inputEventList) {
        logger.info("Started created event map");
        Map<String,List<InputEvent>> eventMap = new HashMap<>();
        for (InputEvent inputEvent : inputEventList) {
            if (eventMap.get(inputEvent.getId()) == null){
                List<InputEvent> inputEventArrayList = new LinkedList<>();
                inputEventArrayList.add(inputEvent);
                eventMap.put(inputEvent.getId(), inputEventArrayList);
            }
            else{
                List<InputEvent> existingInputEventList = eventMap.get(inputEvent.getId());
                existingInputEventList.add(inputEvent);
                eventMap.put(inputEvent.getId(), existingInputEventList);
            }
        }
        logger.info("Finished creating event map");
        logger.debug("Finished creating event map with data {}",eventMap);
        return eventMap;
    }

    public final List<Event> createEventList(Map<String,List<InputEvent>> eventMap) {
        logger.info("Started created final event list from event map");
        List<Event> eventList = new LinkedList<>();
        for (Map.Entry<String, List<InputEvent>> entry : eventMap.entrySet()) {
            String id = entry.getKey();
            List<InputEvent> inputEventList = entry.getValue();
            EventCalculation eventCalculation = calculateEventDetails(inputEventList);
            eventList.add(new Event(id, inputEventList.get(0).getType(), inputEventList.get(0).getHost(),
                    eventCalculation.getDuration(),eventCalculation.isAlert()));

        }
        logger.info("Finished creating final event list from event map");
        logger.debug("Finished creating final event list with data {}", eventList);
        return eventList;
    }

    private final EventCalculation calculateEventDetails(List<InputEvent> inputEventList){
        long startTime = 0;
        long endTime = 0;
        boolean isAlert = false;
        for (InputEvent inputEvent : inputEventList) {
            if(inputEvent.getState().equals(started)){
                startTime = inputEvent.getTimestamp();
            } else if (inputEvent.getState().equals(finished)) {
                endTime = inputEvent.getTimestamp();
            }
        }
        long duration = endTime - startTime;
        if (duration > flaggedDuration){
            isAlert = true;
        }
        return new EventCalculation(duration,isAlert);
    }

}
