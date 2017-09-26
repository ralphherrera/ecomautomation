package com.cukes.utils;

import java.time.Duration;
import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cukes.constants.CommonConstants;


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
}
