/**
 * 
 */
package com.mypocs.thenamegame.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Raj Thuppanna
 *
 */
public class UserStats {
	

	/** User to whom these stats belong */
	@Getter @Setter String userName;
		
	/** Total number of attempts where the subject was identified correctly */
	@Getter @Setter int correctAttemptsCount = 0;
	
	/** Total number of attempts where the subject was identified incorrectly */
	@Getter @Setter int incorrectAttemptsCount = 0;
	
	/** Total time in seconds to identify subjects across all challenges attempted so far */
	@Setter int totalTimeToIdentifySubject = 0;

	
	public UserStats(String userName) {
		super();
		this.userName = userName;
	}
	
	/**
	 * Update stats after completed attempt
	 * @param currentAttemptSuccesful - Was user successful in identifying subject
	 * @param timeToIdentify - Time taken by user in seconds to identify subject
	 */
	public void updateStats(boolean currentAttemptSuccesful, long timeToIdentify) {
		
		if(currentAttemptSuccesful) {
			this.correctAttemptsCount++;
		} else {
			this.incorrectAttemptsCount++;
		}
		
		this.totalTimeToIdentifySubject += timeToIdentify; 	
	}
	
	public long getAverageTimeToIdentify() {
		if((this.correctAttemptsCount + this.incorrectAttemptsCount) == 0)
		{
			return 0;
		}
		return (this.totalTimeToIdentifySubject / (this.correctAttemptsCount + this.incorrectAttemptsCount))/1000;
	}
}
