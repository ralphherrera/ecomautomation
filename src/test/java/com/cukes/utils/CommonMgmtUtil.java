package com.cukes.utils;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cukes.bean.Scenario;
import com.cukes.constants.CommonConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class CommonMgmtUtil {
	
	private static final Logger log = LogManager.getLogger(CommonMgmtUtil.class);

	/**
	 * Private constructor
	 */
	private CommonMgmtUtil(){}

	
	/**
     * Description: Wait for pollTime seconds
	 * @param pollTime - Time to wait for next actions
	 */
	public static void implementPollTime(int pollTime) {
		Instant start = Instant.now();
		try {
			Duration timeElapsed = Duration.ZERO;
			while (timeElapsed.toMillis()/1000 < pollTime) {
				Instant end = Instant.now();
				timeElapsed = Duration.between(start, end);
			}
		} catch (Exception e) {
			log.error("Error in implementPollTime.", e);
		}
	}
	
	public static void resetInterruptFlag () {
		if (Thread.currentThread().isInterrupted()) {
 			log.debug("Thread is interrupted.");
			Thread.currentThread().interrupt();
			log.debug("Interrupt Flag: {}", Thread.interrupted());
 		}
	}
	
	/**
	 * Calculates elapsed time then implements a polling time
	 * equivalent to 3 seconds set in the Common Constants Class
	 * @param now - the time instance set from the wait method
	 * @return elapsed time
	 */
	public static Duration pollThenCalculateDuration(Instant now) {
		Instant end = Instant.now();
		implementPollTime(CommonConstants.POLLING_TIME);
		return Duration.between(now, end);
	}
	
	/**
	 * Description: Split string
	 * @param stringToSplit - string value of string to be split
	 * @param regExp - string value of regular expression
	 * @return String[]
	 */
	public static String[] splitString(String stringToSplit, String regExp) {
		log.entry();
		String[] splitString = stringToSplit.split(regExp);
		log.exit();
		return splitString;
	}
	
	/**
	 * 
	 */
	public static String JSONFileToString(String jsonFile) {
		URL file = Resources.getResource(jsonFile);
		String jsonString = null;

		try {
		jsonString = Resources.toString(file, Charsets.UTF_8);
		} catch (Exception e) {
		log.error("JSON file not found {}", e);
		}

		return jsonString;
	}
	
	/**
	 * 
	 */
	public static Scenario getObjectScenario() {
		Scenario scenario = null;
		String fileLocation = "";
		String json = JSONFileToString(fileLocation);
		ObjectMapper mapper = new ObjectMapper();
		try {
			scenario = mapper.readValue(json, Scenario.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return scenario;
	}
}
