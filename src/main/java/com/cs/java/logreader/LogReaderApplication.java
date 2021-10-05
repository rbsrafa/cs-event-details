package com.cs.java.logreader;

import com.cs.java.logreader.repositories.EventDetailsRepository;
import com.cs.java.logreader.repositories.ServerEventDetailsRepository;
import com.cs.java.logreader.utils.EventLogsWrapper;
import com.cs.java.logreader.utils.EventUtils;
import com.cs.java.logreader.utils.EventDetailsWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LogReaderApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(LogReaderApplication.class);

	@Autowired
	private EventDetailsRepository events;

	@Autowired
	private ServerEventDetailsRepository serverEvents;

	@Value("${logfile.path}")
	private String logfilePath;

	public static void main(String[] args) {
		SpringApplication.run(LogReaderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		EventUtils utils = new EventUtils();

		EventLogsWrapper eventLogsWrapper = utils.getEventLogs(logfilePath);

		// Generate Event Details
		EventDetailsWrapper eventDetailsWrapper = new EventDetailsWrapper();
		eventDetailsWrapper.setEventDetails(utils.generateEventDetails(eventLogsWrapper));
		eventDetailsWrapper.setServerEventDetails(utils.generateServerEventDetails(eventLogsWrapper));

		// Persist Event Details
		this.events.saveAll(eventDetailsWrapper.getEventDetails());
		this.serverEvents.saveAll(eventDetailsWrapper.getServerEventDetails());
	}

}
