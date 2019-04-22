/**
 * 
 */
package com.mypocs.thenamegame.service;

import com.mypocs.thenamegame.model.UserStats;
import com.mypocs.thenamegame.model.ChallengeWithAnswer;

/**
 * @author Raj Thuppanna
 * 
 * Responsible for caching user session data. Implementing service can decide how to cache
 *
 */
public interface CachingService {
	public ChallengeWithAnswer getCurrentChallenge();
	void setCurrentChallenge(ChallengeWithAnswer challengeWithAnswer);
	
	public UserStats getSessionStats();
	public void setSessionStats(UserStats updatedStats);
	
	public UserStats getUserStats(String userName);
	public void setUserStats(String userName, UserStats updatedStats);

}
