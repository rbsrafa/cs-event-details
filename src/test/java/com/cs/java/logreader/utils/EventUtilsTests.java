package com.cs.java.logreader.utils;

import com.cs.java.logreader.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EventUtilsTests {

	EventUtils utils = new EventUtils();
	String filePath;

	@BeforeAll
	public void setup() throws IOException {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("my.properties");
		Properties p = new Properties();
		p.load(is);
		filePath = p.getProperty("logfile.path");
	}

	@Test
	void utilsGetEventLogsTest() throws IOException {
		EventLogsWrapper eventLogsWrapper = utils.getEventLogs(filePath);
		EventLog el1 = new EventLog("scsmbstgrb", State.STARTED, 1491377495213L);

		Assertions.assertEquals(el1.getId(), eventLogsWrapper.getEventLogs().get(0).getId());
		Assertions.assertEquals(el1.getState(), eventLogsWrapper.getEventLogs().get(0).getState());
		Assertions.assertEquals(el1.getTimestamp(), eventLogsWrapper.getEventLogs().get(0).getTimestamp());
	}

	@Test
	void utilsGetServerEventLogsTest() throws IOException {
		EventLogsWrapper eventLogsWrapper = utils.getEventLogs(filePath);
		ServerEventLog sel1 = new ServerEventLog("scsmbstgra", State.STARTED, 1491377495212L, "APPLICATION_LOG", "12345");

		Assertions.assertEquals(sel1.getId(), eventLogsWrapper.getServerEventLogs().get(0).getId());
		Assertions.assertEquals(sel1.getState(), eventLogsWrapper.getServerEventLogs().get(0).getState());
		Assertions.assertEquals(sel1.getTimestamp(), eventLogsWrapper.getServerEventLogs().get(0).getTimestamp());
		Assertions.assertEquals(sel1.getType(), eventLogsWrapper.getServerEventLogs().get(0).getType());
		Assertions.assertEquals(sel1.getHost(), eventLogsWrapper.getServerEventLogs().get(0).getHost());
	}

	@Test
	void utilsGenerateEventDetailsTest() throws IOException {
		EventDetails ed1 = new EventDetails("scsmbstgrb", 3, false);
		EventDetails ed2 = new EventDetails("scsmbstgrc", 8, true);

		EventLogsWrapper eventLogsWrapper = utils.getEventLogs(filePath);
		List<EventDetails> eventDetails = utils.generateEventDetails(eventLogsWrapper);

		Assertions.assertEquals(ed1.getId(), eventDetails.get(0).getId());
		Assertions.assertEquals(ed1.getDuration(), eventDetails.get(0).getDuration());
		Assertions.assertEquals(ed1.isAlert(), eventDetails.get(0).isAlert());
		Assertions.assertEquals(ed1.isAlert(), eventDetails.get(0).isAlert());
		Assertions.assertEquals(ed1.isAlert(), eventDetails.get(0).isAlert());

		Assertions.assertEquals(ed2.getId(), eventDetails.get(1).getId());
		Assertions.assertEquals(ed2.getDuration(), eventDetails.get(1).getDuration());
		Assertions.assertEquals(ed2.isAlert(), eventDetails.get(1).isAlert());
	}

	@Test
	void utilsGenerateServerEventDetailsTest() throws IOException {
		ServerEventDetails sed1 = new ServerEventDetails("scsmbstgra", 5, true, "APPLICATION_LOG", "12345");
		ServerEventDetails sed2 = new ServerEventDetails("scsmbstgrd", 2, false, "APPLICATION_LOG", "54321");

		EventLogsWrapper eventLogsWrapper = utils.getEventLogs(filePath);
		List<ServerEventDetails> serverEventDetails = utils.generateServerEventDetails(eventLogsWrapper);

		Assertions.assertEquals(sed1.getId(), serverEventDetails.get(0).getId());
		Assertions.assertEquals(sed1.getDuration(), serverEventDetails.get(0).getDuration());
		Assertions.assertEquals(sed1.isAlert(), serverEventDetails.get(0).isAlert());
		Assertions.assertEquals(sed1.getType(), serverEventDetails.get(0).getType());
		Assertions.assertEquals(sed1.getHost(), serverEventDetails.get(0).getHost());

		Assertions.assertEquals(sed2.getId(), serverEventDetails.get(1).getId());
		Assertions.assertEquals(sed2.getDuration(), serverEventDetails.get(1).getDuration());
		Assertions.assertEquals(sed2.isAlert(), serverEventDetails.get(1).isAlert());
		Assertions.assertEquals(sed2.getType(), serverEventDetails.get(1).getType());
		Assertions.assertEquals(sed2.getHost(), serverEventDetails.get(1).getHost());
	}

}
