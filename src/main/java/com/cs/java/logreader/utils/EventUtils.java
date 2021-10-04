package com.cs.java.logreader.utils;

import com.cs.java.logreader.models.EventDetails;
import com.cs.java.logreader.models.ServerEventDetails;
import com.cs.java.logreader.models.ServerEventLog;
import com.cs.java.logreader.models.State;
import com.cs.java.logreader.models.EventLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EventUtils {

    private static final Logger logger = LoggerFactory.getLogger(EventUtils.class);

    public List<EventDetails> generateEventDetails(EventLogsWrapper eventLogsWrapper) {
        logger.info("Generating event details...");
        List<EventDetails> eventDetails = new ArrayList<>();
        Map<String, EventDetails> eventDetailsMap = new HashMap<>();

        eventLogsWrapper.getEventLogs().forEach(event -> {
            logger.debug("Processing event: {}", event);

            if(!eventDetailsMap.containsKey(event.getId())) {
                eventDetailsMap.put(event.getId(), new EventDetails(event.getId(), event.getTimestamp()));
            } else {
                EventDetails eventDetail = eventDetailsMap.get(event.getId());
                long duration = proccessEventDuration(eventDetail.getDuration(), event.getTimestamp(), event.getState());;

                eventDetail.setDuration(duration);
                eventDetail.setAlert(duration > 4 ? true : false);

                logger.debug("Adding event detail: {}", eventDetail);
                eventDetailsMap.put(event.getId(), eventDetail);
            }
        });

        eventDetails.addAll(new ArrayList<>(eventDetailsMap.values()));
        return eventDetails;
    }

    public List<ServerEventDetails> generateServerEventDetails(EventLogsWrapper eventLogsWrapper) {
        logger.info("Generating server event details...");
        List<ServerEventDetails> serverEventDetails = new ArrayList<>();
        Map<String, ServerEventDetails> serverEventDetailsMap = new HashMap<>();

        eventLogsWrapper.getServerEventLogs().forEach(event -> {
            logger.debug("Processing server event: {}", event);

            if(!serverEventDetailsMap.containsKey(event.getId())) {
                serverEventDetailsMap.put(event.getId(), new ServerEventDetails(event.getId(), event.getTimestamp(), event.getType(), event.getHost()));
            } else {
                ServerEventDetails serverEventDetail = serverEventDetailsMap.get(event.getId());
                long duration = proccessEventDuration(serverEventDetail.getDuration(), event.getTimestamp(), event.getState());

                serverEventDetail.setDuration(duration);
                serverEventDetail.setAlert(duration > 4 ? true : false);

                logger.debug("Adding event detail: {}", serverEventDetail);
                serverEventDetailsMap.put(event.getId(), serverEventDetail);
            }
        });

        serverEventDetails.addAll(new ArrayList<>(serverEventDetailsMap.values()));
        return serverEventDetails;
    }

    private long proccessEventDuration(long value1, long value2, State state) {
        long duration;
        if(state == State.FINISHED) {
            duration = ((value1 * -1) + value2);
        } else {
            duration = (value1 - value2);
        }
        return duration;
    }

    public EventLogsWrapper getEventLogs(String fileAbsolutePath) throws IOException {
        logger.info("Getting event logs from file: {}", fileAbsolutePath);

        ObjectMapper objectMapper = new ObjectMapper();
        EventLogsWrapper eventLogsWrapper = new EventLogsWrapper();
        FileInputStream inputStream = null;
        Scanner sc = null;

        try {
            inputStream = new FileInputStream(fileAbsolutePath);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.contains("\"type\"") && line.toString().contains("\"host\"")) {
                    logger.debug("Adding ServerEventLog: {}", line);
                    eventLogsWrapper.getServerEventLogs().add(objectMapper.readValue(line, ServerEventLog.class));
                } else {
                    logger.debug("Adding EventLog: {}", line);
                    eventLogsWrapper.getEventLogs().add(objectMapper.readValue(line, EventLog.class));
                }
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
        return eventLogsWrapper;
    }
}
