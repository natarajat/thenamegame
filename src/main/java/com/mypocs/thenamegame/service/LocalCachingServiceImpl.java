/**
 * 
 */
package com.mypocs.thenamegame.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mypocs.thenamegame.model.ChallengeWithAnswer;
import com.mypocs.thenamegame.model.UserStats;

/**
 * @author Raj Thuppanna
 *
 */
@Service
public class LocalCachingServiceImpl implements CachingService{

	@Autowired(required=true)
	private HttpServletRequest request;

	final static String STATS_KEY = "STATS";
	final static String CURRENT_CHALLENGE_KEY = "currentChallenge";

	/** Quick/local static store to capture user stats across sessions */
	static Map<String, UserStats> userStataMap = new ConcurrentHashMap<String, UserStats>();

	@Override 
	public ChallengeWithAnswer getCurrentChallenge() {
		return (ChallengeWithAnswer)request.getSession().getAttribute(CURRENT_CHALLENGE_KEY);
	}

	@Override
	public void setCurrentChallenge(ChallengeWithAnswer challengeWithAnswer) {
		request.getSession().setAttribute(CURRENT_CHALLENGE_KEY, challengeWithAnswer);
	}


	@Override 
	public void setSessionStats(UserStats updatedStats) {
		request.getSession().setAttribute(STATS_KEY, updatedStats);
	}

	@Override
	public UserStats getSessionStats() {
		
		//ChallengeStats currentUserStats = LocalCachingServiceImpl.userStatsMap.get(userName);
		UserStats currentUserStats = (UserStats)request.getSession().getAttribute(STATS_KEY);
		if(currentUserStats == null) {
			currentUserStats = new UserStats(SecurityContextHolder.getContext().getAuthentication().getName());
			//LocalCachingServiceImpl.userStatsMap.put(userName, currentUserStats);
			request.getSession().setAttribute(STATS_KEY, currentUserStats);
		}
		return currentUserStats;
	}

	@Override
	public UserStats getUserStats(String userName) {
		//ChallengeStats currentUserStats = LocalCachingServiceImpl.userStatsMap.get(userName);
		UserStats currentUserStats = userStataMap.get(userName);
		if(currentUserStats == null) {
			currentUserStats = new UserStats(SecurityContextHolder.getContext().getAuthentication().getName());
			//LocalCachingServiceImpl.userStatsMap.put(userName, currentUserStats);
			userStataMap.put(userName, currentUserStats);
		}
		return currentUserStats;
	}

	@Override
	public void setUserStats(String userName, UserStats updatedStats) {
		userStataMap.put(userName, updatedStats);
	}
}
